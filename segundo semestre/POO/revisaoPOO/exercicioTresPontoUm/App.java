public class App {
	public static void main (String [] args) throws Exception {
		Empregado e1 = new Empregado ("Eduardo", "Dourado", 1200);
		Empregado e2 = new Empregado ("Eliarlan", "Dourado", 10000);

		System.out.println("Empregado 1");
		e1.mostrar();

		System.out.println(" ");

		System.out.println("Empregado 2");
		e2.mostrar();
	
	}
}