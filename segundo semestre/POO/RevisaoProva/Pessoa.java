public class Pessoa {
	public String nome;
	public String cpf;
	public double altura;
	public double peso;
	

	public Pessoa (String nome, String cpf, double altura, double peso) {
		this.nome = nome;
		this.cpf = cpf;
		this.altura = altura;
		this.peso = peso;
	}

	public int calcularIMC(){
		return (int) ((int) peso / (altura * altura));
	}

	
	public void mostrar() {
		System.out.println("Nome: " + nome);
		System.out.println("CPF: " + cpf);
		System.out.println("Altura: " + altura + " metros");
		System.out.println("Peso: " + peso + " kg");
		System.out.println("IMC: " + calcularIMC());
	}
}