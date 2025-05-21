public class Produto{
    public String cod;
    public Double peso;
    public Double valor;

    public Produto(String cod, Double peso, Double valor){
        this.cod = cod;
        this.peso = peso;
        this.valor = valor;
    }

    public void mostrar(){
        System.out.println(cod);
        System.out.println(peso);
        System.out.println(valor);
    }
}