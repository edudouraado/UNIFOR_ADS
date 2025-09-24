package ui;

import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import modelo.*;
import excecoes.*;

/**
 * Classe principal responsável pela interface com o usuário (menu de console).
 */
public class CinemaUI {

    private static Scanner scanner = new Scanner(System.in);

    // Constantes para nomes de arquivos (referenciadas na classe modelo, mas úteis aqui para mensagens)
    private static final String ARQUIVO_ATORES = "data/atores.txt";
    private static final String ARQUIVO_CLIENTES = "data/clientes.txt";
    private static final String ARQUIVO_GENEROS = "data/generos.txt";
    private static final String ARQUIVO_FILMES = "data/filmes.txt";
    private static final String ARQUIVO_SESSOES = "data/sessoes.txt";
    private static final String ARQUIVO_INGRESSOS = "data/ingressos.txt";
    private static final String ARQUIVO_ELENCOS = "data/elencos.txt";

    public static void main(String[] args) {
        // Informa ao usuário onde os dados serão armazenados
        File dataDir = new File("data");
        System.out.println("INFO: Arquivos de dados serão lidos/gravados em: " + dataDir.getAbsolutePath());
        System.out.println("(Certifique-se de executar o programa a partir do diretório raiz do projeto, que contém as pastas 'src' e 'data')");

        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerOpcaoInt();

            try {
                processarOpcaoPrincipal(opcao);
            } catch (ValidacaoException | PersistenciaException | NaoEncontradoException | MenuOpcaoInvalidaException e) {
                System.err.println("\nErro: " + e.getMessage());
                if (e.getCause() != null) {
                     System.err.println("Causa: " + e.getCause().getMessage());
                }
            } catch (InputMismatchException e) {
                System.err.println("\nErro: Entrada inválida. Por favor, digite um número quando solicitado.");
                scanner.nextLine(); // Limpa o buffer do scanner
            } catch (Exception e) {
                // Captura genérica para erros inesperados
                System.err.println("\nOcorreu um erro inesperado: " + e.getMessage());
                e.printStackTrace(); // Ajuda a depurar
            }

            if (opcao != 0) {
                pressioneEnterParaContinuar();
            }

        } while (opcao != 0);

        System.out.println("\nSistema encerrado.");
        scanner.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n--- Sistema de Gerenciamento de Cinema ---");
        System.out.println("1. Gerenciar Atores");
        System.out.println("2. Gerenciar Clientes");
        System.out.println("3. Gerenciar Gêneros");
        System.out.println("4. Gerenciar Filmes");
        System.out.println("5. Gerenciar Sessões");
        System.out.println("6. Gerenciar Ingressos");
        System.out.println("7. Gerenciar Elencos");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    // Métodos auxiliares para leitura de tipos específicos
    private static int lerOpcaoInt() {
        int valor = scanner.nextInt();
        scanner.nextLine(); // Consome a nova linha
        return valor;
    }

     private static double lerDouble() {
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Consome a nova linha
        return valor;
    }

    private static boolean lerBoolean(String prompt) {
        String resposta;
        while (true) {
            System.out.print(prompt + " (s/n): ");
            resposta = scanner.nextLine().trim().toLowerCase();
            if (resposta.equals("s")) return true;
            if (resposta.equals("n")) return false;
            System.out.println("Resposta inválida. Digite 's' para sim ou 'n' para não.");
        }
    }

    private static void processarOpcaoPrincipal(int opcao) throws MenuOpcaoInvalidaException, PersistenciaException, ValidacaoException, NaoEncontradoException {
        switch (opcao) {
            case 1: gerenciarAtores(); break;
            case 2: gerenciarClientes(); break;
            case 3: gerenciarGeneros(); break;
            case 4: gerenciarFilmes(); break;
            case 5: gerenciarSessoes(); break;
            case 6: gerenciarIngressos(); break;
            case 7: gerenciarElencos(); break;
            case 0: break; // Sair
            default: throw new MenuOpcaoInvalidaException("Opção inválida no menu principal.");
        }
    }

    // --- Métodos de Gerenciamento --- 

    private static void exibirSubMenu(String entidade, boolean temMostrar) {
        System.out.println("\n--- Gerenciar " + entidade + " ---");
        System.out.println("1. Inserir");
        System.out.println("2. Editar");
        System.out.println("3. Listar");
        System.out.println("4. Consultar");
        if (temMostrar) {
             System.out.println("5. Mostrar Detalhes (após consultar)");
        }
        System.out.println("0. Voltar ao Menu Principal");
        System.out.print("Escolha uma opção: ");
    }

