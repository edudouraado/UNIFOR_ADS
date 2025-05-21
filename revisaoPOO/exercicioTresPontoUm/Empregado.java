public class Empregado {
	private String nome;
	private String sobrenome;
	private int salarioMensal;

	public Empregado (String nome, String sobrenome, int salarioMensal){
		this.nome = nome;
		this.sobrenome = sobrenome;
		if (salarioMensal > 0) {
			this.salarioMensal = salarioMensal;
		} else {
			salarioMensal = 0;
		}
	}

	
	public String getNome () {
		return nome;
	}
	public void setNome (String nome) {
		this.nome = nome;
	}

	public String getSobrenome () {
		return sobrenome;
	}
	public void setSobrenome (String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public int getSalarioMensal () {
		return salarioMensal;
	}
	public void setSalarioMensal (int salarioMensal) {
		if (salarioMensal > 0) {
			this.salarioMensal = salarioMensal;
		} else {
			salarioMensal = 0;
		}
	}

	

	public int calcularSalarioAnual (int salarioMensal) {
		return salarioMensal * 12;
	}

	
	public int calcularBonificacao () {
		return (int) ((int) getSalarioMensal() + (getSalarioMensal() * 0.10));
	}


	public void mostrar () {
		System.out.println("Nome: " + getNome() + " " + getSobrenome());
		System.out.println("Salário Mensal: " + getSalarioMensal());
		System.out.println("Salário Anual: "+ calcularSalarioAnual(salarioMensal));
		System.out.println("Valor do acréscimo de 10% (anual): " + calcularBonificacao());
	}
}