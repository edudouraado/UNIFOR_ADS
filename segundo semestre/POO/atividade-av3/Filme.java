public class Filme {
    private int idFilme;
    private String titulo;
    private int duracao;
    private Genero genero;

    public Filme(int idFilme, String titulo, int duracao, Genero genero) {
        this.idFilme = idFilme;
        this.titulo = titulo;
        this.duracao = duracao;
        this.genero = genero;
    }

    public int getIdFilme() { return idFilme; }
    public void setIdFilme(int idFilme) { this.idFilme = idFilme; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public int getDuracao() { return duracao; }
    public void setDuracao(int duracao) { this.duracao = duracao; }

    public Genero getGenero() { return genero; }
    public void setGenero(Genero genero) { this.genero = genero; }

    public String toString() {
        return "Filme{" + "id=" + idFilme + ", titulo='" + titulo + "', duracao=" + duracao + ", genero=" + genero.getDescGenero() + "}";
    }

    public void mostrar() {
        System.out.println(toString());
    }
}