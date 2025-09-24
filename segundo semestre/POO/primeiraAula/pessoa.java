public class Pessoa {
	public String nome;
	public int anoNascimento;
	public double peso;
	public double altura;


	public Pessoa (String nome, int anoNascimento, double peso, double altura) {
		this.nome = nome;
		this.anoNascimento = anoNascimento;
		this.peso = peso;
		this.altura = altura;
	}

	
	public double calcularIMC () {
		return peso / (altura * altura);
	}

	
	public int calcularIdade () {
		int anoAtual = 2025;
		return anoAtual - anoNascimento;
	}

	
	public void mostrar() {
		System.out.println("Nome: " + nome);
		System.out.println("Idade: " + calcularIdade());
		System.out.println("Peso: " + peso);
		System.out.println("Altura: " + altura);
		System.out.println("IMC: " + calcularIMC());
    }
}