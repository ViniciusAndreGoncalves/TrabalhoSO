package trabalho_so;
import java.util.List;

public class RM implements Algoritmo{

    @Override
    public TCB escolherTarefa(List<TCB> filaPronto) {
        if(filaPronto.isEmpty()) return null;

        TCB tarefaMenorPeriodo = filaPronto.get(0);
        for (TCB tarefa : filaPronto) {
            if (tarefa.getTarefa().getPeriodTime() < tarefaMenorPeriodo.getTarefa().getPeriodTime()) {
                tarefaMenorPeriodo = tarefa;
            }
        }
        return tarefaMenorPeriodo;
    }

    @Override
    public boolean isPreemptivo() {
        return true;
    }

}