    // --- ATORES --- 
    private static void gerenciarAtores() throws PersistenciaException, ValidacaoException, NaoEncontradoException, MenuOpcaoInvalidaException {
        int opcao;
        Ator atorConsultado = null;

        do {
            exibirSubMenu("Atores", true);
            opcao = lerOpcaoInt();

            try {
                switch (opcao) {
                    case 1: // Inserir
                        System.out.println("\n-- Inserir Novo Ator --");
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine().trim();
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Idade: ");
                        int idade = lerOpcaoInt(); // Usar lerOpcaoInt para consumir newline
                        System.out.print("Registro Profissional: ");
                        int reg = lerOpcaoInt();

                        Ator novoAtor = new Ator(cpf, nome, idade, reg);
                        if (novoAtor.inserir()) {
                            System.out.println("Ator inserido com sucesso! Verifique o arquivo em " + new File(ARQUIVO_ATORES).getAbsolutePath());
                        }
                        atorConsultado = null;
                        break;
                    case 2: // Editar
                        System.out.println("\n-- Editar Ator --");
                        System.out.print("CPF do Ator a editar: ");
                        String cpfEditar = scanner.nextLine().trim();
                        if (cpfEditar.isEmpty()) {
                            System.out.println("CPF para edição não pode ser vazio.");
                            atorConsultado = null;
                            break;
                        }
                        Ator atorParaEditar = Ator.consultar(cpfEditar);
                        System.out.println("Editando Ator: " + atorParaEditar.getNome() + " (CPF: " + atorParaEditar.getCpf() + ")");
                        System.out.println("Digite os novos dados (deixe em branco para manter o atual):");

                        System.out.print("Novo Nome (" + atorParaEditar.getNome() + "): ");
                        String novoNome = scanner.nextLine();
                        if (!novoNome.trim().isEmpty()) atorParaEditar.setNome(novoNome);

                        System.out.print("Nova Idade (" + atorParaEditar.getIdade() + "): ");
                        String novaIdadeStr = scanner.nextLine();
                        if (!novaIdadeStr.trim().isEmpty()) {
                            try {
                                atorParaEditar.setIdade(Integer.parseInt(novaIdadeStr.trim()));
                            } catch (NumberFormatException e) {
                                System.err.println("Idade inválida, mantendo a anterior.");
                            }
                        }

                        System.out.print("Novo Registro Profissional (" + atorParaEditar.getRegistroProfissional() + "): ");
                        String novoRegStr = scanner.nextLine();
                        if (!novoRegStr.trim().isEmpty()) {
                            try {
                                atorParaEditar.setRegistroProfissional(Integer.parseInt(novoRegStr.trim()));
                            } catch (NumberFormatException e) {
                                System.err.println("Registro inválido, mantendo o anterior.");
                            }
                        }

                        if (atorParaEditar.editar()) {
                            System.out.println("Ator editado com sucesso!");
                        }
                        atorConsultado = null;
                        break;
                    case 3: // Listar
                        System.out.println("\n-- Lista de Atores --");
                        ArrayList<Ator> atores = Ator.listar();
                        if (atores.isEmpty()) {
                            System.out.println("Nenhum ator cadastrado ou arquivo '" + ARQUIVO_ATORES + "' não encontrado/vazio.");
                        } else {
                            for (Ator a : atores) {
                                System.out.println("CPF: " + a.getCpf() + ", Nome: " + a.getNome() + ", Registro: " + a.getRegistroProfissional());
                            }
                        }
                        atorConsultado = null;
                        break;
                    case 4: // Consultar
                        System.out.println("\n-- Consultar Ator --");
                        System.out.print("CPF do Ator: ");
                        String cpfConsultar = scanner.nextLine().trim();
                        if (cpfConsultar.isEmpty()) {
                            System.out.println("CPF para consulta não pode ser vazio.");
                            atorConsultado = null;
                        } else {
                            atorConsultado = Ator.consultar(cpfConsultar);
                            System.out.println("Ator encontrado: " + atorConsultado.getNome());
                            System.out.println("(Use a opção 5 para ver detalhes completos)");
                        }
                        break;
                    case 5: // Mostrar Detalhes
                        if (atorConsultado != null) {
                            atorConsultado.mostrar();
                        } else {
                            System.out.println("Nenhum ator consultado recentemente. Use a opção 4 primeiro.");
                        }
                        break;
                    case 0: // Voltar
                        break;
                    default: throw new MenuOpcaoInvalidaException("Opção inválida no menu de Atores.");
                }
            } catch (InputMismatchException e) {
                 System.err.println("\nErro: Entrada inválida para número. Tente novamente.");
                 scanner.nextLine(); // Limpa o buffer
                 atorConsultado = null;
            } catch (NaoEncontradoException | ValidacaoException | PersistenciaException e) {
                 System.err.println("\nErro: " + e.getMessage());
                 atorConsultado = null;
            }

             // Pausa apenas se não for a opção de mostrar ou sair
             if (opcao != 0 && opcao != 5) {
                 pressioneEnterParaContinuar();
             }

        } while (opcao != 0);
    }

