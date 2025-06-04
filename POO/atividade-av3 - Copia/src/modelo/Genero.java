package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import excecoes.PersistenciaException;
import excecoes.ValidacaoException;
import excecoes.NaoEncontradoException;
import persistencia.Persistencia; // Importa a classe de persistência

/**
 * Classe que representa um Gênero de filme.
 */
public class Genero {
    private int idGenero;
    private String descGenero;

    // Constante para o nome do arquivo de persistência
    private static final String NOME_ARQUIVO = "data/generos.txt";
    private static final String SEPARADOR = ";";

    /**
     * Construtor da classe Genero.
     *
     * @param idGenero   ID do gênero.
     * @param descGenero Descrição do gênero.
     * @throws ValidacaoException Se algum dado for inválido.
     */
    public Genero(int idGenero, String descGenero) throws ValidacaoException {
        if (idGenero <= 0) {
            throw new ValidacaoException("ID do Gênero deve ser um número positivo.");
        }
        if (descGenero == null || descGenero.trim().isEmpty()) {
            throw new ValidacaoException("Descrição do Gênero não pode ser vazia.");
        }
        this.idGenero = idGenero;
        this.descGenero = descGenero.trim();
    }

    // Getters
    public int getIdGenero() {
        return idGenero;
    }

    public String getDescGenero() {
        return descGenero;
    }

    // Setters
    public void setIdGenero(int idGenero) throws ValidacaoException {
         if (idGenero <= 0) {
            throw new ValidacaoException("ID do Gênero deve ser um número positivo.");
        }
        this.idGenero = idGenero;
    }

    public void setDescGenero(String descGenero) throws ValidacaoException {
        if (descGenero == null || descGenero.trim().isEmpty()) {
            throw new ValidacaoException("Descrição do Gênero não pode ser vazia.");
        }
        this.descGenero = descGenero.trim();
    }

    /**
     * Exibe os detalhes do gênero.
     */
    public void mostrar() {
        System.out.println("--- Detalhes do Gênero ---");
        System.out.println("ID Gênero: " + idGenero);
        System.out.println("Descrição: " + descGenero);
        System.out.println("--------------------------");
    }

    /**
     * Retorna a representação do objeto em String para persistência.
     * Formato: idGenero;descGenero
     */
    @Override
    public String toString() {
        return idGenero + SEPARADOR + descGenero;
    }

    /**
     * Cria um objeto Genero a partir de uma linha do arquivo TXT.
     *
     * @param linha A linha lida do arquivo.
     * @return O objeto Genero correspondente.
     * @throws ValidacaoException Se os dados na linha forem inválidos ou mal formatados.
     */
    private static Genero fromString(String linha) throws ValidacaoException {
        String[] dados = linha.split(SEPARADOR);
        if (dados.length != 2) {
            throw new ValidacaoException("Formato inválido na linha do arquivo de generos: " + linha);
        }
        try {
            int id = Integer.parseInt(dados[0]);
            String desc = dados[1];
            return new Genero(id, desc);
        } catch (NumberFormatException e) {
            throw new ValidacaoException("Erro ao converter ID na linha do arquivo de generos: " + linha, e);
        }
    }

    // --- Métodos de Persistência (Implementados usando a classe Persistencia) ---

    /**
     * Insere o gênero atual no arquivo TXT.
     * @return true sempre (lança exceção em caso de erro).
     * @throws PersistenciaException Se ocorrer um erro durante a escrita no arquivo.
     */
    public boolean inserir() throws PersistenciaException {
        // Verifica se já existe um gênero com o mesmo ID antes de inserir
        try {
            consultar(this.idGenero);
            // Se encontrou, não deveria inserir um ID duplicado (regra de negócio)
            throw new PersistenciaException("Erro ao inserir: Já existe um Gênero com o ID " + this.idGenero);
        } catch (NaoEncontradoException e) {
            // Se não encontrou, pode inserir
            Persistencia.inserirObjeto(NOME_ARQUIVO, this.toString());
            return true;
        } catch (PersistenciaException pe) {
             // Relança outras exceções de persistência
             throw pe;
        }
    }

    /**
     * Edita os dados deste gênero no arquivo TXT.
     * A identificação é feita pelo idGenero.
     * @return true sempre (lança exceção em caso de erro ou se não encontrado).
     * @throws PersistenciaException Se ocorrer um erro durante a leitura/escrita no arquivo.
     * @throws NaoEncontradoException Se o gênero com este ID não for encontrado para edição.
     */
    public boolean editar() throws PersistenciaException, NaoEncontradoException {
        // O ID para edição é o idGenero convertido para String
        return Persistencia.editarObjetoPorId(NOME_ARQUIVO, String.valueOf(this.idGenero), this.toString(), SEPARADOR);
    }

    /**
     * Lista todos os gêneros do arquivo TXT.
     * @return Uma lista de Generos.
     * @throws PersistenciaException Se ocorrer um erro durante a leitura do arquivo ou na validação dos dados.
     */
    public static ArrayList<Genero> listar() throws PersistenciaException {
        List<String> linhas = Persistencia.listarLinhas(NOME_ARQUIVO);
        ArrayList<Genero> listaGeneros = new ArrayList<>();
        for (String linha : linhas) {
            try {
                listaGeneros.add(fromString(linha));
            } catch (ValidacaoException e) {
                System.err.println("Erro ao processar linha do arquivo de generos: " + linha + " - " + e.getMessage());
                // throw new PersistenciaException("Dado inválido no arquivo de generos: " + linha, e);
            }
        }
        return listaGeneros;
    }

    /**
     * Consulta um gênero pelo seu ID no arquivo TXT.
     * @param idGenero O ID do gênero a ser consultado.
     * @return O objeto Genero encontrado.
     * @throws PersistenciaException Se ocorrer um erro durante a leitura do arquivo ou na validação.
     * @throws NaoEncontradoException Se o gênero com o ID especificado não for encontrado.
     */
    public static Genero consultar(int idGenero) throws PersistenciaException, NaoEncontradoException {
        String linha = Persistencia.consultarLinhaPorId(NOME_ARQUIVO, String.valueOf(idGenero), SEPARADOR);
        try {
            return fromString(linha);
        } catch (ValidacaoException e) {
            throw new PersistenciaException("Erro ao validar dados do genero consultado (ID: " + idGenero + "): " + linha, e);
        }
    }

    // --- Fim dos Métodos de Persistência ---

    // Métodos equals e hashCode para comparações (baseado no idGenero)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genero genero = (Genero) o;
        return idGenero == genero.idGenero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGenero);
    }
}

