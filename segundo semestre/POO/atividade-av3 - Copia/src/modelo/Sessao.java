package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import excecoes.PersistenciaException;
import excecoes.ValidacaoException;
import excecoes.NaoEncontradoException;
import persistencia.Persistencia; // Importa a classe de persistência

/**
 * Classe que representa uma Sessão de cinema.
 * Possui uma associação com Filme.
 */
public class Sessao {
    private int idSessao;
    private String dataSessao; // Formato "dd/MM/yyyy"
    private String horaSessao; // Formato "HH:mm"
    private int classificacao; // Classificação indicativa de idade
    private Filme filme; // Associação com Filme

    // Constante para o nome do arquivo de persistência
    private static final String NOME_ARQUIVO = "data/sessoes.txt";
    private static final String SEPARADOR = ";";

    /**
     * Construtor da classe Sessao.
     *
     * @param idSessao      ID da sessão.
     * @param dataSessao    Data da sessão (formato dd/MM/yyyy).
     * @param horaSessao    Hora da sessão (formato HH:mm).
     * @param classificacao Classificação indicativa.
     * @param filme         Objeto Filme associado à sessão.
     * @throws ValidacaoException Se algum dado for inválido.
     */
    public Sessao(int idSessao, String dataSessao, String horaSessao, int classificacao, Filme filme) throws ValidacaoException {
        if (idSessao <= 0) {
            throw new ValidacaoException("ID da Sessão deve ser um número positivo.");
        }
        // Validações simples de formato (poderiam ser mais robustas com regex ou Date API)
        if (dataSessao == null || !dataSessao.matches("\\d{2}/\\d{2}/\\d{4}")) {
            throw new ValidacaoException("Formato da Data da Sessão inválido (esperado dd/MM/yyyy).");
        }
        if (horaSessao == null || !horaSessao.matches("\\d{2}:\\d{2}")) {
            throw new ValidacaoException("Formato da Hora da Sessão inválido (esperado HH:mm).");
        }
        if (classificacao < 0) { // 0 pode ser 'Livre'
            throw new ValidacaoException("Classificação indicativa não pode ser negativa.");
        }
        if (filme == null) {
            throw new ValidacaoException("Sessão deve ter um Filme associado.");
        }
        this.idSessao = idSessao;
        this.dataSessao = dataSessao;
        this.horaSessao = horaSessao;
        this.classificacao = classificacao;
        this.filme = filme;
    }

    // Getters
    public int getIdSessao() {
        return idSessao;
    }

    public String getDataSessao() {
        return dataSessao;
    }

