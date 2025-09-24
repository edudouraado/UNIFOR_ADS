package model;

public class Cliente extends Pessoa {
    private String rg;
    private boolean estudante;

    public Cliente(String cpf, String nome, int idade, String rg, boolean estudante) {
        super(cpf, nome, idade);
        this.rg = rg;
        this.estudante = estudante;
    }

    public String getRg() { return rg; }
    public void setRg(String rg) { this.rg = rg; }

    public boolean isEstudante() { return estudante; }
    public void setEstudante(boolean estudante) { this.estudante = estudante; }

    public String toString() {
        return "Cliente{" + "cpf='" + cpf + "', nome='" + nome + "', idade=" + idade + ", rg='" + rg + "', estudante=" + estudante + "}";
    }

    public void mostrar() {
        System.out.println(toString());
    }
}