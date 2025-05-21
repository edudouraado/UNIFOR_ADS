public class Professor {
    private String nome;
    private String identificacaoProfessor;
    private String disciplina;
    private int anoExperiencia;
    private int salario;


    public Professor (String nome, String identificacaoProfessor, String disciplina, int anoExperiencia, int salario){
        this.nome = nome;
        this.identificacaoProfessor = identificacaoProfessor;
        this.disciplina = disciplina;
        this.anoExperiencia = anoExperiencia;
        this.salario = salario;
    }

    public String getNome () {
        return nome;
    }
    public void setNome (String nome){
        this.nome = nome;
    }

    public String getIdentificacaoProfessor() {
        return identificacaoProfessor;
    }
    public void setIdentificacaoProfessor(String identificacaoProfessor) {
        this.identificacaoProfessor = identificacaoProfessor;
    }

    public String getDisciplina() {
        return disciplina;
    }
    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }
    
    public int getAnoExperiencia() {
        return anoExperiencia;
    }
    public void setAnoExperiencia(int anoExperiencia) {
        if (anoExperiencia > 0) {
            this.anoExperiencia = anoExperiencia;
        } else {
            System.out.println("Ano de experiência inválido.");
        }
    }

    public int getSalario() {
        return salario;
    }
    public void setSalario(int salario) {
        if (salario > 0) {
            this.salario = salario;
        } else {
            System.out.println("Salário inválido.");
        }
    }


    public void aumentarSalario(int aumento) {
        if (aumento > 0) {
                salario += aumento;
        } else {
            System.out.println("Aumento inválido.");
        }
    }

    public void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("Identificação: " + identificacaoProfessor);
        System.out.println("Disciplina: " + disciplina);
        System.out.println("Ano de experiência: " + anoExperiencia);
        System.out.println("Salário: " + salario);
    }
}
