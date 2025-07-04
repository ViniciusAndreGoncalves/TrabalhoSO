package trabalho_so;
public class Tarefa {
    int taskID = 0;
    int offset;
    int computation_time;
    int period_time;
    int quantum;
    int deadline;

    public Tarefa(int taskID, int offset, int computation_time, int period_time, int quantum, int deadline) {
        this.taskID = taskID;
        this.offset = offset;
        this.computation_time = computation_time;
        this.quantum = quantum;
        this.period_time = period_time;
        this.deadline = deadline;
    }

    public int getTaskID() {
        return taskID;
    }
    
    public int getOffset() {
        return offset;
    }
    public void setOffset(int offset) {
        this.offset = offset;
    }
    public int getComputationTime() {
        return computation_time;
    }
    public void setComputationTime(int computation_time) {
        this.computation_time = computation_time;
    }
    public int getPeriodTime() {
        return period_time;
    }
    public void setPeriodTime(int period_time) {
        this.period_time = period_time;
    }
    public int getQuantum() {
        return quantum;
    }
    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }
    public int getDeadline() {
        return deadline;
    }
    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }
    
}