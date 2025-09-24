public class Cachorro extends Animal {
    private boolean corre;

    public Cachorro (String nome, int idade, boolean corre) {
        super(nome,idade);
        this.corre = corre;
    }

    public void setCorre(boolean corre) {
        this.corre = corre;
    }

    public boolean getCorre() {
        return corre;
    }


    @Override
    public void emitirSom() {
        System.out.println(getNome() + " está latindo!");
    }
}