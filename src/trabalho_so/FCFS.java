package trabalho_so;
import java.util.List;

public class FCFS implements Algoritmo{

    @Override
    public boolean isPreemptivo() {
        return false;
    }

    @Override
    public TCB escolherTarefa(List<TCB> filaPronto) {
        if (filaPronto == null || filaPronto.isEmpty()) {
            return null;
        }
        return filaPronto.get(0);
    }

}