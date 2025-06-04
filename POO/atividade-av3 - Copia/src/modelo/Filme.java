package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import excecoes.PersistenciaException;
import excecoes.ValidacaoException;
import excecoes.NaoEncontradoException;
import persistencia.Persistencia; // Importa a classe de persistência

/**
 * Classe que representa um Filme.
 * Possui uma associação com Genero.
 */
public class Filme {
    private int idFilme;
    private String titulo;
    private int duracao; // em minutos
    private Genero genero; // Associação com Genero

    // Constante para o nome do arquivo de persistência
    private static final String NOME_ARQUIVO = "data/filmes.txt";
    private static final String SEPARADOR = ";";

    /**
     * Construtor da classe Filme.
     *
     * @param idFilme  ID do filme.
     * @param titulo   Título do filme.
     * @param duracao  Duração do filme em minutos.
     * @param genero   Objeto Genero associado ao filme.
     * @throws ValidacaoException Se algum dado for inválido.
     */
    public Filme(int idFilme, String titulo, int duracao, Genero genero) throws ValidacaoException {
        if (idFilme <= 0) {
            throw new ValidacaoException("ID do Filme deve ser um número positivo.");
        }
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new ValidacaoException("Título do Filme não pode ser vazio.");
        }
        if (duracao <= 0) {
            throw new ValidacaoException("Duração do Filme deve ser positiva.");
        }
        if (genero == null) {
            throw new ValidacaoException("Filme deve ter um Gênero associado.");
        }
        this.idFilme = idFilme;
        this.titulo = titulo.trim();
        this.duracao = duracao;
        this.genero = genero;
    }

    // Getters
    public int getIdFilme() {
        return idFilme;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getDuracao() {
        return duracao;
    }

    public Genero getGenero() {
        return genero;
    }

    // Setters
    public void setIdFilme(int idFilme) throws ValidacaoException {
        if (idFilme <= 0) {
            throw new ValidacaoException("ID do Filme deve ser um número positivo.");
        }
        this.idFilme = idFilme;
    }

    public void setTitulo(String titulo) throws ValidacaoException {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new ValidacaoException("Título do Filme não pode ser vazio.");
        }
        this.titulo = titulo.trim();
    }

    public void setDuracao(int duracao) throws ValidacaoException {
         if (duracao <= 0) {
            throw new ValidacaoException("Duração do Filme deve ser positiva.");
        }
        this.duracao = duracao;
    }

    public void setGenero(Genero genero) throws ValidacaoException {
        if (genero == null) {
            throw new ValidacaoException("Filme deve ter um Gênero associado.");
        }
        this.genero = genero;
    }

    /**
     * Exibe os detalhes do filme.
     */
    public void mostrar() {
        System.out.println("--- Detalhes do Filme ---");
        System.out.println("ID Filme: " + idFilme);
        System.out.println("Título: " + titulo);
        System.out.println("Duração: " + duracao + " minutos");
        System.out.println("Gênero: " + (genero != null ? genero.getDescGenero() : "N/A")); // Mostra descrição do gênero
        System.out.println("-------------------------");
    }

    /**
     * Retorna a representação do objeto em String para persistência.
     * Formato: idFilme;titulo;duracao;idGenero
     */
    @Override
    public String toString() {
        return idFilme + SEPARADOR + titulo + SEPARADOR + duracao + SEPARADOR + (genero != null ? genero.getIdGenero() : "0");
    }

    /**
     * Cria um objeto Filme a partir de uma linha do arquivo TXT.
     * Busca o Genero associado pelo ID.
     *
     * @param linha A linha lida do arquivo.
     * @return O objeto Filme correspondente.
     * @throws ValidacaoException Se os dados na linha forem inválidos ou mal formatados.
     * @throws PersistenciaException Se ocorrer erro ao buscar o Genero.
     * @throws NaoEncontradoException Se o Genero associado não for encontrado.
     */
    private static Filme fromString(String linha) throws ValidacaoException, PersistenciaException, NaoEncontradoException {
        String[] dados = linha.split(SEPARADOR);
        if (dados.length != 4) {
            throw new ValidacaoException("Formato inválido na linha do arquivo de filmes: " + linha);
        }
        try {
            int idFilme = Integer.parseInt(dados[0]);
            String titulo = dados[1];
            int duracao = Integer.parseInt(dados[2]);
            int idGenero = Integer.parseInt(dados[3]);

            // Buscar o objeto Genero pelo idGenero
            Genero genero = Genero.consultar(idGenero);

            return new Filme(idFilme, titulo, duracao, genero);
        } catch (NumberFormatException e) {
            throw new ValidacaoException("Erro ao converter número na linha do arquivo de filmes: " + linha, e);
        } catch (NaoEncontradoException e) {
            throw new NaoEncontradoException("Gênero com ID " + dados[3] + " associado ao filme na linha '" + linha + "' não foi encontrado.");
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Erro de persistência ao buscar gênero para o filme na linha: " + linha, e);
        }
    }

    // --- Métodos de Persistência (Implementados usando a classe Persistencia) ---

    /**
     * Insere o filme atual no arquivo TXT.
     * @return true sempre (lança exceção em caso de erro).
     * @throws PersistenciaException Se ocorrer um erro durante a escrita no arquivo ou se ID já existir.
     */
    public boolean inserir() throws PersistenciaException {
         try {
            consultar(this.idFilme);
            throw new PersistenciaException("Erro ao inserir: Já existe um Filme com o ID " + this.idFilme);
        } catch (NaoEncontradoException e) {
            // ID não existe, pode inserir
            Persistencia.inserirObjeto(NOME_ARQUIVO, this.toString());
            return true;
        } catch (PersistenciaException pe) {
             throw pe;
        }
    }

    /**
     * Edita os dados deste filme no arquivo TXT.
     * A identificação é feita pelo idFilme.
     * @return true sempre (lança exceção em caso de erro ou se não encontrado).
     * @throws PersistenciaException Se ocorrer um erro durante a leitura/escrita no arquivo.
     * @throws NaoEncontradoException Se o filme com este ID não for encontrado para edição.
     */
    public boolean editar() throws PersistenciaException, NaoEncontradoException {
        return Persistencia.editarObjetoPorId(NOME_ARQUIVO, String.valueOf(this.idFilme), this.toString(), SEPARADOR);
    }

    /**
     * Lista todos os filmes do arquivo TXT.
     * Carrega o Genero associado para cada filme.
     * @return Uma lista de Filmes.
     * @throws PersistenciaException Se ocorrer um erro durante a leitura ou ao buscar/validar Gêneros.
     */
    public static ArrayList<Filme> listar() throws PersistenciaException {
        List<String> linhas = Persistencia.listarLinhas(NOME_ARQUIVO);
        ArrayList<Filme> listaFilmes = new ArrayList<>();
        for (String linha : linhas) {
            try {
                listaFilmes.add(fromString(linha));
            } catch (ValidacaoException | NaoEncontradoException e) {
                System.err.println("Erro ao processar linha do arquivo de filmes ou buscar gênero: " + linha + " - " + e.getMessage());
                // Considerar se um filme com gênero inválido/não encontrado deve ser ignorado ou lançar exceção
                // throw new PersistenciaException("Dado inválido ou Gênero não encontrado no arquivo de filmes: " + linha, e);
            }
        }
        return listaFilmes;
    }

    /**
     * Consulta um filme pelo seu ID no arquivo TXT.
     * Carrega o Genero associado.
     * @param idFilme O ID do filme a ser consultado.
     * @return O objeto Filme encontrado.
     * @throws PersistenciaException Se ocorrer um erro durante a leitura do arquivo ou na validação/busca do Gênero.
     * @throws NaoEncontradoException Se o filme ou seu gênero associado não for encontrado.
     */
    public static Filme consultar(int idFilme) throws PersistenciaException, NaoEncontradoException {
        String linha = Persistencia.consultarLinhaPorId(NOME_ARQUIVO, String.valueOf(idFilme), SEPARADOR);
        try {
            // O fromString já busca o gênero
            return fromString(linha);
        } catch (ValidacaoException | NaoEncontradoException e) {
            // Se fromString lançou NaoEncontradoException, foi porque o Gênero não foi achado.
            // Se lançou ValidacaoException, o formato estava errado.
            throw new PersistenciaException("Erro ao validar dados ou buscar gênero do filme consultado (ID: " + idFilme + "): " + linha, e);
        }
    }

    // --- Fim dos Métodos de Persistência ---

    // Métodos equals e hashCode para comparações (baseado no idFilme)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filme filme = (Filme) o;
        return idFilme == filme.idFilme;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFilme);
    }
}

