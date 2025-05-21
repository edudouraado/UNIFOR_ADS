import java.util.ArrayList;

public class Zoologico {
    private ArrayList<Animal> animais;

    public Zoologico() {
        animais = new ArrayList<>();
    }

    public void adicionarAnimal(Animal a) {
        animais.add(a);
    }

    public void listarAnimais() {
        for (Animal a : animais) {
            a.mostrar();
        }
    }

    public void emitirSons() {
        for (Animal a : animais) {
            if (a instanceof Som) {
                ((Som) a).emitirSom();
            }
        }
    }
}