public class Conta {
	public String cpf;
	public String numeroConta;
	public int saldo;
	public boolean clienteEspecial;


	public Conta (String cpf, String numeroConta, int saldo, boolean clienteEspecial) {
		this.cpf = cpf;
		this.numeroConta = numeroConta;
		if (saldo > 0) {
			this.saldo = saldo;
		} else {
			this.saldo = 0;
		}
	}


	public int calcularBonificacao() {
		if (clienteEspecial == true) {
            System.out.println("Cliente Especial");
			return (int) (saldo * 0.05);
		} else {
            System.out.println("Cliente Normal");
			return (int) (saldo * 0.10);
		}
	}
	
	
	public int sacar(int valor) {
		return saldo -= valor;
	}

	public int depositar(int valor) {
		return saldo += valor;
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