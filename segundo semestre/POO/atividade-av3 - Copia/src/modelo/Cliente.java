package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import excecoes.PersistenciaException;
import excecoes.ValidacaoException;
import excecoes.NaoEncontradoException;
import persistencia.Persistencia; // Importa a classe de persistência

/**
 * Classe que representa um Cliente, herdando de Pessoa.
 */
public class Cliente extends Pessoa {
    private String rg;
    private boolean estudante; // true se for estudante, false caso contrário

    // Constante para o nome do arquivo de persistência
    private static final String NOME_ARQUIVO = "data/clientes.txt";
    private static final String SEPARADOR = ";";

    /**
     * Construtor da classe Cliente.
     *
     * @param cpf       CPF do cliente.
     * @param nome      Nome do cliente.
     * @param idade     Idade do cliente.
     * @param rg        RG do cliente.
     * @param estudante Indica se o cliente é estudante.
     * @throws ValidacaoException Se algum dado for inválido.
     */
    public Cliente(String cpf, String nome, int idade, String rg, boolean estudante) throws ValidacaoException {
        super(cpf, nome, idade);
        if (rg == null || rg.trim().isEmpty()) {
            throw new ValidacaoException("RG não pode ser vazio.");
        }
        // Validação adicional para CPF (exemplo simples)
        if (cpf == null || cpf.trim().isEmpty()) {
             throw new ValidacaoException("CPF do Cliente não pode ser vazio.");
        }
        // Outras validações para RG podem ser adicionadas (formato, etc.)
        this.rg = rg.trim();
        this.estudante = estudante;
    }

    // Getters
    public String getRg() {
        return rg;
    }

    public boolean isEstudante() {
        return estudante;
    }

    // Setters
    public void setRg(String rg) throws ValidacaoException {
        if (rg == null || rg.trim().isEmpty()) {
            throw new ValidacaoException("RG não pode ser vazio.");
        }
        this.rg = rg.trim();
    }

    public void setEstudante(boolean estudante) {
        this.estudante = estudante;
    }

    @Override
    public void mostrar() {
        System.out.println("--- Detalhes do Cliente ---");
        System.out.println("CPF: " + getCpf());
        System.out.println("Nome: " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("RG: " + rg);
        System.out.println("Estudante: " + (estudante ? "Sim" : "Não"));
        System.out.println("---------------------------");
    }

    /**
     * Retorna a representação do objeto em String para persistência.
     * Formato: cpf;nome;idade;rg;estudante(true/false)
     */
    @Override
    public String toString() {
        return getCpf() + SEPARADOR + getNome() + SEPARADOR + getIdade() + SEPARADOR + rg + SEPARADOR + estudante;
    }

    /**
     * Cria um objeto Cliente a partir de uma linha do arquivo TXT.
     *
     * @param linha A linha lida do arquivo.
     * @return O objeto Cliente correspondente.
     * @throws ValidacaoException Se os dados na linha forem inválidos ou mal formatados.
     */
    private static Cliente fromString(String linha) throws ValidacaoException {
        String[] dados = linha.split(SEPARADOR);
        if (dados.length != 5) {
            throw new ValidacaoException("Formato inválido na linha do arquivo de clientes: " + linha);
        }
        try {
            String cpf = dados[0];
            String nome = dados[1];
            int idade = Integer.parseInt(dados[2]);
            String rg = dados[3];
            boolean estudante = Boolean.parseBoolean(dados[4]);
            return new Cliente(cpf, nome, idade, rg, estudante);
        } catch (NumberFormatException e) {
            throw new ValidacaoException("Erro ao converter número na linha do arquivo de clientes: " + linha, e);
        }
    }

    // --- Métodos de Persistência (Implementados usando a classe Persistencia) ---

    /**
     * Insere o cliente atual no arquivo TXT.
     * @return true sempre (lança exceção em caso de erro).
     * @throws PersistenciaException Se ocorrer um erro durante a escrita no arquivo.
     */
    public boolean inserir() throws PersistenciaException {
        Persistencia.inserirObjeto(NOME_ARQUIVO, this.toString());
        return true;
    }

    /**
     * Edita os dados deste cliente no arquivo TXT.
     * A identificação é feita pelo CPF.
     * @return true sempre (lança exceção em caso de erro ou se não encontrado).
     * @throws PersistenciaException Se ocorrer um erro durante a leitura/escrita no arquivo.
     * @throws NaoEncontradoException Se o cliente com este CPF não for encontrado para edição.
     */
    public boolean editar() throws PersistenciaException, NaoEncontradoException {
        // O ID para edição é o CPF
        return Persistencia.editarObjetoPorId(NOME_ARQUIVO, this.getCpf(), this.toString(), SEPARADOR);
    }

    /**
     * Lista todos os clientes do arquivo TXT.
     * @return Uma lista de Clientes.
     * @throws PersistenciaException Se ocorrer um erro durante a leitura do arquivo ou na validação dos dados.
     */
    public static ArrayList<Cliente> listar() throws PersistenciaException {
        List<String> linhas = Persistencia.listarLinhas(NOME_ARQUIVO);
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        for (String linha : linhas) {
            try {
                listaClientes.add(fromString(linha));
            } catch (ValidacaoException e) {
                System.err.println("Erro ao processar linha do arquivo de clientes: " + linha + " - " + e.getMessage());
                // throw new PersistenciaException("Dado inválido no arquivo de clientes: " + linha, e);
            }
        }
        return listaClientes;
    }

    /**
     * Consulta um cliente pelo seu CPF no arquivo TXT.
     * @param cpf O CPF do cliente a ser consultado.
     * @return O objeto Cliente encontrado.
     * @throws PersistenciaException Se ocorrer um erro durante a leitura do arquivo ou na validação.
     * @throws NaoEncontradoException Se o cliente com o CPF especificado não for encontrado.
     */
    public static Cliente consultar(String cpf) throws PersistenciaException, NaoEncontradoException {
        String linha = Persistencia.consultarLinhaPorId(NOME_ARQUIVO, cpf, SEPARADOR);
        try {
            return fromString(linha);
        } catch (ValidacaoException e) {
            throw new PersistenciaException("Erro ao validar dados do cliente consultado (CPF: " + cpf + "): " + linha, e);
        }
    }

    // --- Fim dos Métodos de Persistência ---

    // Métodos equals e hashCode para comparações (baseado no CPF da superclasse)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o); // Compara baseado no CPF da classe Pessoa
    }

    @Override
    public int hashCode() {
        return super.hashCode(); // Usa o hash do CPF da classe Pessoa
    }
}

