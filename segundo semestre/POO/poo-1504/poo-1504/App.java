public class App {
    public static void main(String[] args) {
        Funcionario f1 = new Funcionario("Eduardo","123",1200);
        Presidente p1 = new Presidente("Kennedy", "987", 1000000, 10);
    
    f1.mostrar();
    System.out.println( " ");
    p1.mostrar();
    }
}
