public class Funcionario {
    private String nome;
    private String cpf;
    private double salario;

    public Funcionario (String nome, String cpf, double salario) {
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome(){
        return nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getCpf(){
        return cpf;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    public double getSalario(){
        return salario;
    }

    public double calcularBonus() {
        return salario * 0.05;
    }

    public void mostrar(){
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Salário: " + salario);
        System.out.println("Bonus: " +calcularBonus());
    }
}