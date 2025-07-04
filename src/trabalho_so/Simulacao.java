package trabalho_so;

import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class Simulacao {
    private Scheduler scheduler;
    private Dispatcher dispatcher;
    private Algoritmo algoritmo;
    private List<TCB> tarefas;
    private int tempoTotal;
    private String nomeSimulador;
    private ArrayList<TCB> tarefasFinalizadas;
    private ArrayList<TCB> tarefasMonitoradas = new ArrayList<>();


    public Simulacao(String caminhoConfig) {
        this.scheduler = new Scheduler();
        this.dispatcher = new Dispatcher();
        this.tarefas = new ArrayList<>();
        this.tarefasFinalizadas = new ArrayList<>();
        
        carregarConfiguracao(caminhoConfig); 
        switch (nomeSimulador) {
            case "FCFS":
                this.algoritmo = new FCFS();
                System.out.println("\n    Simulacao - First Come First Served - FCFS\n");
                executar();
                break;
            case "SJF":
                this.algoritmo = new SJF();
                System.out.println("\n    Simulacao - Shortest Job First - SJF\n");
                executar();
                break;
            case "SRTF":
                this.algoritmo = new SRTF();
                System.out.println("\n    Simulacao - Shortest Remaining Time - SRTF\n");
                executar();
                break;
            case "RR":
                this.algoritmo = new RR();
                System.out.println("\n    Simulacao - Round Robin - RR\n");
                executar();
                break;
            case "RM":
                this.algoritmo = new RM();
                System.out.println("\n    Simulacao - Rate Monotonic - RM\n");
                executar();
                break;
            case "EDF":
                this.algoritmo = new EDF();
                System.out.println("\n    Simulação - Earliest Deadline First - EDF\n");
                executar();
                break;
            default:
                System.err.println("Algoritmo desconhecido "+ nomeSimulador);
                System.exit(1);
                break;
        }
        
    }

    private void carregarConfiguracao(String caminho) {
        try {
            String conteudo = new String(Files.readAllBytes(Paths.get(caminho)));
            JSONObject json = new JSONObject(conteudo);

            this.tempoTotal = json.getInt("simulation_time");
            this.nomeSimulador = json.getString("scheduler_name");
            JSONArray arrayTarefas = json.getJSONArray("tasks");

            for (int i = 0; i < arrayTarefas.length(); i++) {
                JSONObject obj = arrayTarefas.getJSONObject(i);
                Tarefa tarefa = new Tarefa(i, 
                    obj.getInt("offset"),
                    obj.getInt("computation_time"),
                    obj.getInt("period_time"),
                    obj.getInt("quantum"),
                    obj.getInt("deadline")
                );
                TCB tcb = new TCB(tarefa);
                tarefas.add(tcb);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar o arquivo JSON.");
            System.exit(1);
        }
    }

    public void executar() {
        for (int tempo = 0; tempo < tempoTotal; tempo++) {
            atualizarFilaDePronto(tempo);
            
            TCB tarefaAtualNaCPU = scheduler.getTarefaAtual();
            TCB proximaTarefa = algoritmo.escolherTarefa(scheduler.getFilaProntos());
            

            if (tarefaAtualNaCPU != null) {
                tarefaAtualNaCPU.executarUmCiclo();
                tarefaAtualNaCPU.incrementarTempoExecTotal();
                
                if (algoritmo instanceof RR) {
                    
                    tarefaAtualNaCPU.decrementarQuantum();

                    if (tarefaAtualNaCPU.getQuantumRestante() == 0 && !tarefaAtualNaCPU.terminou()) {
                    
                    System.out.printf("Tempo %d | Quantum expirou | Tarefa %d preemptada \n", 
                    tempo, tarefaAtualNaCPU.getTarefa().getTaskID());
                    scheduler.getFilaProntos().add(tarefaAtualNaCPU);
                    scheduler.setTarefaAtual(null);
                    
                    TCB proxima = algoritmo.escolherTarefa(scheduler.getFilaProntos());
                    if (proxima != null) {
                        scheduler.getFilaProntos().remove(proxima);
                        dispatcher.despachar(proxima, scheduler, tempo);
                        proxima.resetarQuantum(); // Começa um novo quantum para a nota tarefa
                    }
                }
                
            }
            
            if (tarefaAtualNaCPU.terminou()) {
                dispatcher.finalizar(tarefaAtualNaCPU, scheduler, tempo);
                tarefasFinalizadas.add(tarefaAtualNaCPU);
                tarefasMonitoradas.remove(tarefaAtualNaCPU);
                scheduler.setTarefaAtual(null);
                System.out.printf("Tempo %d | CPU Livre | Tarefa %d terminou \n", 
                tempo, tarefaAtualNaCPU.getTarefa().getTaskID());
                
                TCB proxima = algoritmo.escolherTarefa(scheduler.getFilaProntos());
                if (proxima != null) {
                    scheduler.getFilaProntos().remove(proxima);
                    dispatcher.despachar(proxima, scheduler, tempo);
                    proxima.resetarQuantum();
                }
            }
        }

        
        for (TCB tcb : scheduler.getFilaProntos()) {
            if (tcb != tarefaAtualNaCPU && tcb.getTempoChegada() != tempo) {
                tcb.incrementarEspera();
            } 
            if (!tcb.terminou() && !tcb.isPerdeuDeadline() && tempo > tcb.getDeadlineAbsoluto()) {
                tcb.setPerdeuDeadline(true);
                System.out.printf("Tempo %d | Tarefa %d >>> PERDEU DEADLINE\n", tempo, tcb.getTarefa().getTaskID());
            }
        }

        for (TCB tarefa : tarefasMonitoradas) {
            if (!tarefa.terminou() && !tarefa.isPerdeuDeadline() && tempo > tarefa.getDeadlineAbsoluto()) {
                tarefa.setPerdeuDeadline(true);
                System.out.printf("Tempo %d | Tarefa %d >>> PERDEU DEADLINE\n", tempo, tarefa.getTarefa().getTaskID());
            }
        }
        if (tarefaAtualNaCPU != null 
            && !tarefaAtualNaCPU.isPerdeuDeadline() 
            && !tarefaAtualNaCPU.terminou() 
            && tempo > tarefaAtualNaCPU.getDeadlineAbsoluto()) {
            tarefaAtualNaCPU.setPerdeuDeadline(true);
            System.out.printf("Tempo %d | Tarefa %d >>> PERDEU DEADLINE (em execução)\n", tempo, tarefaAtualNaCPU.getTarefa().getTaskID());
        }




        if (proximaTarefa != null) {
            
            if (algoritmo.isPreemptivo()) {
                boolean precisaPreemptar = false;

                if (tarefaAtualNaCPU == null) {
                    precisaPreemptar = true;
                } else if (algoritmo instanceof RR) {
                    precisaPreemptar = false;
                } else if (algoritmo instanceof RM) {
                    precisaPreemptar = proximaTarefa.getPeriodo() < tarefaAtualNaCPU.getPeriodo();
                } else if (algoritmo instanceof EDF) {
                    precisaPreemptar = proximaTarefa.getDeadlineAbsoluto() < tarefaAtualNaCPU.getDeadlineAbsoluto();
                } else if (proximaTarefa.getTempoRestante() < tarefaAtualNaCPU.getTempoRestante()) {
                    precisaPreemptar = true;
                }
                
                if (precisaPreemptar) {
                    if (tarefaAtualNaCPU != null) {
                        scheduler.getFilaProntos().add(tarefaAtualNaCPU);
                        System.out.printf("Tempo %d | Tarefa %d removida da CPU | -- PREEMPÇÃO --\n", 
                        tempo, tarefaAtualNaCPU.getTarefa().getTaskID());
                    }                    
                    scheduler.getFilaProntos().remove(proximaTarefa);
                    dispatcher.despachar(proximaTarefa, scheduler, tempo);
                    if (algoritmo instanceof RR) {
                        proximaTarefa.resetarQuantum();
                    }
                }

            } else {
                if (tarefaAtualNaCPU == null) { 
                    scheduler.getFilaProntos().remove(proximaTarefa);
                    dispatcher.despachar(proximaTarefa, scheduler, tempo);  
                    if (algoritmo instanceof RR) {
                        proximaTarefa.resetarQuantum();
                    }                      
                }
            }

        }
            System.out.printf("Tempo %d | CPU : %s\n", tempo, 
            scheduler.getTarefaAtual() != null ? "Tarefa " + scheduler.getTarefaAtual().getTarefa().getTaskID() + " Executando...": "Livre");
        }
        TCB emExecucao = scheduler.getTarefaAtual();
        if (emExecucao != null && !emExecucao.terminou()) {
            System.out.printf("Tempo %d | Tarefa %d -- estava em execucao -- Simulacao encerrada\n", 
                tempoTotal, emExecucao.getTarefa().getTaskID());
            System.out.printf(" - Início: %d\n", emExecucao.getInicio());
            System.out.printf(" - Tempo restante: %d\n", emExecucao.getTempoRestante());
            System.out.printf(" - Tempo total executado: %d\n", emExecucao.getTempoExecucaoTotal());
            System.out.printf(" - Tempo de espera: %d\n", emExecucao.getTempoNaFila());
            int fimPlan = emExecucao.getInicio() + emExecucao.getTarefa().getComputationTime();
            System.out.printf(" - Fim planejado: %d\n", fimPlan);
            System.out.println("\n");
        }

        if (!scheduler.getFilaProntos().isEmpty()) {
            System.out.println("Tarefas ainda na fila de prontos ao final da simulacao:");
            for (TCB tcb : scheduler.getFilaProntos()) {
                System.out.printf(" - Tarefa %d (tempo restante: %d)\n",
                    tcb.getTarefa().getTaskID(), tcb.getTempoRestante());
            }
        }
        System.out.println("\n");
        
        Estatisticas stats = new Estatisticas();
        stats.imprimirEstatisticas(tarefasFinalizadas);

    }

    private void atualizarFilaDePronto(int tempo) {
        for (TCB tarefa : tarefas) {
            
            if ((tempo - tarefa.getOffset())% tarefa.getPeriodo() == 0) {
                TCB copiaTarefa = new TCB(tarefa);
                copiaTarefa.setTempoChegada(tempo);
                copiaTarefa.setChegadaInstancia(tempo);
                copiaTarefa.resetarTempoNaFila();
                copiaTarefa.setPerdeuDeadline(false);
                scheduler.adicionarNaFila(copiaTarefa);
                tarefasMonitoradas.add(copiaTarefa);
                tarefa.setEstado("READY");
                System.out.printf("Tempo %d → Tarefa %d adicionada na fila de prontos\n", tempo, tarefa.getTarefa().getTaskID());
                System.out.printf("Tempo %d | Tarefa %d deadline absoluto: %d\n", 
                  tempo, copiaTarefa.getTarefa().getTaskID(), copiaTarefa.getDeadlineAbsoluto());

            }
        }
    }
}
