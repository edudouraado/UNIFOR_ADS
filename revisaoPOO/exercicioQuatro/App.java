public class App {
    public static void main (String [] args) throws Exception {
        Estudante e1 = new Estudante("João", "123", 20, "Engenharia", 8.5);
        Estudante e2 = new Estudante("Maria", "456", 21, "Medicina", 9.0);
        Estudante e3 = new Estudante("José", "789", 19, "Direito", 7.5);


        Professor p1 = new Professor("Carlos", "321", "Matemática", 10, 5000);
        Professor p2 = new Professor("Ana", "654", "Português", 5, 3000);
        Professor p3 = new Professor("Marta", "987", "História", 3, 2500);


        Turma t1 = new Turma("Turma 1", 30, "Carlos", 8.0, 40);
        Turma t2 = new Turma("Turma 2", 25, "Ana", 10.0, 35);
        Turma t3 = new Turma("Turma 3", 20, "Marta", 14.0, 30);

        e1.exibirInformacoes();
        System.out.println(" ");
        e2.exibirInformacoes();
        System.out.println(" ");
        e3.exibirInformacoes();
        System.out.println("--------------------");

        p1.exibirInformacoes();
        System.out.println(" ");
        p2.exibirInformacoes();
        System.out.println(" ");
        p3.exibirInformacoes();
        System.out.println("--------------------");

        t1.exibirInformacoes();
        System.out.println(" ");
        t2.exibirInformacoes();
        System.out.println(" ");
        t3.exibirInformacoes();
        System.out.println(" ");
    }
}
