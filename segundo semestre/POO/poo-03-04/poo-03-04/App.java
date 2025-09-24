public class App {
    public static void main(String[] args) throws Exception {
        Produto p = new Produto("0", 9.0, 50.0);
        Produto p1 = new Produto("1", 11.0, 49.0);
        Produto p2 = new Produto("2", 9.0, 51.0);
        Produto p3 = new Produto("3", 20.0, 40.0);
        Produto p4 = new Produto("4", 30.0, 0.0);

        Carrinho c = new Carrinho(5);
        c.adicionarProduto(p);
        c.adicionarProduto(p2);
        c.adicionarProduto(p4);
        c.adicionarProduto(p);
        c.adicionarProduto(p);
        c.adicionarProduto(p);
        
        c.listarProdutos();

        System.out.println(c.qtdPesoMaior10ValorMenor50());
    }
}