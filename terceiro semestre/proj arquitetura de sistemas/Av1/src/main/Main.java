package main;

import controller.FilmeController;
import controller.ClienteController;
import model.Genero;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FilmeController filmeCtrl = new FilmeController();
        ClienteController clienteCtrl = new ClienteController();

        int opcao;
        do {
            System.out.println("\n=== SISTEMA DE CINEMA ===");
            System.out.println("1 - Cadastrar Filme");
            System.out.println("2 - Listar Filmes");
            System.out.println("3 - Atualizar Filme");
            System.out.println("4 - Remover Filme");
            System.out.println("5 - Cadastrar Cliente");
            System.out.println("6 - Listar Clientes");
            System.out.println("7 - Atualizar Cliente");
            System.out.println("8 - Remover Cliente");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    System.out.print("ID do Filme: ");
                    int idFilme = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Duração (min): ");
                    int duracao = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Gênero: ");
                    String descGenero = scanner.nextLine();
                    Genero genero = new Genero(1, descGenero); // ID fixo para simplificar
                    filmeCtrl.criarFilme(idFilme, titulo, duracao, genero);
                    System.out.println("Filme cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println("\n--- LISTA DE FILMES ---");
                    filmeCtrl.listarFilmes();
                    break;

                case 3:
                    System.out.print("ID do Filme para atualizar: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Novo Título: ");
                    String novoTitulo = scanner.nextLine();
                    System.out.print("Nova Duração (min): ");
                    int novaDuracao = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Novo Gênero: ");
                    String novoGenero = scanner.nextLine();
                    Genero generoAtualizado = new Genero(1, novoGenero);
                    filmeCtrl.atualizarFilme(idAtualizar, novoTitulo, novaDuracao, generoAtualizado);
                    System.out.println("Filme atualizado com sucesso!");
                    break;

                case 4:
                    System.out.print("ID do Filme para remover: ");
                    int idRemover = scanner.nextInt();
                    filmeCtrl.removerFilme(idRemover);
                    System.out.println("Filme removido com sucesso!");
                    break;

                case 5:
                    System.out.print("CPF do Cliente: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Idade: ");
                    int idade = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("RG: ");
                    String rg = scanner.nextLine();
                    System.out.print("É estudante? (true/false): ");
                    boolean estudante = scanner.nextBoolean();
                    clienteCtrl.criarCliente(cpf, nome, idade, rg, estudante);
                    System.out.println("Cliente cadastrado com sucesso!");
                    break;

                case 6:
                    System.out.println("\n--- LISTA DE CLIENTES ---");
                    clienteCtrl.listarClientes();
                    break;

                case 7:
                    System.out.print("CPF do Cliente para atualizar: ");
                    String cpfAtualizar = scanner.nextLine();
                    System.out.print("Novo Nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Nova Idade: ");
                    int novaIdade = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Novo RG: ");
                    String novoRg = scanner.nextLine();
                    System.out.print("É estudante? (true/false): ");
                    boolean novoEstudante = scanner.nextBoolean();
                    clienteCtrl.atualizarCliente(cpfAtualizar, novoNome, novaIdade, novoRg, novoEstudante);
                    System.out.println("Cliente atualizado com sucesso!");
                    break;

                case 8:
                    System.out.print("CPF do Cliente para remover: ");
                    String cpfRemover = scanner.nextLine();
                    clienteCtrl.removerCliente(cpfRemover);
                    System.out.println("Cliente removido com sucesso!");
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }
}