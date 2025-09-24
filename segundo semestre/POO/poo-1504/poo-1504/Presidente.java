public class Presidente extends Funcionario{
    private double acoes;

    public Presidente (String nome, String cpf, int salario, double acoes) {
        super(nome, cpf, salario);
        this.acoes = acoes;
    }

    public void setAcoes(double acoes) {
        this.acoes = acoes;
    }
    public double getAcoes(){
        return acoes;
    }

    @Override
    public double calcularBonus() {
        return getSalario() * 0.10;
    }

    @Override
    public void mostrar(){
        super.mostrar();
        System.out.println("Quantidade de ações: " + acoes + " %");
    }
}

