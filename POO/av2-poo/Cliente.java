public class Cliente {
    private int id;
    private String nome;
    private int anoNascimento;
    private String telefone;

    public Cliente(int id, String nome, int anoNascimento, String telefone) {
        this.id = id;
        this.nome = nome;
        this.anoNascimento = anoNascimento;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", anoNascimento=" + anoNascimento +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
