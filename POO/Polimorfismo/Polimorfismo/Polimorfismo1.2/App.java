public class App {
    public static void main(String[] args) {
        Animal[] animais = new Animal[4];

        animais[0] = new Cachorro("Rex", 5, true);
        animais[1] = new Cachorro("Buddy", 3, false);
        animais[2] = new Preguica("Sid", 10, true);
        animais[3] = new Preguica("Luna", 8, false);

        for (Animal animal : animais) {
            animal.emitirSom();

            if (animal instanceof Cachorro) {
                System.out.println(animal.getNome() + " é um cachorro!");
            } else if (animal instanceof Preguica) {
                System.out.println(animal.getNome() + " é uma preguiça!");
            }
        }

        int somaIdades = 0;
        for (Animal animal : animais) {
            somaIdades += animal.getIdade();
        }

        double mediaIdade = (double) somaIdades / animais.length;
        System.out.println("Média da idade dos animais: " + mediaIdade);
    }
}