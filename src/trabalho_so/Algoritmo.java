package trabalho_so;

import java.util.List;

public interface Algoritmo {
    //método que retorna a tarefa mais adequada conforme o algoritmo.
    TCB escolherTarefa(List<TCB> filaPronto);

    //método para saber se o algoritmo é(return true) ou não preemptivo(false)
    boolean isPreemptivo();
}
