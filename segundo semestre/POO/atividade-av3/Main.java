import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Cliente> listaClientes = new ArrayList<>();
        ArrayList<Ator> listaAtores = new ArrayList<>();

        while (true) {
            System.out.println("===== MENU =====");
            System.out.println("1 - Criar Cliente");
            System.out.println("2 - Criar Ator");
            System.out.println("3 - Listar Clientes");
            System.out.println("4 - Listar Atores");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            int opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            if (opcao == 0) {
                System.out.println("Encerrando...");
                break;
            }

            switch(opcao) {
                case 1:
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Idade: ");
                    int idade = sc.nextInt();
                    sc.nextLine();
                    System.out.print("RG: ");
                    String rg = sc.nextLine();
                    System.out.print("Estudante (true/false): ");
                    boolean estudante = sc.nextBoolean();
                    sc.nextLine();

                    Cliente cliente = new Cliente(cpf, nome, idade, rg, estudante);
                    listaClientes.add(cliente); // adiciona na lista
                    System.out.println("Cliente criado com sucesso!");
                    break;

                case 2:
                    System.out.print("CPF: ");
                    String cpfAtor = sc.nextLine();
                    System.out.print("Nome: ");
                    String nomeAtor = sc.nextLine();
                    System.out.print("Idade: ");
                    int idadeAtor = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Registro Profissional: ");
                    int registro = sc.nextInt();
                    sc.nextLine();

                    Ator ator = new Ator(cpfAtor, nomeAtor, idadeAtor, registro);
                    listaAtores.add(ator); // adiciona na lista
                    System.out.println("Ator criado com sucesso!");
                    break;

                case 3:
                    System.out.println("=== Lista de Clientes ===");
                    for (Cliente c : listaClientes) {
                        c.mostrar();
                        System.out.println("--------------------");
                    }
                    break;

                case 4:
                    System.out.println("=== Lista de Atores ===");
                    for (Ator a : listaAtores) {
                        a.mostrar();
                        System.out.println("--------------------");
                    }
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }
}
