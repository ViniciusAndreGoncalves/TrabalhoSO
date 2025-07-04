
package trabalho_so;
import java.util.List;

public class RR implements Algoritmo {

    @Override
    public TCB escolherTarefa(List<TCB> filaPronto) {
        if (filaPronto.isEmpty()) {
            return null;
        }
        // Round Robin chama a primeira tarefa da fila
        return filaPronto.get(0);
    }

    @Override
    public boolean isPreemptivo() {
        return true;
    }

}
