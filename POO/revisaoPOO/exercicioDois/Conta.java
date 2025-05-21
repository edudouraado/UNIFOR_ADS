public class Conta {
	public String cpf;
	public String numeroConta;
	public int saldo;


	public Conta (String cpf, String numeroConta, int saldo) {
		this.cpf = cpf;
		this.numeroConta = numeroConta;
		this.saldo = saldo;
	}


	public int calcularBonificacao() {
		return (int) (saldo * 0.10);
	}
	
	
	public int sacar(int valor) {
		return saldo - valor;
	}

	public int depositar(int valor) {
		return saldo + valor;
	}


	public void mostrar() {
		System.out.println("CPF: " + cpf);
		System.out.println("Número da Conta: " + numeroConta);
		System.out.println("Saldo: " + saldo);
		System.out.println("Valor da Bonificação: " + calcularBonificacao());
		System.out.println("Saldo após Saque: " + sacar(100));
		System.out.println("Saldo após Depósito: " + depositar(400));
	}
}