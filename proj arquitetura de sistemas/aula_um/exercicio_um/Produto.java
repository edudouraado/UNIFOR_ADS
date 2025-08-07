public class Produto {
    private String descricao;
    private double preco_unitario;

    public Produto(String descricao, double preco_unitario) {
        this.descricao = descricao;
        this.preco_unitario = preco_unitario;
    }
    public Double getPreco() {
        return preco_unitario;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setPreco_unitario(double preco_unitario) {
        this.preco_unitario = preco_unitario;
    }

    

}
