public class Sessao {
    private int idSessao;
    private String dataSessao;
    private String horaSessao;
    private int classificacao;
    private Filme filme;

    public Sessao(int idSessao, String dataSessao, String horaSessao, int classificacao, Filme filme) {
        this.idSessao = idSessao;
        this.dataSessao = dataSessao;
        this.horaSessao = horaSessao;
        this.classificacao = classificacao;
        this.filme = filme;
    }

    public int getIdSessao() { return idSessao; }
    public void setIdSessao(int idSessao) { this.idSessao = idSessao; }

    public String getDataSessao() { return dataSessao; }
    public void setDataSessao(String dataSessao) { this.dataSessao = dataSessao; }

    public String getHoraSessao() { return horaSessao; }
    public void setHoraSessao(String horaSessao) { this.horaSessao = horaSessao; }

    public int getClassificacao() { return classificacao; }
    public void setClassificacao(int classificacao) { this.classificacao = classificacao; }

    public Filme getFilme() { return filme; }
    public void setFilme(Filme filme) { this.filme = filme; }

    public String toString() {
        return "Sessao{" + "id=" + idSessao + ", data='" + dataSessao + "', hora='" + horaSessao + "', classificacao=" + classificacao + ", filme=" + filme.getTitulo() + "}";
    }

    public void mostrar() {
        System.out.println(toString());
    }
}