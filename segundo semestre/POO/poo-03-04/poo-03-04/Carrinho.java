public class Carrinho{
    public Produto[] produtos;
    public int limiteCarrinho;

    public Carrinho(int qtd){
        this.produtos = new Produto[qtd];
        this.limiteCarrinho = qtd;
    }

    public int totalPreenchido(){
        int retorno = 0;
        for(int i=0;i<produtos.length;i++){
            if(produtos[i] != null){
                retorno++;
            }
        }
        return retorno;
    }

    public void adicionarProduto(Produto p){
        if(totalPreenchido()<this.limiteCarrinho){
            produtos[totalPreenchido()] = p;
        }else{
            System.out.println("Carrinho cheio");
        }
    }

    public void listarProdutos(){        
        for(int i=0;i<produtos.length;i++){
            if(produtos[i] != null){
                produtos[i].mostrar();
            }
        }
    }

    public int qtdPesoMaior10ValorMenor50(){
        int retorno=0;
        for(int i=0;i<produtos.length;i++){
            if(produtos[i].peso>10 && produtos[i].valor<50){
                retorno +=1;
                produtos[i].mostrar();
            }
        }
        return retorno;
    }

}
