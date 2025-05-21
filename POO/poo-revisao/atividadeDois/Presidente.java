public class Presidente extends Funcionario{
    private double acoes;

    public Presidente (String nome, String cpf, int salario, double acoes) {
        super (nome,cpf,salario);
        this.acoes = acoes;
    }

    public double getAcoes() {
        return acoes;
    }
    public void setAcoes(double acoes) {
        this.acoes = acoes;
    }

    @Override
    public double bonus () {
        return getSalario() * 0.10;
    }
}