    // --- CLIENTES --- 
    private static void gerenciarClientes() throws PersistenciaException, ValidacaoException, NaoEncontradoException, MenuOpcaoInvalidaException {
        int opcao;
        Cliente clienteConsultado = null;

        do {
            exibirSubMenu("Clientes", true);
            opcao = lerOpcaoInt();

            try {
                switch (opcao) {
                    case 1: // Inserir
                        System.out.println("\n-- Inserir Novo Cliente --");
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine().trim();
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Idade: ");
                        int idade = lerOpcaoInt();
                        System.out.print("RG: ");
                        String rg = scanner.nextLine().trim();
                        boolean estudante = lerBoolean("É estudante?");

                        Cliente novoCliente = new Cliente(cpf, nome, idade, rg, estudante);
                        if (novoCliente.inserir()) {
                            System.out.println("Cliente inserido com sucesso! Verifique o arquivo em " + new File(ARQUIVO_CLIENTES).getAbsolutePath());
                        }
                        clienteConsultado = null;
                        break;
                    case 2: // Editar
                        System.out.println("\n-- Editar Cliente --");
                        System.out.print("CPF do Cliente a editar: ");
                        String cpfEditar = scanner.nextLine().trim();
                         if (cpfEditar.isEmpty()) {
                            System.out.println("CPF para edição não pode ser vazio.");
                            clienteConsultado = null;
                            break;
                        }
                        Cliente clienteParaEditar = Cliente.consultar(cpfEditar);
                        System.out.println("Editando Cliente: " + clienteParaEditar.getNome() + " (CPF: " + clienteParaEditar.getCpf() + ")");
                        System.out.println("Digite os novos dados (deixe em branco para manter o atual):");

                        System.out.print("Novo Nome (" + clienteParaEditar.getNome() + "): ");
                        String novoNome = scanner.nextLine();
                        if (!novoNome.trim().isEmpty()) clienteParaEditar.setNome(novoNome);

                        System.out.print("Nova Idade (" + clienteParaEditar.getIdade() + "): ");
                        String novaIdadeStr = scanner.nextLine();
                        if (!novaIdadeStr.trim().isEmpty()) {
                             try {
                                clienteParaEditar.setIdade(Integer.parseInt(novaIdadeStr.trim()));
                            } catch (NumberFormatException e) {
                                System.err.println("Idade inválida, mantendo a anterior.");
                            }
                        }

                        System.out.print("Novo RG (" + clienteParaEditar.getRg() + "): ");
                        String novoRg = scanner.nextLine();
                        if (!novoRg.trim().isEmpty()) clienteParaEditar.setRg(novoRg);

                        System.out.print("É estudante? (" + (clienteParaEditar.isEstudante() ? "s" : "n") + ") - Digite 's' ou 'n' ou deixe em branco: ");
                        String estudanteStr = scanner.nextLine().trim().toLowerCase();
                        if (estudanteStr.equals("s")) clienteParaEditar.setEstudante(true);
                        else if (estudanteStr.equals("n")) clienteParaEditar.setEstudante(false);

                        if (clienteParaEditar.editar()) {
                            System.out.println("Cliente editado com sucesso!");
                        }
                        clienteConsultado = null;
                        break;
                    case 3: // Listar
                        System.out.println("\n-- Lista de Clientes --");
                        ArrayList<Cliente> clientes = Cliente.listar();
                        if (clientes.isEmpty()) {
                            System.out.println("Nenhum cliente cadastrado ou arquivo '" + ARQUIVO_CLIENTES + "' não encontrado/vazio.");
                        } else {
                            for (Cliente c : clientes) {
                                System.out.println("CPF: " + c.getCpf() + ", Nome: " + c.getNome() + ", Estudante: " + (c.isEstudante() ? "Sim" : "Não"));
                            }
                        }
                        clienteConsultado = null;
                        break;
                    case 4: // Consultar
                        System.out.println("\n-- Consultar Cliente --");
                        System.out.print("CPF do Cliente: ");
                        String cpfConsultar = scanner.nextLine().trim();
                         if (cpfConsultar.isEmpty()) {
                            System.out.println("CPF para consulta não pode ser vazio.");
                            clienteConsultado = null;
                        } else {
                            clienteConsultado = Cliente.consultar(cpfConsultar);
                            System.out.println("Cliente encontrado: " + clienteConsultado.getNome());
                            System.out.println("(Use a opção 5 para ver detalhes completos)");
                        }
                        break;
                    case 5: // Mostrar Detalhes
                        if (clienteConsultado != null) {
                            clienteConsultado.mostrar();
                        } else {
                            System.out.println("Nenhum cliente consultado recentemente. Use a opção 4 primeiro.");
                        }
                        break;
                    case 0: // Voltar
                        break;
                    default: throw new MenuOpcaoInvalidaException("Opção inválida no menu de Clientes.");
                }
            } catch (InputMismatchException e) {
                 System.err.println("\nErro: Entrada inválida para número. Tente novamente.");
                 scanner.nextLine(); // Limpa o buffer
                 clienteConsultado = null;
            } catch (NaoEncontradoException | ValidacaoException | PersistenciaException e) {
                 System.err.println("\nErro: " + e.getMessage());
                 clienteConsultado = null;
            }

             if (opcao != 0 && opcao != 5) {
                 pressioneEnterParaContinuar();
             }

        } while (opcao != 0);
    }

    // --- GÊNEROS --- 
    private static void gerenciarGeneros() throws PersistenciaException, ValidacaoException, NaoEncontradoException, MenuOpcaoInvalidaException {
        int opcao;
        Genero generoConsultado = null;

        do {
            exibirSubMenu("Gêneros", true);
            opcao = lerOpcaoInt();

            try {
                switch (opcao) {
                    case 1: // Inserir
                        System.out.println("\n-- Inserir Novo Gênero --");
                        System.out.print("ID do Gênero: ");
                        int id = lerOpcaoInt();
                        System.out.print("Descrição: ");
                        String desc = scanner.nextLine();

                        Genero novoGenero = new Genero(id, desc);
                        if (novoGenero.inserir()) {
                            System.out.println("Gênero inserido com sucesso! Verifique o arquivo em " + new File(ARQUIVO_GENEROS).getAbsolutePath());
                        }
                        generoConsultado = null;
                        break;
                    case 2: // Editar
                        System.out.println("\n-- Editar Gênero --");
                        System.out.print("ID do Gênero a editar: ");
                        int idEditar = lerOpcaoInt();

                        Genero generoParaEditar = Genero.consultar(idEditar);
                        System.out.println("Editando Gênero: " + generoParaEditar.getDescGenero()+ " (ID: " + generoParaEditar.getIdGenero() + ")");
                        System.out.println("Digite os novos dados (deixe em branco para manter o atual):");

                        System.out.print("Nova Descrição (" + generoParaEditar.getDescGenero() + "): ");
                        String novaDesc = scanner.nextLine();
                        if (!novaDesc.trim().isEmpty()) generoParaEditar.setDescGenero(novaDesc);

                        // Não permitimos editar o ID, pois é a chave primária

                        if (generoParaEditar.editar()) {
                            System.out.println("Gênero editado com sucesso!");
                        }
                        generoConsultado = null;
                        break;
                    case 3: // Listar
                        System.out.println("\n-- Lista de Gêneros --");
                        ArrayList<Genero> generos = Genero.listar();
                        if (generos.isEmpty()) {
                            System.out.println("Nenhum gênero cadastrado ou arquivo '" + ARQUIVO_GENEROS + "' não encontrado/vazio.");
                        } else {
                            for (Genero g : generos) {
                                System.out.println("ID: " + g.getIdGenero() + ", Descrição: " + g.getDescGenero());
                            }
                        }
                        generoConsultado = null;
                        break;
                    case 4: // Consultar
                        System.out.println("\n-- Consultar Gênero --");
                        System.out.print("ID do Gênero: ");
                        int idConsultar = lerOpcaoInt();
                        generoConsultado = Genero.consultar(idConsultar);
                        System.out.println("Gênero encontrado: " + generoConsultado.getDescGenero());
                        System.out.println("(Use a opção 5 para ver detalhes completos)");
                        break;
                    case 5: // Mostrar Detalhes
                        if (generoConsultado != null) {
                            generoConsultado.mostrar();
                        } else {
                            System.out.println("Nenhum gênero consultado recentemente. Use a opção 4 primeiro.");
                        }
                        break;
                    case 0: // Voltar
                        break;
                    default: throw new MenuOpcaoInvalidaException("Opção inválida no menu de Gêneros.");
                }
            } catch (InputMismatchException e) {
                 System.err.println("\nErro: Entrada inválida para número (ID). Tente novamente.");
                 scanner.nextLine(); // Limpa o buffer
                 generoConsultado = null;
            } catch (NaoEncontradoException | ValidacaoException | PersistenciaException e) {
                 System.err.println("\nErro: " + e.getMessage());
                 generoConsultado = null;
            }

             if (opcao != 0 && opcao != 5) {
                 pressioneEnterParaContinuar();
             }

        } while (opcao != 0);
    }

