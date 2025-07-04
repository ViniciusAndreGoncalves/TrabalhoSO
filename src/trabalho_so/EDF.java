package trabalho_so;

import java.util.List;


public class EDF implements Algoritmo {

    @Override
    public TCB escolherTarefa(List<TCB> filaPronto) {
        if(filaPronto.isEmpty()) return null;

        TCB tarefaMenorDL = filaPronto.get(0);
        for (TCB tarefa : filaPronto) {
            if (tarefa.getDeadlineAbsoluto() < tarefaMenorDL.getDeadlineAbsoluto()) {
                tarefaMenorDL = tarefa;
            }
        }
        return tarefaMenorDL;
    }

    @Override
    public boolean isPreemptivo() {
        return true;
    }

}
