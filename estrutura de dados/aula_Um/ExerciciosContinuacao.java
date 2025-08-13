import java.util.Random;
import java.util.Scanner;

public class ExerciciosContinuacao {
	public static void main(String[] args) {
		// 3) Vetor de 10 inteiros positivos aleatórios
		int[] vetor = new int[10];
		Random rand = new Random();
		for (int i = 0; i < vetor.length; i++) {
			vetor[i] = rand.nextInt(100) + 1; // 1 a 100
		}
		System.out.print("Vetor gerado: ");
		for (int v : vetor) {
			System.out.print(v + " ");
		}
		System.out.println();

		// Solicitar valor para busca
		Scanner sc = new Scanner(System.in);
		System.out.print("Digite um valor inteiro positivo para buscar: ");
		int valorBusca = sc.nextInt();
		boolean encontrado = false;
		for (int i = 0; i < vetor.length; i++) {
			if (vetor[i] == valorBusca) {
				System.out.println("Valor encontrado no índice: " + i);
				encontrado = true;
				break;
			}
		}
		if (!encontrado) {
			System.out.println("Elemento não existe no vetor.");
		}

		sc.close();

		// 4) Encontrar menor e maior número do vetor
		int menor = encontrarMenor(vetor);
		int maior = encontrarMaior(vetor);
		System.out.println("Menor valor do vetor: " + menor);
		System.out.println("Maior valor do vetor: " + maior);
	}

	// Método para encontrar o menor valor
	public static int encontrarMenor(int[] vetor) {
		int menor = vetor[0];
		for (int i = 1; i < vetor.length; i++) {
			if (vetor[i] < menor) menor = vetor[i];
		}
		return menor;
	}

	// Método para encontrar o maior valor
	public static int encontrarMaior(int[] vetor) {
		int maior = vetor[0];
		for (int i = 1; i < vetor.length; i++) {
			if (vetor[i] > maior) maior = vetor[i];
		}
		return maior;
	}
}
