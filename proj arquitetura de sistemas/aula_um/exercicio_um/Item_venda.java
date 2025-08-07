public class Item_venda {
    private int quantidade;
    private Produto produto;
    private Double subtotal;

    public Item_venda(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Double getSubtotal() {
        return produto.getPreco() * quantidade;
    }
    public Produto getProduto() {
        return produto;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double calcularSubtotal() {
        subtotal = 0.0;
        subtotal = produto.getPreco() * quantidade;
        return subtotal;
    }
}