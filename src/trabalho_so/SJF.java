package trabalho_so;
import java.util.List;

public class SJF implements Algoritmo{

    @Override
    public TCB escolherTarefa(List<TCB> filaPronto) {
        
        if (filaPronto == null || filaPronto.isEmpty()) {
            return null;
        }
        
        TCB tarefaMenorCT = filaPronto.get(0);
        for (TCB tarefa : filaPronto) {
            if (tarefa.getTempoRestante() < tarefaMenorCT.getTempoRestante()) {
                tarefaMenorCT = tarefa;
            }
        }
        
        return tarefaMenorCT;
    }

    @Override
    public boolean isPreemptivo() {
        return false;
    }

}