    // --- FILMES --- 
    private static void gerenciarFilmes() throws PersistenciaException, ValidacaoException, NaoEncontradoException, MenuOpcaoInvalidaException {
        int opcao;
        Filme filmeConsultado = null;

        do {
            exibirSubMenu("Filmes", true);
            opcao = lerOpcaoInt();

            try {
                switch (opcao) {
                    case 1: // Inserir
                        System.out.println("\n-- Inserir Novo Filme --");
                        System.out.print("ID do Filme: ");
                        int idFilme = lerOpcaoInt();
                        System.out.print("Título: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Duração (minutos): ");
                        int duracao = lerOpcaoInt();
                        System.out.print("ID do Gênero: ");
                        int idGenero = lerOpcaoInt();

                        Genero genero = Genero.consultar(idGenero); // Busca o objeto Genero

                        Filme novoFilme = new Filme(idFilme, titulo, duracao, genero);
                        if (novoFilme.inserir()) {
                            System.out.println("Filme inserido com sucesso! Verifique o arquivo em " + new File(ARQUIVO_FILMES).getAbsolutePath());
                        }
                        filmeConsultado = null;
                        break;
                    case 2: // Editar
                        System.out.println("\n-- Editar Filme --");
                        System.out.print("ID do Filme a editar: ");
                        int idEditar = lerOpcaoInt();

                        Filme filmeParaEditar = Filme.consultar(idEditar);
                        System.out.println("Editando Filme: " + filmeParaEditar.getTitulo() + " (ID: " + filmeParaEditar.getIdFilme() + ")");
                        System.out.println("Digite os novos dados (deixe em branco para manter o atual):");

                        System.out.print("Novo Título (" + filmeParaEditar.getTitulo() + "): ");
                        String novoTitulo = scanner.nextLine();
                        if (!novoTitulo.trim().isEmpty()) filmeParaEditar.setTitulo(novoTitulo);

                        System.out.print("Nova Duração (" + filmeParaEditar.getDuracao() + "): ");
                        String novaDuracaoStr = scanner.nextLine();
                        if (!novaDuracaoStr.trim().isEmpty()) {
                             try {
                                filmeParaEditar.setDuracao(Integer.parseInt(novaDuracaoStr.trim()));
                            } catch (NumberFormatException e) {
                                System.err.println("Duração inválida, mantendo a anterior.");
                            }
                        }

                        System.out.print("Novo ID do Gênero (" + filmeParaEditar.getGenero().getIdGenero() + "): ");
                        String novoIdGeneroStr = scanner.nextLine();
                        if (!novoIdGeneroStr.trim().isEmpty()) {
                            try {
                                int novoIdGenero = Integer.parseInt(novoIdGeneroStr.trim());
                                Genero novoGenero = Genero.consultar(novoIdGenero); // Busca o novo gênero
                                filmeParaEditar.setGenero(novoGenero);
                            } catch (NumberFormatException e) {
                                System.err.println("ID de Gênero inválido, mantendo o anterior.");
                            } catch (NaoEncontradoException e) {
                                System.err.println("Erro: Gênero com ID " + novoIdGeneroStr + " não encontrado. Gênero anterior mantido.");
                            }
                        }

                        if (filmeParaEditar.editar()) {
                            System.out.println("Filme editado com sucesso!");
                        }
                        filmeConsultado = null;
                        break;
                    case 3: // Listar
                        System.out.println("\n-- Lista de Filmes --");
                        ArrayList<Filme> filmes = Filme.listar();
                        if (filmes.isEmpty()) {
                            System.out.println("Nenhum filme cadastrado ou arquivo '" + ARQUIVO_FILMES + "' não encontrado/vazio.");
                        } else {
                            for (Filme f : filmes) {
                                System.out.println("ID: " + f.getIdFilme() + ", Título: " + f.getTitulo() + ", Gênero: " + (f.getGenero() != null ? f.getGenero().getDescGenero() : "N/A"));
                            }
                        }
                        filmeConsultado = null;
                        break;
                    case 4: // Consultar
                        System.out.println("\n-- Consultar Filme --");
                        System.out.print("ID do Filme: ");
                        int idConsultar = lerOpcaoInt();
                        filmeConsultado = Filme.consultar(idConsultar);
                        System.out.println("Filme encontrado: " + filmeConsultado.getTitulo());
                        System.out.println("(Use a opção 5 para ver detalhes completos)");
                        break;
                    case 5: // Mostrar Detalhes
                        if (filmeConsultado != null) {
                            filmeConsultado.mostrar();
                        } else {
                            System.out.println("Nenhum filme consultado recentemente. Use a opção 4 primeiro.");
                        }
                        break;
                    case 0: // Voltar
                        break;
                    default: throw new MenuOpcaoInvalidaException("Opção inválida no menu de Filmes.");
                }
            } catch (InputMismatchException e) {
                 System.err.println("\nErro: Entrada inválida para número (ID/Duração). Tente novamente.");
                 scanner.nextLine(); // Limpa o buffer
                 filmeConsultado = null;
            } catch (NaoEncontradoException | ValidacaoException | PersistenciaException e) {
                 System.err.println("\nErro: " + e.getMessage());
                 filmeConsultado = null;
            }

             if (opcao != 0 && opcao != 5) {
                 pressioneEnterParaContinuar();
             }

        } while (opcao != 0);
    }

