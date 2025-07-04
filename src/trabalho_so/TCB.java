package trabalho_so;

public class TCB {
    private Tarefa tarefa;
    private int tempoRestante, quantumRestante;
    private String estado;
    private int inicio = -1;
    private int fim = -1;
    private int waitingTime = 0;
    private int tempoChegadaFila = -1;
    private int tempoExecucaoTotal;
    private int tempoNaFila = 0;
    private boolean perdeuDeadline = false; 
    private int chegadaInstancia;     

    public TCB(Tarefa tarefa) {
        this.tarefa = tarefa;
        this.tempoRestante = tarefa.getComputationTime();
        this.estado = "NEW";
    }

    public TCB(TCB copia){
        this.tarefa = copia.getTarefa();
        this.tempoRestante = copia.getTarefa().getComputationTime();
        this.tempoExecucaoTotal = 0; 
        this.estado = "NEW";
    }

    
    public int getOffset(){
        return tarefa.getOffset();
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }


    public int getTempoRestante() {
        return this.tempoRestante;
    }

    public void setTempoRestante(int tempoRestante) {
        this.tempoRestante = tempoRestante;
    }


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public void executarUmCiclo() {
        if (tempoRestante >= 0) tempoRestante--;
    }

    public boolean terminou() {
        return tempoRestante <= 0;
    }
    
    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getFim() {
        return fim;
    }

    public void setFim(int fim) {
        this.fim = fim;
    }

    public int getWaitingTime() {
        return this.waitingTime;
    }
    
    public int getTempoChegada() {
        return this.tempoChegadaFila;
    }    
    public void setTempoChegada(int tempo) {
        this.tempoChegadaFila = tempo;
    }

    public void incrementarEspera(){
        waitingTime++;
    }
    public void resetarEspera(){
        waitingTime = 0;
    }

    //Para RR
    public int getQuantumRestante() {
        return quantumRestante;
    }
    public void resetarQuantum() {
        this.quantumRestante = tarefa.getQuantum();
    }
    public void decrementarQuantum(){
        if (quantumRestante > 0) quantumRestante--;
    }

    public int getTempoExecucaoTotal(){
        return tempoExecucaoTotal;
    }
    public void incrementarTempoExecTotal(){
        tempoExecucaoTotal++;
    }
    
    
    // Para EDF e RM
    public int getDeadlineAbsoluto(){
        return tarefa.getDeadline() + getTempoChegada();
    }

    public int getPeriodo(){
        return tarefa.getPeriodTime();
    }

    
    public int getChegadaInstancia() {
        return chegadaInstancia;
    }

    public void setChegadaInstancia(int chegadaInstancia) {
        this.chegadaInstancia = chegadaInstancia;
    }

    
    public void incrementarTempoNaFila() {
        tempoNaFila++;
    }

    public void resetarTempoNaFila() {
        tempoNaFila = 0;
    }

    public int getTempoNaFila() {
        return tempoNaFila;
    }

    // flag deadline perdido
    public boolean isPerdeuDeadline() {
        return perdeuDeadline;
    }

    public void setPerdeuDeadline(boolean perdeuDeadline) {
        this.perdeuDeadline = perdeuDeadline;
    }


    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        TCB outro = (TCB) obj;
        return this.tarefa.getTaskID() == outro.tarefa.getTaskID();
    }

    @Override
    public int hashCode(){
        return Integer.hashCode(tarefa.getTaskID());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TCB{");
        
        
        sb.append("Tarefa :").append(tarefa.taskID);
        sb.append(", inicio=").append(tempoChegadaFila);
        sb.append(", fim=").append(fim);
        sb.append(", waitingTime=").append(waitingTime);
        
        sb.append('}');
        return sb.toString();
    }

}
