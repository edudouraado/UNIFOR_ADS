import java.util.Scanner;
public class App {
	public static void main (String [] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite seu nome: " );
		String nome = sc.nextLine();
		System.out.println("Digite sua idade: ");
		int idade = sc.nextInt();
		System.out.println("Olá " + nome +", você tem " + idade + " anos");
		sc.close();
		Pessoa p = new Pessoa(nome, idade);
		p.mostrar();
	}
}  