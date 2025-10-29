package arvorebinaria.src.com.projeto.arvore;

public class No {
    int chave; 

    No esquerda;
    No direita;

    public No(int chave) {
        this.chave = chave;
        this.esquerda = null;
        this.direita = null;
    }
}