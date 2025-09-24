public class Turma {
    private String nomeTurma;
    private int numeroAlunos;
    private String professorResponsavel;
    private double horarioAula;
    private int listaAlunos;

    public Turma (String nomeTurma, int numeroAlunos, String professorResponsavel, double horarioAula, double listaAlunos){
        this.nomeTurma = nomeTurma;
        this.numeroAlunos = numeroAlunos;
        this.professorResponsavel = professorResponsavel;
        this.horarioAula = horarioAula;
        this.listaAlunos = (int) listaAlunos;
    }


    public String getNomeTurma () {
        return nomeTurma;
    }
    public void setNomeTurma (String nomeTurma){
        this.nomeTurma = nomeTurma;
    }

    public int getNumeroAlunos() {
        return numeroAlunos;
    }
    public void setNumeroAlunos(int numeroAlunos) {
        if (numeroAlunos > 0) {
            this.numeroAlunos = numeroAlunos;
        } else {
            System.out.println("Número de alunos inválido.");
        }
    }

    public String getProfessorResponsavel() {
        return professorResponsavel;
    }
    public void setProfessorResponsavel(String professorResponsavel) {
        this.professorResponsavel = professorResponsavel;
    }

    public double getHorarioAula() {
        return horarioAula;
    }
    public void setHorarioAula(double horarioAula) {
        if (horarioAula > 0) {
            this.horarioAula = horarioAula;
        } else {
            System.out.println("Horário de aula inválido.");
        }
    }

    public int getListaAlunos() {
        return listaAlunos;
    }
    public void setListaAlunos(double listaAlunos) {
        if (listaAlunos > 0) {
            this.listaAlunos = (int) listaAlunos;
        } else {
            System.out.println("Lista de alunos inválida.");
        }
    }

    public void adicionarAluno (Estudante estudante) {
        if (numeroAlunos < listaAlunos) {
            System.out.println("Aluno adicionado com sucesso.");
            numeroAlunos++;
        } else {
            System.out.println("Lista de alunos cheia.");
        }
    }

    public void removerAluno (Estudante estudante) {
        if (numeroAlunos > 0) {
            System.out.println("Aluno removido com sucesso.");
            numeroAlunos--;
        } else {
            System.out.println("Lista de alunos vazia.");
        }
    }

    public void exibirInformacoes() {
        System.out.println("Lista de alunos:");
        System.out.println("Nome da turma: " + getNomeTurma());
        System.out.println("Número de Alunos: " + getNumeroAlunos());
        System.out.println("Professor Responsável: " + getProfessorResponsavel());
        System.out.println("Hoarário de Aula: " + getHorarioAula());
        System.out.println("Lista de Alunos: " + getListaAlunos());
    }
}
