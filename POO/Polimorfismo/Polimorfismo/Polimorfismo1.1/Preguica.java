public class Preguica extends Animal {
    private boolean escala;

    public Preguica (String nome, int idade, boolean escala) {
        super(nome, escala);
        this.escala = escala;
    }

    public boolean getEscala() {
        return escala;
    }

    public void setEscala (booblean escala) {
        this.escala = escala;
    }

    @Override
    public void emitirSom() {
        System.out.println(getNome() + " emite um som bem lento... zzz...");
    }
}