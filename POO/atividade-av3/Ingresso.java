public class Ingresso {
    private int idIngresso;
    private double valor;
    private boolean meiaEntrada;
    private boolean gratuidade;
    private boolean utilizado;
    private Sessao sessao;
    private Cliente cliente;

    public Ingresso(int idIngresso, double valor, boolean meiaEntrada, boolean gratuidade, boolean utilizado, Sessao sessao, Cliente cliente) {
        this.idIngresso = idIngresso;
        this.valor = valor;
        this.meiaEntrada = meiaEntrada;
        this.gratuidade = gratuidade;
        this.utilizado = utilizado;
        this.sessao = sessao;
        this.cliente = cliente;
    }

    public int getIdIngresso() { return idIngresso; }
    public void setIdIngresso(int idIngresso) { this.idIngresso = idIngresso; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public boolean isMeiaEntrada() { return meiaEntrada; }
    public void setMeiaEntrada(boolean meiaEntrada) { this.meiaEntrada = meiaEntrada; }

    public boolean isGratuidade() { return gratuidade; }
    public void setGratuidade(boolean gratuidade) { this.gratuidade = gratuidade; }

    public boolean isUtilizado() { return utilizado; }
    public void setUtilizado(boolean utilizado) { this.utilizado = utilizado; }

    public Sessao getSessao() { return sessao; }
    public void setSessao(Sessao sessao) { this.sessao = sessao; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public String toString() {
        return "Ingresso{" + "id=" + idIngresso + ", valor=" + valor + ", meia=" + meiaEntrada + ", gratuidade=" + gratuidade + ", utilizado=" + utilizado + ", cliente=" + cliente.getNome() + ", sessao=" + sessao.getFilme().getTitulo() + "}";
    }

    public void mostrar() {
        System.out.println(toString());
    }
}