    // --- SESSÕES --- 
    private static void gerenciarSessoes() throws PersistenciaException, ValidacaoException, NaoEncontradoException, MenuOpcaoInvalidaException {
        int opcao;
        Sessao sessaoConsultada = null;

        do {
            exibirSubMenu("Sessões", true);
            opcao = lerOpcaoInt();

            try {
                switch (opcao) {
                    case 1: // Inserir
                        System.out.println("\n-- Inserir Nova Sessão --");
                        System.out.print("ID da Sessão: ");
                        int idSessao = lerOpcaoInt();
                        System.out.print("Data (dd/MM/yyyy): ");
                        String data = scanner.nextLine();
                        System.out.print("Hora (HH:mm): ");
                        String hora = scanner.nextLine();
                        System.out.print("Classificação (0 para Livre): ");
                        int classificacao = lerOpcaoInt();
                        System.out.print("ID do Filme: ");
                        int idFilme = lerOpcaoInt();

                        Filme filme = Filme.consultar(idFilme); // Busca o objeto Filme

                        Sessao novaSessao = new Sessao(idSessao, data, hora, classificacao, filme);
                        if (novaSessao.inserir()) {
                            System.out.println("Sessão inserida com sucesso! Verifique o arquivo em " + new File(ARQUIVO_SESSOES).getAbsolutePath());
                        }
                        sessaoConsultada = null;
                        break;
                    case 2: // Editar
                        System.out.println("\n-- Editar Sessão --");
                        System.out.print("ID da Sessão a editar: ");
                        int idEditar = lerOpcaoInt();

                        Sessao sessaoParaEditar = Sessao.consultar(idEditar);
                        System.out.println("Editando Sessão: ID " + sessaoParaEditar.getIdSessao() + " (Filme: " + (sessaoParaEditar.getFilme() != null ? sessaoParaEditar.getFilme().getTitulo() : "N/A") + ")");
                        System.out.println("Digite os novos dados (deixe em branco para manter o atual):");

                        System.out.print("Nova Data (" + sessaoParaEditar.getDataSessao() + "): ");
                        String novaData = scanner.nextLine();
                        if (!novaData.trim().isEmpty()) sessaoParaEditar.setDataSessao(novaData);

                        System.out.print("Nova Hora (" + sessaoParaEditar.getHoraSessao() + "): ");
                        String novaHora = scanner.nextLine();
                        if (!novaHora.trim().isEmpty()) sessaoParaEditar.setHoraSessao(novaHora);

                        System.out.print("Nova Classificação (" + sessaoParaEditar.getClassificacao() + "): ");
                        String novaClassificacaoStr = scanner.nextLine();
                        if (!novaClassificacaoStr.trim().isEmpty()) {
                             try {
                                sessaoParaEditar.setClassificacao(Integer.parseInt(novaClassificacaoStr.trim()));
                            } catch (NumberFormatException e) {
                                System.err.println("Classificação inválida, mantendo a anterior.");
                            }
                        }

                        System.out.print("Novo ID do Filme (" + (sessaoParaEditar.getFilme() != null ? sessaoParaEditar.getFilme().getIdFilme() : "N/A") + "): ");
                        String novoIdFilmeStr = scanner.nextLine();
                        if (!novoIdFilmeStr.trim().isEmpty()) {
                            try {
                                int novoIdFilme = Integer.parseInt(novoIdFilmeStr.trim());
                                Filme novoFilme = Filme.consultar(novoIdFilme); // Busca o novo filme
                                sessaoParaEditar.setFilme(novoFilme);
                            } catch (NumberFormatException e) {
                                System.err.println("ID de Filme inválido, mantendo o anterior.");
                            } catch (NaoEncontradoException e) {
                                System.err.println("Erro: Filme com ID " + novoIdFilmeStr + " não encontrado. Filme anterior mantido.");
                            }
                        }

                        if (sessaoParaEditar.editar()) {
                            System.out.println("Sessão editada com sucesso!");
                        }
                        sessaoConsultada = null;
                        break;
                    case 3: // Listar
                        System.out.println("\n-- Lista de Sessões --");
                        ArrayList<Sessao> sessoes = Sessao.listar();
                        if (sessoes.isEmpty()) {
                            System.out.println("Nenhuma sessão cadastrada ou arquivo '" + ARQUIVO_SESSOES + "' não encontrado/vazio.");
                        } else {
                            for (Sessao s : sessoes) {
                                System.out.println("ID: " + s.getIdSessao() + ", Data: " + s.getDataSessao() + " " + s.getHoraSessao() + ", Filme: " + (s.getFilme() != null ? s.getFilme().getTitulo() : "N/A"));
                            }
                        }
                        sessaoConsultada = null;
                        break;
                    case 4: // Consultar
                        System.out.println("\n-- Consultar Sessão --");
                        System.out.print("ID da Sessão: ");
                        int idConsultar = lerOpcaoInt();
                        sessaoConsultada = Sessao.consultar(idConsultar);
                        System.out.println("Sessão encontrada: Filme " + (sessaoConsultada.getFilme() != null ? sessaoConsultada.getFilme().getTitulo() : "N/A") + " em " + sessaoConsultada.getDataSessao());
                        System.out.println("(Use a opção 5 para ver detalhes completos)");
                        break;
                    case 5: // Mostrar Detalhes
                        if (sessaoConsultada != null) {
                            sessaoConsultada.mostrar();
                        } else {
                            System.out.println("Nenhuma sessão consultada recentemente. Use a opção 4 primeiro.");
                        }
                        break;
                    case 0: // Voltar
                        break;
                    default: throw new MenuOpcaoInvalidaException("Opção inválida no menu de Sessões.");
                }
            } catch (InputMismatchException e) {
                 System.err.println("\nErro: Entrada inválida para número (ID/Classificação). Tente novamente.");
                 scanner.nextLine(); // Limpa o buffer
                 sessaoConsultada = null;
            } catch (NaoEncontradoException | ValidacaoException | PersistenciaException e) {
                 System.err.println("\nErro: " + e.getMessage());
                 sessaoConsultada = null;
            }

             if (opcao != 0 && opcao != 5) {
                 pressioneEnterParaContinuar();
             }

        } while (opcao != 0);
    }

