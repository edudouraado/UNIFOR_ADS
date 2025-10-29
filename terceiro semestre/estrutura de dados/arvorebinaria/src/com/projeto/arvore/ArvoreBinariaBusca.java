package arvorebinaria.src.com.projeto.arvore;

public class ArvoreBinariaBusca {

    No raiz;

    public ArvoreBinariaBusca() {
        this.raiz = null;
    }

    public void inserir(int chave) {
        this.raiz = inserirRecursivo(this.raiz, chave);
    }

    private No inserirRecursivo(No noAtual, int chave) {

        if (noAtual == null) {
            System.out.println("DEBUG: Nó folha encontrado. Inserindo " + chave);
            return new No(chave);
        }

        if (chave < noAtual.chave) {
            System.out.println("DEBUG: " + chave + " < " + noAtual.chave + ". Indo para a ESQUERDA.");
            noAtual.esquerda = inserirRecursivo(noAtual.esquerda, chave);
        } else if (chave > noAtual.chave) {

            System.out.println("DEBUG: " + chave + " > " + noAtual.chave + ". Indo para a DIREITA.");
            noAtual.direita = inserirRecursivo(noAtual.direita, chave);
        }

        return noAtual;
    }

    public void percorrerEmOrdem() {
        System.out.println("\n--- Percurso Em Ordem (Resultado Ordenado) ---");
        percorrerEmOrdemRecursivo(this.raiz);
        System.out.println(); 
    }

    private void percorrerEmOrdemRecursivo(No noAtual) {
        if (noAtual != null) {

            percorrerEmOrdemRecursivo(noAtual.esquerda);

            System.out.print(noAtual.chave + " ");

            percorrerEmOrdemRecursivo(noAtual.direita);
        }
    }
}