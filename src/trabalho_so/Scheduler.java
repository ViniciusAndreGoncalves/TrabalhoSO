package trabalho_so;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private TCB tarefaAtual;
    private List<TCB> filaProntos = new ArrayList<>();


    public void setTarefaAtual(TCB tcb) {
        this.tarefaAtual = tcb;
    }

    public TCB getTarefaAtual(){
        return this.tarefaAtual;
    }

    public List<TCB> getFilaProntos() {
        return this.filaProntos;
    }

    public void setFilaProntos(List<TCB> filaProntos) {
        this.filaProntos = filaProntos;
    }

    public void adicionarNaFila(TCB tcb) {
        filaProntos.add(tcb);
    }

}
