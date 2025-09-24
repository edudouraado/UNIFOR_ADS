import java.util.ArrayList;

public class Venda {
    private ArrayList<Item_venda> itens;
    private Double total; 
    private String dataVenda;


    public Venda(String dataVenda) {
        this.dataVenda = dataVenda;
        this.itens = new ArrayList<Item_venda>();
        this.total = 0.0;
    }

    public double getTotal() {
        total = 0;
        for (Item_venda item : itens) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void criarItemVenda(Produto produto, int quantidade) {
        Item_venda item = new Item_venda(produto, quantidade);
        itens.add(item);
    }
   
}