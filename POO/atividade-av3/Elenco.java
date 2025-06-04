public class Elenco {
    private int idElenco;
    private Ator ator;
    private Filme filme;
    private boolean atorPrincipal;

    public Elenco(int idElenco, Ator ator, Filme filme, boolean atorPrincipal) {
        this.idElenco = idElenco;
        this.ator = ator;
        this.filme = filme;
        this.atorPrincipal = atorPrincipal;
    }

    public int getIdElenco() { return idElenco; }
    public void setIdElenco(int idElenco) { this.idElenco = idElenco; }

    public Ator getAtor() { return ator; }
    public void setAtor(Ator ator) { this.ator = ator; }

    public Filme getFilme() { return filme; }
    public void setFilme(Filme filme) { this.filme = filme; }

    public boolean isAtorPrincipal() { return atorPrincipal; }
    public void setAtorPrincipal(boolean atorPrincipal) { this.atorPrincipal = atorPrincipal; }

    public String toString() {
        return "Elenco{" + "id=" + idElenco + ", ator=" + ator.getNome() + ", filme=" + filme.getTitulo() + ", principal=" + atorPrincipal + "}";
    }

    public void mostrar() {
        System.out.println(toString());
    }
}