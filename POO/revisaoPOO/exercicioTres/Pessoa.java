public class Pessoa {
	private String nome;
	private String cpf;
	private int anoNascimento;
	private double altura;

	
	public Pessoa (String nome, String cpf, int anoNascimento, double altura) {
		this.nome = nome;
		this.cpf = cpf;
		this.anoNascimento = anoNascimento;
		this.altura = altura;
	}


	public String getNome(){
		return nome;
	}

	public void setNome(String nome){
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf){
		this.cpf = cpf;
	}

	public int getAnoNascimento(){
		return anoNascimento;
	}

	public void setAnoNascimento(int anoNascimento){
		this.anoNascimento = anoNascimento;
	}

	public double getAltura() {
		return altura;
	}
	
	public void setAltura(double altura) {
		this.altura = altura;
	}


	public int calcularIdade(int anoNascimento){
		return (int) 2025 - anoNascimento;
	}


	public void mostrar() {
		System.out.println("Nome: " + getNome());
		System.out.println("CPF: " + getCpf());
		System.out.println("Idade: " + calcularIdade(anoNascimento) + " anos.");
		System.out.println("Altura: " + getAltura() + " metros.");
	}
}