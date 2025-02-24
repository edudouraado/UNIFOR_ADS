package primeiraAula;

class main {
    public static void main(String[] args) {
        pessoa p1 = new pessoa("Eduardo Dourado", 2000, 78, 1.73);
        pessoa p2 = new pessoa("Ana Kelly Moura", 2003, 70, 1.70);

        System.out.println("=== Pessoa 1 ===");
        p1.mostra();

        System.out.println("\n=== Pessoa 2 ===");
        p2.mostra();

        // Comparação de objetos
        if (p1.getIMC() > p2.getIMC()) {
            System.out.println("\n" + p1.getNome() + " tem um IMC maior que " + p2.getNome());
        } else if (p1.getIMC() < p2.getIMC()) {
            System.out.println("\n" + p2.getNome() + " tem um IMC maior que " + p1.getNome());
        } else {
            System.out.println("\nAmbos têm o mesmo IMC.");
        }
    }

    @Override
    public String toString() {
        return "main []";
    }
}
