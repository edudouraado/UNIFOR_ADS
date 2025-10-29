package arvorebinaria.src.com.projeto.arvore;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando simulação de Indexação de Banco de Dados (BST)\n");

        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();

        int[] chaves = {50, 30, 70, 20, 40, 60, 80}; 

        System.out.println("--- Construção da Árvore (Inserção) ---");
        for (int chave : chaves) {
            System.out.println("Tentando inserir: " + chave);

            arvore.inserir(chave); 
            
            System.out.println("---------------------------------------");
        }

        arvore.percorrerEmOrdem(); 
        System.out.println("\nFim da simulação.");
    }
}