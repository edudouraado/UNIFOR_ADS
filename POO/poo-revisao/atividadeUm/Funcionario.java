public class Funcionario {
    private String nome;
    private String cpf;
    private int salario;

    public Funcionario(String nome, String cpf, int salario){
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
    }

    public String getNome (){
        return nome;
    }

    public String getCpf (){
        return cpf;
    }

    public int getSalario (){
        return salario;
    }

    public void setNome (String nome){
        this.nome = nome;
    }

    public void setCpf (String cpf){
        this.cpf = cpf;
    }

    public void setSalario (int salario){
        this.salario = salario;
    }

    public double calcularBonus () {
        return salario * 0.1;
    }

    public void mostrar(){
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Salario: " + salario);
        System.out.println("Bonus: " + calcularBonus());
    }
}