    // --- INGRESSOS --- 
    private static void gerenciarIngressos() throws PersistenciaException, ValidacaoException, NaoEncontradoException, MenuOpcaoInvalidaException {
        int opcao;
        Ingresso ingressoConsultado = null;

        do {
            exibirSubMenu("Ingressos", true);
            opcao = lerOpcaoInt();

            try {
                switch (opcao) {
                    case 1: // Inserir
                        System.out.println("\n-- Inserir Novo Ingresso --");
                        System.out.print("ID do Ingresso: ");
                        int idIngresso = lerOpcaoInt();
                        System.out.print("Valor: ");
                        double valor = lerDouble();
                        boolean meia = lerBoolean("É meia entrada?");
                        boolean gratuidade = lerBoolean("Tem gratuidade?");
                        // boolean utilizado = lerBoolean("Já foi utilizado?"); // Normalmente começa como não utilizado
                        boolean utilizado = false;
                        System.out.print("ID da Sessão: ");
                        int idSessao = lerOpcaoInt();
                        System.out.print("CPF do Cliente: ");
                        String cpfCliente = scanner.nextLine().trim();

                        Sessao sessao = Sessao.consultar(idSessao);
                        Cliente cliente = Cliente.consultar(cpfCliente);

                        Ingresso novoIngresso = new Ingresso(idIngresso, valor, meia, gratuidade, utilizado, sessao, cliente);
                        if (novoIngresso.inserir()) {
                            System.out.println("Ingresso inserido com sucesso! Verifique o arquivo em " + new File(ARQUIVO_INGRESSOS).getAbsolutePath());
                        }
                        ingressoConsultado = null;
                        break;
                    case 2: // Editar
                        System.out.println("\n-- Editar Ingresso --");
                        System.out.print("ID do Ingresso a editar: ");
                        int idEditar = lerOpcaoInt();

                        Ingresso ingressoParaEditar = Ingresso.consultar(idEditar);
                        System.out.println("Editando Ingresso: ID " + ingressoParaEditar.getIdIngresso() + " (Cliente: " + (ingressoParaEditar.getCliente() != null ? ingressoParaEditar.getCliente().getNome() : "N/A") + ")");
                        System.out.println("Digite os novos dados (deixe em branco para manter o atual):");

                        System.out.print("Novo Valor (" + ingressoParaEditar.getValor() + "): ");
                        String novoValorStr = scanner.nextLine();
                        if (!novoValorStr.trim().isEmpty()) {
                             try {
                                ingressoParaEditar.setValor(Double.parseDouble(novoValorStr.trim()));
                            } catch (NumberFormatException e) {
                                System.err.println("Valor inválido, mantendo o anterior.");
                            }
                        }

                        System.out.print("É meia entrada? (" + (ingressoParaEditar.isMeiaEntrada() ? "s" : "n") + ") - Digite 's' ou 'n' ou deixe em branco: ");
                        String meiaStr = scanner.nextLine().trim().toLowerCase();
                        if (meiaStr.equals("s")) ingressoParaEditar.setMeiaEntrada(true);
                        else if (meiaStr.equals("n")) ingressoParaEditar.setMeiaEntrada(false);

                        System.out.print("Tem gratuidade? (" + (ingressoParaEditar.isGratuidade() ? "s" : "n") + ") - Digite 's' ou 'n' ou deixe em branco: ");
                        String gratuidadeStr = scanner.nextLine().trim().toLowerCase();
                        if (gratuidadeStr.equals("s")) ingressoParaEditar.setGratuidade(true);
                        else if (gratuidadeStr.equals("n")) ingressoParaEditar.setGratuidade(false);

                        System.out.print("Foi utilizado? (" + (ingressoParaEditar.isUtilizado() ? "s" : "n") + ") - Digite 's' ou 'n' ou deixe em branco: ");
                        String utilizadoStr = scanner.nextLine().trim().toLowerCase();
                        if (utilizadoStr.equals("s")) ingressoParaEditar.setUtilizado(true);
                        else if (utilizadoStr.equals("n")) ingressoParaEditar.setUtilizado(false);

                        System.out.print("Novo ID da Sessão (" + (ingressoParaEditar.getSessao() != null ? ingressoParaEditar.getSessao().getIdSessao() : "N/A") + "): ");
                        String novoIdSessaoStr = scanner.nextLine();
                        if (!novoIdSessaoStr.trim().isEmpty()) {
                            try {
                                int novoIdSessao = Integer.parseInt(novoIdSessaoStr.trim());
                                Sessao novaSessao = Sessao.consultar(novoIdSessao);
                                ingressoParaEditar.setSessao(novaSessao);
                            } catch (NumberFormatException e) {
                                System.err.println("ID de Sessão inválido, mantendo a anterior.");
                            } catch (NaoEncontradoException e) {
                                System.err.println("Erro: Sessão com ID " + novoIdSessaoStr + " não encontrada. Sessão anterior mantida.");
                            }
                        }

                        System.out.print("Novo CPF do Cliente (" + (ingressoParaEditar.getCliente() != null ? ingressoParaEditar.getCliente().getCpf() : "N/A") + "): ");
                        String novoCpfCliente = scanner.nextLine().trim();
                        if (!novoCpfCliente.isEmpty()) {
                            try {
                                Cliente novoCliente = Cliente.consultar(novoCpfCliente);
                                ingressoParaEditar.setCliente(novoCliente);
                            } catch (NaoEncontradoException e) {
                                System.err.println("Erro: Cliente com CPF " + novoCpfCliente + " não encontrado. Cliente anterior mantido.");
                            }
                        }

                        if (ingressoParaEditar.editar()) {
                            System.out.println("Ingresso editado com sucesso!");
                        }
                        ingressoConsultado = null;
                        break;
                    case 3: // Listar
                        System.out.println("\n-- Lista de Ingressos --");
                        ArrayList<Ingresso> ingressos = Ingresso.listar();
                        if (ingressos.isEmpty()) {
                            System.out.println("Nenhum ingresso cadastrado ou arquivo '" + ARQUIVO_INGRESSOS + "' não encontrado/vazio.");
                        } else {
                            for (Ingresso i : ingressos) {
                                System.out.println("ID: " + i.getIdIngresso() + ", Cliente: " + (i.getCliente() != null ? i.getCliente().getNome() : "N/A") + ", Sessão ID: " + (i.getSessao() != null ? i.getSessao().getIdSessao() : "N/A") + ", Utilizado: " + (i.isUtilizado() ? "Sim" : "Não"));
                            }
                        }
                        ingressoConsultado = null;
                        break;
                    case 4: // Consultar
                        System.out.println("\n-- Consultar Ingresso --");
                        System.out.print("ID do Ingresso: ");
                        int idConsultar = lerOpcaoInt();
                        ingressoConsultado = Ingresso.consultar(idConsultar);
                        System.out.println("Ingresso encontrado: Cliente " + (ingressoConsultado.getCliente() != null ? ingressoConsultado.getCliente().getNome() : "N/A") + " para Sessão ID " + (ingressoConsultado.getSessao() != null ? ingressoConsultado.getSessao().getIdSessao() : "N/A"));
                        System.out.println("(Use a opção 5 para ver detalhes completos)");
                        break;
                    case 5: // Mostrar Detalhes
                        if (ingressoConsultado != null) {
                            ingressoConsultado.mostrar();
                        } else {
                            System.out.println("Nenhum ingresso consultado recentemente. Use a opção 4 primeiro.");
                        }
                        break;
                    case 0: // Voltar
                        break;
                    default: throw new MenuOpcaoInvalidaException("Opção inválida no menu de Ingressos.");
                }
            } catch (InputMismatchException e) {
                 System.err.println("\nErro: Entrada inválida para número (ID/Valor). Tente novamente.");
                 scanner.nextLine(); // Limpa o buffer
                 ingressoConsultado = null;
            } catch (NaoEncontradoException | ValidacaoException | PersistenciaException e) {
                 System.err.println("\nErro: " + e.getMessage());
                 ingressoConsultado = null;
            }

             if (opcao != 0 && opcao != 5) {
                 pressioneEnterParaContinuar();
             }

        } while (opcao != 0);
    }

