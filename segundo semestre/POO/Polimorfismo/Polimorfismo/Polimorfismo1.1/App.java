public class App {
    public static void main (String [] args) throws Exception {
        Cachorro c1 = new Cachorro ("sushi", 15, true);
        Preguica p1 = new Preguica ("Eli", 29, true);

        p1.mostrar();
        c1.mostrar();

        Animal a1 = new Cachorro("Athena", 5, false);
        Animal a2 = new Preguica("Kelly", 21, false);

        a1.emitirSom();
        a2.emitirSom();
    }
}