public class Veiculo {
	private String marca;
	private String modelo;

	public Veiculo (String marca, String modelo){
		this.marca = marca;
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void mover(){
		System.out.println("veículo se movendo...");
	}
}


public class Carro extends Veiculo{
	private int quantidadeDePortas;
	
	public Carro (String marca, String modelo, int quantidadeDePortas) {
		super(marca,modelo);
		this.quantidadeDePortas = quantidadeDePortas;
	}

	public int getQuantidadeDePortas() {
		return quantidadeDePortas;
	}

	public void setQuantidadeDePortas(int quantidadeDePortas) {
		this.quantidadeDePortas = quantidadeDePortas;
	}

	@Override
	public void mover(){
		System.out.println("Carro andando na estrada");
	}
}

public class Bicicleta extends Veiculo{
	private String tipo;

	public Bicicleta(String marca, String modelo, String tipo) {
		super(marca,modelo);
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public void mover() {
		System.out.println("Bicicleta pedalando");
	}
}

public class App {
	public static void main (String [] args) throws Exception {
		Carro carro1 = new Carro("Chevrolet", "Onix", 4);
		Bicicleta bicicleta1 = new Bicicleta("Monark", "1980", "urbana");

		Veiculo v1 = new Bicicleta("BMX","street","urbana");
		Veiculo v2 = new Carro("Ford","F1",2);

		carro1.mover();
		bicicleta1.mover();
		v1.mover();
		v2.mover();
	}
}