    public String getHoraSessao() {
        return horaSessao;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public Filme getFilme() {
        return filme;
    }

    // Setters
    public void setIdSessao(int idSessao) throws ValidacaoException {
        if (idSessao <= 0) {
            throw new ValidacaoException("ID da Sessão deve ser um número positivo.");
        }
        this.idSessao = idSessao;
    }

    public void setDataSessao(String dataSessao) throws ValidacaoException {
        if (dataSessao == null || !dataSessao.matches("\\d{2}/\\d{2}/\\d{4}")) {
            throw new ValidacaoException("Formato da Data da Sessão inválido (esperado dd/MM/yyyy).");
        }
        this.dataSessao = dataSessao;
    }

    public void setHoraSessao(String horaSessao) throws ValidacaoException {
        if (horaSessao == null || !horaSessao.matches("\\d{2}:\\d{2}")) {
            throw new ValidacaoException("Formato da Hora da Sessão inválido (esperado HH:mm).");
        }
        this.horaSessao = horaSessao;
    }

    public void setClassificacao(int classificacao) throws ValidacaoException {
        if (classificacao < 0) {
            throw new ValidacaoException("Classificação indicativa não pode ser negativa.");
        }
        this.classificacao = classificacao;
    }

    public void setFilme(Filme filme) throws ValidacaoException {
        if (filme == null) {
            throw new ValidacaoException("Sessão deve ter um Filme associado.");
        }
        this.filme = filme;
    }

    /**
     * Exibe os detalhes da sessão.
     */
    public void mostrar() {
        System.out.println("--- Detalhes da Sessão ---");
        System.out.println("ID Sessão: " + idSessao);
        System.out.println("Data: " + dataSessao);
        System.out.println("Hora: " + horaSessao);
        System.out.println("Classificação: " + (classificacao == 0 ? "Livre" : classificacao + " anos"));
        System.out.println("Filme: " + (filme != null ? filme.getTitulo() : "N/A")); // Mostra título do filme
        System.out.println("--------------------------");
    }

    /**
     * Retorna a representação do objeto em String para persistência.
     * Formato: idSessao;dataSessao;horaSessao;classificacao;idFilme
     */
    @Override
    public String toString() {
        return idSessao + SEPARADOR + dataSessao + SEPARADOR + horaSessao + SEPARADOR + classificacao + SEPARADOR + (filme != null ? filme.getIdFilme() : "0");
    }

     /**
     * Cria um objeto Sessao a partir de uma linha do arquivo TXT.
     * Busca o Filme associado pelo ID.
     *
     * @param linha A linha lida do arquivo.
     * @return O objeto Sessao correspondente.
     * @throws ValidacaoException Se os dados na linha forem inválidos ou mal formatados.
     * @throws PersistenciaException Se ocorrer erro ao buscar o Filme.
     * @throws NaoEncontradoException Se o Filme associado não for encontrado.
     */
    private static Sessao fromString(String linha) throws ValidacaoException, PersistenciaException, NaoEncontradoException {
        String[] dados = linha.split(SEPARADOR);
        if (dados.length != 5) {
            throw new ValidacaoException("Formato inválido na linha do arquivo de sessoes: " + linha);
        }
        try {
            int idSessao = Integer.parseInt(dados[0]);
            String data = dados[1];
            String hora = dados[2];
            int classificacao = Integer.parseInt(dados[3]);
            int idFilme = Integer.parseInt(dados[4]);

            // Buscar o objeto Filme pelo idFilme
            Filme filme = Filme.consultar(idFilme);

            return new Sessao(idSessao, data, hora, classificacao, filme);
        } catch (NumberFormatException e) {
            throw new ValidacaoException("Erro ao converter número na linha do arquivo de sessoes: " + linha, e);
        } catch (NaoEncontradoException e) {
            throw new NaoEncontradoException("Filme com ID " + dados[4] + " associado à sessão na linha '" + linha + "' não foi encontrado.");
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Erro de persistência ao buscar filme para a sessão na linha: " + linha, e);
        }
    }

    // --- Métodos de Persistência (Implementados usando a classe Persistencia) ---

    /**
     * Insere a sessão atual no arquivo TXT.
     * @return true sempre (lança exceção em caso de erro).
     * @throws PersistenciaException Se ocorrer um erro durante a escrita no arquivo ou se ID já existir.
     */
    public boolean inserir() throws PersistenciaException {
         try {
            consultar(this.idSessao);
            throw new PersistenciaException("Erro ao inserir: Já existe uma Sessão com o ID " + this.idSessao);
        } catch (NaoEncontradoException e) {
            // ID não existe, pode inserir
            Persistencia.inserirObjeto(NOME_ARQUIVO, this.toString());
            return true;
        } catch (PersistenciaException pe) {
             throw pe;
        }
    }

    /**
     * Edita os dados desta sessão no arquivo TXT.
     * A identificação é feita pelo idSessao.
     * @return true sempre (lança exceção em caso de erro ou se não encontrado).
     * @throws PersistenciaException Se ocorrer um erro durante a leitura/escrita no arquivo.
     * @throws NaoEncontradoException Se a sessão com este ID não for encontrada para edição.
     */
    public boolean editar() throws PersistenciaException, NaoEncontradoException {
        return Persistencia.editarObjetoPorId(NOME_ARQUIVO, String.valueOf(this.idSessao), this.toString(), SEPARADOR);
    }

    /**
     * Lista todas as sessões do arquivo TXT.
     * Carrega o Filme associado para cada sessão.
     * @return Uma lista de Sessoes.
     * @throws PersistenciaException Se ocorrer um erro durante a leitura ou ao buscar/validar Filmes.
     */
    public static ArrayList<Sessao> listar() throws PersistenciaException {
        List<String> linhas = Persistencia.listarLinhas(NOME_ARQUIVO);
        ArrayList<Sessao> listaSessoes = new ArrayList<>();
        for (String linha : linhas) {
            try {
                listaSessoes.add(fromString(linha));
            } catch (ValidacaoException | NaoEncontradoException e) {
                System.err.println("Erro ao processar linha do arquivo de sessoes ou buscar filme: " + linha + " - " + e.getMessage());
                // throw new PersistenciaException("Dado inválido ou Filme não encontrado no arquivo de sessoes: " + linha, e);
            }
        }
        return listaSessoes;
    }

    /**
     * Consulta uma sessão pelo seu ID no arquivo TXT.
     * Carrega o Filme associado.
     * @param idSessao O ID da sessão a ser consultada.
     * @return O objeto Sessao encontrado.
     * @throws PersistenciaException Se ocorrer um erro durante a leitura do arquivo ou na validação/busca do Filme.
     * @throws NaoEncontradoException Se a sessão ou seu filme associado não for encontrado.
     */
    public static Sessao consultar(int idSessao) throws PersistenciaException, NaoEncontradoException {
        String linha = Persistencia.consultarLinhaPorId(NOME_ARQUIVO, String.valueOf(idSessao), SEPARADOR);
        try {
            // O fromString já busca o filme
            return fromString(linha);
        } catch (ValidacaoException | NaoEncontradoException e) {
            throw new PersistenciaException("Erro ao validar dados ou buscar filme da sessão consultada (ID: " + idSessao + "): " + linha, e);
        }
    }

    // --- Fim dos Métodos de Persistência ---

    // Métodos equals e hashCode para comparações (baseado no idSessao)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sessao sessao = (Sessao) o;
        return idSessao == sessao.idSessao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSessao);
    }
}

