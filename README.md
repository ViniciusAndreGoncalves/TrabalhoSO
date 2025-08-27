# Simulação de Algoritmos de Escalonamento

Este é um projeto para a disciplina de Sistemas Operacionais do curso de Ciência da Computação do Instituto Federal de Santa Catarina (IFSC) - Câmpus Lages. O objetivo é desenvolver um software que simula diferentes algoritmos de escalonamento para obter informações estatísticas e análises de viabilidade.

## ⚙️ Configuração da Simulação

O simulador deve ser capaz de ler um arquivo de configuração no formato JSON. Abaixo está um exemplo da estrutura do arquivo:

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

## 💻 Módulos de Simulação

### Algoritmos de Escalonamento Tradicionais

#### FCFS (First Come First Served) e SJF (Shortest Job First)

Para os algoritmos FCFS e SJF, o simulador deve:

  * Descrever a sequência de execução das tarefas.
  * Calcular o nível de utilização do sistema.
  * Calcular o *turnaround time* médio de cada tarefa $TAT\{avg}.
  * Calcular o *turnaround time* médio do sistema TAT\_{avg}.
  * Calcular o *waiting time* médio de cada tarefa $WT\_{avg}.
  * Calcular o *waiting time* médio do sistema $WT\_{avg}.
  * Apontar as tarefas com maior e menor *waiting time* médio.
  * Apontar se alguma tarefa sofre *starvation*.

#### RR (Round Robin) e SRTF (Shortest Remaining Time First)

Para os algoritmos RR e SRTF, o simulador deve:

  * Descrever a sequência de execução das tarefas.
  * Calcular o nível de utilização do sistema.
  * Calcular o *turnaround time* médio de cada tarefa TAT\_{avg}.
  * Calcular o *turnaround time* médio do sistema TAT\_{avg}.
  * Calcular o *waiting time* médio de cada tarefa WT\_{avg}.
  * Calcular o *waiting time* médio do sistema WT\_{avg}.
  * Apontar as tarefas com maior e menor *waiting time* médio.
  * Apontar se alguma tarefa sofre *starvation*.
  * Apontar as tarefas envolvidas e o momento, caso ocorra inversão de prioridade.

### Algoritmos de Escalonamento de Tempo Real

#### RM (Rate Monotonic) e EDF (Earliest Deadline First)

Para os algoritmos RM e EDF, o simulador deve:

  * Realizar e apresentar o resultado do teste de escalonabilidade.
  * Descrever a sequência de execução das tarefas, independentemente do resultado do teste.
  * Apontar qual tarefa perdeu o *deadline*, em que momento e com qual frequência, caso ocorra.
  * Calcular o nível de utilização do sistema.
  * Calcular o *turnaround time* médio de cada tarefa TAT\_{avg}.
  * Calcular o *turnaround time* médio do sistema TAT\_{avg}.
  * Calcular o *waiting time* médio de cada tarefa WT\_{avg}.
  * Calcular o *waiting time* médio do sistema WT\_{avg}.
  * Apontar as tarefas com maior e menor *waiting time* médio.
  * Apontar se alguma tarefa sofre *starvation*.
  * Apontar as tarefas envolvidas e o momento, caso ocorra inversão de prioridade.

## ℹ️ Instruções Gerais e Avaliação

  * O desenvolvimento pode ser realizado em dupla.
  * A linguagem de programação é de livre escolha.
  * A nota será individual e baseada na apresentação em sala de aula.

No momento da apresentação, será solicitado:

  * A abertura e explicação do código fonte.
  * A execução e demonstração dos algoritmos com um arquivo de configuração fornecido pelo professor.
