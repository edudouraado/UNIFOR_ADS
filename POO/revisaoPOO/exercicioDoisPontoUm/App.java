public class App {
	public static void main (String [] args) throws Exception {
		Conta c1 = new Conta ("123456789", "12345", 500, false);

        c1.depositar(0);
		c1.mostrar();
	}
}