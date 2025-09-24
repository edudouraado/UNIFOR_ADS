public class ExerciciosEstruturas {
    public static void main(String[] args) {
        // 1) Vetor de inteiros de 10 posições
        int[] vetor = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        System.out.println("Vetor de inteiros:");
        for (int i = 0; i < vetor.length; i++) {
            System.out.print(vetor[i] + " ");
        }
        System.out.println("\n");

        // 2) Matriz de caracteres 4x4
        char[][] matriz = {
            {'a', 'b', 'c', 'd'},
            {'e', 'f', 'g', 'h'},
            {'i', 'j', 'k', 'l'},
            {'m', 'n', 'o', 'p'}
        };
        System.out.println("Matriz de caracteres:");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}