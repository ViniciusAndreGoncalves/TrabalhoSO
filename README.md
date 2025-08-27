# Simulação de Algoritmos de Escalonamento

[cite\_start]Este é um projeto para a disciplina de Sistemas Operacionais do curso de Ciência da Computação do Instituto Federal de Santa Catarina (IFSC) - Câmpus Lages[cite: 1, 5, 6]. [cite\_start]O objetivo é desenvolver um software que simula diferentes algoritmos de escalonamento para obter informações estatísticas e análises de viabilidade[cite: 10].

## ⚙️ Configuração da Simulação

[cite\_start]O simulador deve ser capaz de ler um arquivo de configuração no formato JSON[cite: 11]. Abaixo está um exemplo da estrutura do arquivo:

```json
{
  "simulation_time": 14,
  "scheduler_name": "FCFS",
  "tasks_number": 4,
  "tasks": [
    {
      "offset": 0,
      "computation_time": 5,
      "period_time": 14,
      "quantum": 1,
      "deadline": 14
    },
    {
      "offset": 1,
      "computation_time": 2,
      "period_time": 10,
      "quantum": 2,
      "deadline": 5
    },
    {
      "offset": 3,
      "computation_time": 7,
      "period_time": 30,
      "quantum": 5,
      "deadline": 40
    },
    {
      "offset": 2,
      "computation_time": 3,
      "period_time": 15,
      "quantum": 3,
      "deadline": 10
    }
  ]
}
```

[cite\_start][cite: 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 24, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 37, 40, 41, 42, 43]

## 💻 Módulos de Simulação

### Algoritmos de Escalonamento Tradicionais

#### FCFS (First Come First Served) e SJF (Shortest Job First)

[cite\_start]Para os algoritmos FCFS e SJF, o simulador deve[cite: 51]:

  * [cite\_start]Descrever a sequência de execução das tarefas[cite: 52].
  * [cite\_start]Calcular o nível de utilização do sistema[cite: 52].
  * [cite\_start]Calcular o *turnaround time* médio de cada tarefa ($TAT\_{avg}^{n}$)[cite: 53].
  * [cite\_start]Calcular o *turnaround time* médio do sistema ($TAT\_{avg}$)[cite: 54].
  * [cite\_start]Calcular o *waiting time* médio de cada tarefa ($WT\_{avg}^{n}$)[cite: 55].
  * [cite\_start]Calcular o *waiting time* médio do sistema ($WT\_{avg}$)[cite: 56].
  * [cite\_start]Apontar as tarefas com maior e menor *waiting time* médio[cite: 57].
  * [cite\_start]Apontar se alguma tarefa sofre *starvation*[cite: 57].

#### RR (Round Robin) e SRTF (Shortest Remaining Time First)

[cite\_start]Para os algoritmos RR e SRTF, o simulador deve[cite: 58]:

  * [cite\_start]Descrever a sequência de execução das tarefas[cite: 59].
  * [cite\_start]Calcular o nível de utilização do sistema[cite: 59].
  * [cite\_start]Calcular o *turnaround time* médio de cada tarefa ($TAT\_{avg}^{n}$)[cite: 60].
  * [cite\_start]Calcular o *turnaround time* médio do sistema ($TAT\_{avg}$)[cite: 61].
  * [cite\_start]Calcular o *waiting time* médio de cada tarefa ($WT\_{avg}^{n}$)[cite: 62].
  * [cite\_start]Calcular o *waiting time* médio do sistema ($WT\_{avg}$)[cite: 63].
  * [cite\_start]Apontar as tarefas com maior e menor *waiting time* médio[cite: 64].
  * [cite\_start]Apontar se alguma tarefa sofre *starvation*[cite: 64].
  * [cite\_start]Apontar as tarefas envolvidas e o momento, caso ocorra inversão de prioridade[cite: 65].

### Algoritmos de Escalonamento de Tempo Real

#### RM (Rate Monotonic) e EDF (Earliest Deadline First)

[cite\_start]Para os algoritmos RM e EDF, o simulador deve[cite: 74]:

  * [cite\_start]Realizar e apresentar o resultado do teste de escalonabilidade[cite: 76].
  * [cite\_start]Descrever a sequência de execução das tarefas, independentemente do resultado do teste[cite: 77].
  * [cite\_start]Apontar qual tarefa perdeu o *deadline*, em que momento e com qual frequência, caso ocorra[cite: 78].
  * [cite\_start]Calcular o nível de utilização do sistema[cite: 79].
  * [cite\_start]Calcular o *turnaround time* médio de cada tarefa ($TAT\_{avg}^{n}$)[cite: 80].
  * [cite\_start]Calcular o *turnaround time* médio do sistema ($TA{T\_{avg}}$)[cite: 81].
  * [cite\_start]Calcular o *waiting time* médio de cada tarefa ($WT\_{avg}^{n}$)[cite: 82].
  * [cite\_start]Calcular o *waiting time* médio do sistema ($WT\_{avg}$)[cite: 83].
  * [cite\_start]Apontar as tarefas com maior e menor *waiting time* médio[cite: 84].
  * [cite\_start]Apontar se alguma tarefa sofre *starvation*[cite: 84].
  * [cite\_start]Apontar as tarefas envolvidas e o momento, caso ocorra inversão de prioridade[cite: 85].

## ℹ️ Instruções Gerais e Avaliação

  * [cite\_start]O desenvolvimento pode ser realizado em dupla[cite: 86].
  * [cite\_start]A linguagem de programação é de livre escolha[cite: 87].
  * [cite\_start]A nota será individual e baseada na apresentação em sala de aula[cite: 88].

[cite\_start]No momento da apresentação, será solicitado[cite: 89]:

  * [cite\_start]A abertura e explicação do código fonte[cite: 91].
  * [cite\_start]A execução e demonstração dos algoritmos com um arquivo de configuração fornecido pelo professor[cite: 92].
