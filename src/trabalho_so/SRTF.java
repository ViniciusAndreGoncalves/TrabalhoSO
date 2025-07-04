package trabalho_so;
import java.util.List;

public class SRTF implements Algoritmo {

    @Override
    public boolean isPreemptivo() {
        return true;
    }

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

}
