package trabalho_so;

import java.util.ArrayList;
import java.util.List;

public class Estatisticas {
    List<TCB> tarefas;
    private Algoritmo algoritmo;

    public void imprimirEstatisticas(ArrayList<TCB> tarefas) {
        
        //Imprimir WT se precisar
//        for (TCB tcb : tarefas) {
//            System.out.println(tcb);
//        }

        int numTarefas = 0;
        for (TCB tarefa : tarefas) {
            numTarefas = Math.max(numTarefas, tarefa.getTarefa().getTaskID());
        }
        numTarefas++;
        
        ArrayList<Integer> totalWT = new ArrayList<>();
        ArrayList<Integer> totalTAT = new ArrayList<>();
        ArrayList<Integer> totalExecucoes = new ArrayList<>();
        for (int i = 0; i < numTarefas; i++) {
            totalWT.add(0);
            totalTAT.add(0);
            totalExecucoes.add(0);
        }

        for (TCB infosTarefa : tarefas) {
            int id = infosTarefa.getTarefa().getTaskID();
            int tat = infosTarefa.getFim() - infosTarefa.getTempoChegada(); 
            
            int wt = infosTarefa.getFim() - infosTarefa.getTempoChegada() - infosTarefa.getTempoExecucaoTotal();

            totalWT.set(id, totalWT.get(id) + wt);
            totalTAT.set(id, totalTAT.get(id) + tat);
            totalExecucoes.set(id, totalExecucoes.get(id) + 1);
        }

        System.out.println("\n====================== ESTATISTICAS =======================");

        double somaWT = 0;
        double somaTAT = 0;
        int totalInstancias = 0;

        for (int i = 0; i < numTarefas; i++) {
            int execucoes = totalExecucoes.get(i);
            if (execucoes > 0) {
                double mediaWT_T = totalWT.get(i) / (double) execucoes;
                double mediaTAT_T = totalTAT.get(i) / (double) execucoes;
                System.out.printf("Tarefa %d | WT avg: %.2f | TAT avg: %.2f | Instancias %d \n", i, mediaWT_T, mediaTAT_T, execucoes);

                somaWT += totalWT.get(i);
                somaTAT += totalTAT.get(i);
                totalInstancias += execucoes;
            }
        }
        System.out.printf("\nWaiting Time Medio do sistema = %.2f \n", somaWT / totalInstancias);
        System.out.printf("Turn Around Time Medio do sistema = %.2f \n", somaTAT / totalInstancias);

        System.out.println("===========================================================");

    }
}
