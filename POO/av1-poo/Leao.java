public class Leao extends Animal implements Som {
    public Leao(String nome, int idade) {
        super(nome, idade);
    }

    @Override
    public void emitirSom() {
        System.out.println("O leão rugiu!");
    }
}