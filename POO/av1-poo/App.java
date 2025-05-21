public class App {
    public static void main(String[] args) {
        Zoologico zoo = new Zoologico();

        zoo.adicionarAnimal(new Cachorro("Rex", 5));
        zoo.adicionarAnimal(new Cachorro("Bela", 3));
        zoo.adicionarAnimal(new Gato("Tom", 4));
        zoo.adicionarAnimal(new Gato("Lili", 2));
        zoo.adicionarAnimal(new Leao("Simba", 6));

        zoo.listarAnimais();
        System.out.println();
        zoo.emitirSons();
    }
}