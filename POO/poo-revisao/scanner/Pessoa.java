public class Pessoa{
	public int idade;
	public String nome;
	
    public Pessoa(String nome, int idade){
		this.nome = nome;
		this.idade = idade;
	}   
	// incluir método mostrar
    public void mostrar(){
        System.out.println("Olá " + this.nome + ", você tem " + this.idade + " anos");
    }
}
