package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import excecoes.PersistenciaException;
import excecoes.ValidacaoException;
import excecoes.NaoEncontradoException;
import persistencia.Persistencia; // Importa a classe de persistência

/**
 * Classe que representa um Ator, herdando de Pessoa.
 */
public class Ator extends Pessoa {
    private int registroProfissional;

    // Constante para o nome do arquivo de persistência
    private static final String NOME_ARQUIVO = "data/atores.txt";
    private static final String SEPARADOR = ";";

    /**
     * Construtor da classe Ator.
     *
     * @param cpf                 CPF do ator.
     * @param nome                Nome do ator.
     * @param idade               Idade do ator.
     * @param registroProfissional Registro profissional do ator.
     * @throws ValidacaoException Se algum dado for inválido.
     */
    public Ator(String cpf, String nome, int idade, int registroProfissional) throws ValidacaoException {
        super(cpf, nome, idade);
        if (registroProfissional <= 0) {
            throw new ValidacaoException("Registro profissional deve ser um número positivo.");
        }
        // Validação adicional para CPF (exemplo simples)
        if (cpf == null || cpf.trim().isEmpty()) {
             throw new ValidacaoException("CPF do Ator não pode ser vazio.");
        }
        this.registroProfissional = registroProfissional;
    }

    // Getter
    public int getRegistroProfissional() {
        return registroProfissional;
    }

    // Setter
    public void setRegistroProfissional(int registroProfissional) throws ValidacaoException {
         if (registroProfissional <= 0) {
            throw new ValidacaoException("Registro profissional deve ser um número positivo.");
        }
        this.registroProfissional = registroProfissional;
    }

    @Override
    public void mostrar() {
        System.out.println("--- Detalhes do Ator ---");
        System.out.println("CPF: " + getCpf());
        System.out.println("Nome: " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("Registro Profissional: " + registroProfissional);
        System.out.println("-------------------------");
    }

    /**
     * Retorna a representação do objeto em String para persistência.
     * Formato: cpf;nome;idade;registroProfissional
     */
    @Override
    public String toString() {
        return getCpf() + SEPARADOR + getNome() + SEPARADOR + getIdade() + SEPARADOR + registroProfissional;
    }

    /**
     * Cria um objeto Ator a partir de uma linha do arquivo TXT.
     *
     * @param linha A linha lida do arquivo.
     * @return O objeto Ator correspondente.
     * @throws ValidacaoException Se os dados na linha forem inválidos ou mal formatados.
     */
    private static Ator fromString(String linha) throws ValidacaoException {
        String[] dados = linha.split(SEPARADOR);
        if (dados.length != 4) {
            throw new ValidacaoException("Formato inválido na linha do arquivo de atores: " + linha);
        }
        try {
            String cpf = dados[0];
            String nome = dados[1];
            int idade = Integer.parseInt(dados[2]);
            int registro = Integer.parseInt(dados[3]);
            return new Ator(cpf, nome, idade, registro);
        } catch (NumberFormatException e) {
            throw new ValidacaoException("Erro ao converter número na linha do arquivo de atores: " + linha, e);
        }
    }

    // --- Métodos de Persistência (Implementados usando a classe Persistencia) ---

    /**
     * Insere o ator atual no arquivo TXT.
     * @return true sempre (lança exceção em caso de erro).
     * @throws PersistenciaException Se ocorrer um erro durante a escrita no arquivo.
     */
    public boolean inserir() throws PersistenciaException {
        Persistencia.inserirObjeto(NOME_ARQUIVO, this.toString());
        return true;
    }

    /**
     * Edita os dados deste ator no arquivo TXT.
     * A identificação é feita pelo CPF.
     * @return true sempre (lança exceção em caso de erro ou se não encontrado).
     * @throws PersistenciaException Se ocorrer um erro durante a leitura/escrita no arquivo.
     * @throws NaoEncontradoException Se o ator com este CPF não for encontrado para edição.
     */
    public boolean editar() throws PersistenciaException, NaoEncontradoException {
        // O ID para edição é o CPF
        return Persistencia.editarObjetoPorId(NOME_ARQUIVO, this.getCpf(), this.toString(), SEPARADOR);
    }

    /**
     * Lista todos os atores do arquivo TXT.
     * @return Uma lista de Atores.
     * @throws PersistenciaException Se ocorrer um erro durante a leitura do arquivo ou na validação dos dados.
     */
    public static ArrayList<Ator> listar() throws PersistenciaException {
        List<String> linhas = Persistencia.listarLinhas(NOME_ARQUIVO);
        ArrayList<Ator> listaAtores = new ArrayList<>();
        for (String linha : linhas) {
            try {
                listaAtores.add(fromString(linha));
            } catch (ValidacaoException e) {
                // Loga o erro ou decide como tratar linhas inválidas
                System.err.println("Erro ao processar linha do arquivo de atores: " + linha + " - " + e.getMessage());
                // Pode-se optar por lançar a PersistenciaException ou continuar
                // throw new PersistenciaException("Dado inválido no arquivo de atores: " + linha, e);
            }
        }
        return listaAtores;
    }

    /**
     * Consulta um ator pelo seu CPF no arquivo TXT.
     * @param cpf O CPF do ator a ser consultado.
     * @return O objeto Ator encontrado.
     * @throws PersistenciaException Se ocorrer um erro durante a leitura do arquivo ou na validação.
     * @throws NaoEncontradoException Se o ator com o CPF especificado não for encontrado.
     */
    public static Ator consultar(String cpf) throws PersistenciaException, NaoEncontradoException {
        String linha = Persistencia.consultarLinhaPorId(NOME_ARQUIVO, cpf, SEPARADOR);
        try {
            return fromString(linha);
        } catch (ValidacaoException e) {
            throw new PersistenciaException("Erro ao validar dados do ator consultado (CPF: " + cpf + "): " + linha, e);
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