    // --- ELENCOS --- 
    private static void gerenciarElencos() throws PersistenciaException, ValidacaoException, NaoEncontradoException, MenuOpcaoInvalidaException {
        int opcao;
        Elenco elencoConsultado = null;

        do {
            exibirSubMenu("Elencos", true);
            opcao = lerOpcaoInt();

            try {
                switch (opcao) {
                    case 1: // Inserir
                        System.out.println("\n-- Inserir Nova Entrada de Elenco --");
                        System.out.print("ID do Elenco (único para esta relação Ator-Filme): ");
                        int idElenco = lerOpcaoInt();
                        System.out.print("CPF do Ator: ");
                        String cpfAtor = scanner.nextLine().trim();
                        System.out.print("ID do Filme: ");
                        int idFilme = lerOpcaoInt();
                        boolean principal = lerBoolean("É ator principal?");

                        Ator ator = Ator.consultar(cpfAtor);
                        Filme filme = Filme.consultar(idFilme);

                        Elenco novoElenco = new Elenco(idElenco, ator, filme, principal);
                        if (novoElenco.inserir()) {
                            System.out.println("Entrada de elenco inserida com sucesso! Verifique o arquivo em " + new File(ARQUIVO_ELENCOS).getAbsolutePath());
                        }
                        elencoConsultado = null;
                        break;
                    case 2: // Editar
                        System.out.println("\n-- Editar Entrada de Elenco --");
                        System.out.print("ID do Elenco a editar: ");
                        int idEditar = lerOpcaoInt();

                        Elenco elencoParaEditar = Elenco.consultar(idEditar);
                        System.out.println("Editando Elenco: ID " + elencoParaEditar.getIdElenco() + " (Ator: " + (elencoParaEditar.getAtor() != null ? elencoParaEditar.getAtor().getNome() : "N/A") + ", Filme: " + (elencoParaEditar.getFilme() != null ? elencoParaEditar.getFilme().getTitulo() : "N/A") + ")");
                        System.out.println("Digite os novos dados (deixe em branco para manter o atual):");

                        // Geralmente não se edita o Ator ou Filme de uma entrada de elenco, mas sim o status (principal)
                        // Se precisar editar Ator/Filme, seria mais lógico excluir e criar uma nova entrada.

                        System.out.print("É ator principal? (" + (elencoParaEditar.isAtorPrincipal() ? "s" : "n") + ") - Digite 's' ou 'n' ou deixe em branco: ");
                        String principalStr = scanner.nextLine().trim().toLowerCase();
                        if (principalStr.equals("s")) elencoParaEditar.setAtorPrincipal(true);
                        else if (principalStr.equals("n")) elencoParaEditar.setAtorPrincipal(false);

                        // Exemplo: Permitindo editar Ator (embora conceitualmente estranho)
                        System.out.print("Novo CPF do Ator (" + (elencoParaEditar.getAtor() != null ? elencoParaEditar.getAtor().getCpf() : "N/A") + "): ");
                        String novoCpfAtor = scanner.nextLine().trim();
                        if (!novoCpfAtor.isEmpty()) {
                            try {
                                Ator novoAtor = Ator.consultar(novoCpfAtor);
                                elencoParaEditar.setAtor(novoAtor);
                            } catch (NaoEncontradoException e) {
                                System.err.println("Erro: Ator com CPF " + novoCpfAtor + " não encontrado. Ator anterior mantido.");
                            }
                        }
                         // Exemplo: Permitindo editar Filme (embora conceitualmente estranho)
                        System.out.print("Novo ID do Filme (" + (elencoParaEditar.getFilme() != null ? elencoParaEditar.getFilme().getIdFilme() : "N/A") + "): ");
                        String novoIdFilmeStr = scanner.nextLine().trim();
                        if (!novoIdFilmeStr.isEmpty()) {
                            try {
                                int novoIdFilme = Integer.parseInt(novoIdFilmeStr);
                                Filme novoFilme = Filme.consultar(novoIdFilme);
                                elencoParaEditar.setFilme(novoFilme);
                            } catch (NumberFormatException e) {
                                System.err.println("ID de Filme inválido, mantendo o anterior.");
                            } catch (NaoEncontradoException e) {
                                System.err.println("Erro: Filme com ID " + novoIdFilmeStr + " não encontrado. Filme anterior mantido.");
                            }
                        }

                        if (elencoParaEditar.editar()) {
                            System.out.println("Entrada de elenco editada com sucesso!");
                        }
                        elencoConsultado = null;
                        break;
                    case 3: // Listar
                        System.out.println("\n-- Lista de Elencos --");
                        ArrayList<Elenco> elencos = Elenco.listar();
                        if (elencos.isEmpty()) {
                            System.out.println("Nenhuma entrada de elenco cadastrada ou arquivo '" + ARQUIVO_ELENCOS + "' não encontrado/vazio.");
                        } else {
                            for (Elenco e : elencos) {
                                System.out.println("ID: " + e.getIdElenco() + ", Ator: " + (e.getAtor() != null ? e.getAtor().getNome() : "N/A") + ", Filme: " + (e.getFilme() != null ? e.getFilme().getTitulo() : "N/A") + ", Principal: " + (e.isAtorPrincipal() ? "Sim" : "Não"));
                            }
                        }
                        elencoConsultado = null;
                        break;
                    case 4: // Consultar
                        System.out.println("\n-- Consultar Entrada de Elenco --");
                        System.out.print("ID do Elenco: ");
                        int idConsultar = lerOpcaoInt();
                        elencoConsultado = Elenco.consultar(idConsultar);
                        System.out.println("Elenco encontrado: Ator " + (elencoConsultado.getAtor() != null ? elencoConsultado.getAtor().getNome() : "N/A") + " no Filme " + (elencoConsultado.getFilme() != null ? elencoConsultado.getFilme().getTitulo() : "N/A"));
                        System.out.println("(Use a opção 5 para ver detalhes completos)");
                        break;
                    case 5: // Mostrar Detalhes
                        if (elencoConsultado != null) {
                            elencoConsultado.mostrar();
                        } else {
                            System.out.println("Nenhuma entrada de elenco consultada recentemente. Use a opção 4 primeiro.");
                        }
                        break;
                    case 0: // Voltar
                        break;
                    default: throw new MenuOpcaoInvalidaException("Opção inválida no menu de Elencos.");
                }
            } catch (InputMismatchException e) {
                 System.err.println("\nErro: Entrada inválida para número (ID). Tente novamente.");
                 scanner.nextLine(); // Limpa o buffer
                 elencoConsultado = null;
            } catch (NaoEncontradoException | ValidacaoException | PersistenciaException e) {
                 System.err.println("\nErro: " + e.getMessage());
                 elencoConsultado = null;
            }

             if (opcao != 0 && opcao != 5) {
                 pressioneEnterParaContinuar();
             }

        } while (opcao != 0);
    }

    private static void pressioneEnterParaContinuar() {
        System.out.print("\nPressione Enter para continuar...");
        scanner.nextLine();
    }
}

