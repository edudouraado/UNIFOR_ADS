public class Preguica extends Animal {
    private boolean escala;

    public Preguica(String nome, int idade, boolean escala) {
        super(nome, idade);
        this.escala = escala;
    }

    public boolean getEscala() {
        return escala;
    }

    public void setEscala(boolean escala) {
        this.escala = escala;
    }

    @Override
    public void emitirSom() {
        System.out.println(getNome() + " emite um som bem lento... zzz...");
    }
}