package trabalho_so;

public class Dispatcher {

    public void despachar(TCB tcb, Scheduler scheduler, int tempo) {
        tcb.setEstado("RUNNING");
        if (tcb.getInicio() == -1) {
            tcb.setInicio(tempo);
        }
        scheduler.setTarefaAtual(tcb);
    }

    public void finalizar(TCB tcb, Scheduler scheduler, int tempo) {
        tcb.setEstado("FINISHED");
        tcb.setFim(tempo);
        scheduler.setTarefaAtual(null);
    }
}
