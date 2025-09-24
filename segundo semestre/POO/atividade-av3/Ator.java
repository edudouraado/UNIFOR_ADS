public class Ator extends Pessoa {
    private int registroProfissional;

    public Ator(String cpf, String nome, int idade, int registroProfissional) {
        super(cpf, nome, idade);
        this.registroProfissional = registroProfissional;
    }

    public int getRegistroProfissional() { return registroProfissional; }
    public void setRegistroProfissional(int registroProfissional) { this.registroProfissional = registroProfissional; }

    public String toString() {
        return "Ator{" + "cpf='" + cpf + "', nome='" + nome + "', idade=" + idade + ", registro=" + registroProfissional + "}";
    }

    public void mostrar() {
        System.out.println(toString());
    }
}