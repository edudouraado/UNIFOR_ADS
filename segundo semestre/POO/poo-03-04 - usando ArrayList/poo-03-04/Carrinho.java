import java.util.ArrayList;

public class Carrinho {
    public ArrayList<Produto> produtos;
    public int limiteCarrinho;

    public Carrinho(int qtd) {
        this.produtos = new ArrayList<>();
        this.limiteCarrinho = qtd;
    }

    public int totalPreenchido() {
        return produtos.size();
    }

    public void adicionarProduto(Produto p) {
        if (produtos.size() < this.limiteCarrinho) {
            produtos.add(p);
        } else {
            System.out.println("Carrinho cheio");
        }
    }

    public void listarProdutos() {
        for (Produto produto: produtos) {
            produto.mostrar();
        }
    }

    public int qtdPesoMaior10ValorMenor50() {
        int retorno = 0;
        for (Produto produto: produtos) {
            if (produto.peso > 10 && produto.valor < 50) {
                retorno++;
                produto.mostrar();
            }
        }
        return retorno;
    }
    
}