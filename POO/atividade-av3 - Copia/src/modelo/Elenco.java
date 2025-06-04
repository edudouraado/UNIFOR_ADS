package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import excecoes.PersistenciaException;
import excecoes.ValidacaoException;
import excecoes.NaoEncontradoException;
import persistencia.Persistencia; // Importa a classe de persistência

/**
 * Classe que representa a participação de um Ator em um Filme (Elenco).
 * Possui associações com Ator e Filme.
 */
public class Elenco {
    private int idElenco; // Identificador único para a relação Ator-Filme
    private Ator ator;       // Associação com Ator
    private Filme filme;     // Associação com Filme
    private boolean atorPrincipal; // Indica se o ator é principal no filme

    // Constante para o nome do arquivo de persistência
    private static final String NOME_ARQUIVO = "data/elencos.txt";
    private static final String SEPARADOR = ";";

    /**
     * Construtor da classe Elenco.
     *
     * @param idElenco      ID da entrada do elenco.
     * @param ator          Objeto Ator associado.
     * @param filme         Objeto Filme associado.
     * @param atorPrincipal Indica se é o ator principal.
     * @throws ValidacaoException Se algum dado for inválido.
     */
    public Elenco(int idElenco, Ator ator, Filme filme, boolean atorPrincipal) throws ValidacaoException {
        if (idElenco <= 0) {
            throw new ValidacaoException("ID do Elenco deve ser um número positivo.");
        }
        if (ator == null) {
            throw new ValidacaoException("Elenco deve ter um Ator associado.");
        }
        if (filme == null) {
            throw new ValidacaoException("Elenco deve ter um Filme associado.");
        }
        this.idElenco = idElenco;
        this.ator = ator;
        this.filme = filme;
        this.atorPrincipal = atorPrincipal;
    }

    // Getters
    public int getIdElenco() {
        return idElenco;
    }

    public Ator getAtor() {
        return ator;
    }

    public Filme getFilme() {
        return filme;
    }

    public boolean isAtorPrincipal() {
        return atorPrincipal;
    }

    // Setters
    public void setIdElenco(int idElenco) throws ValidacaoException {
        if (idElenco <= 0) {
            throw new ValidacaoException("ID do Elenco deve ser um número positivo.");
        }
        this.idElenco = idElenco;
    }

    public void setAtor(Ator ator) throws ValidacaoException {
        if (ator == null) {
            throw new ValidacaoException("Elenco deve ter um Ator associado.");
        }
        this.ator = ator;
    }

    public void setFilme(Filme filme) throws ValidacaoException {
        if (filme == null) {
            throw new ValidacaoException("Elenco deve ter um Filme associado.");
        }
        this.filme = filme;
    }

    public void setAtorPrincipal(boolean atorPrincipal) {
        this.atorPrincipal = atorPrincipal;
    }

    /**
     * Exibe os detalhes da participação no elenco.
     */
    public void mostrar() {
        System.out.println("--- Detalhes do Elenco ---");
        System.out.println("ID Elenco: " + idElenco);
        System.out.println("Ator CPF: " + (ator != null ? ator.getCpf() : "N/A") + " (Nome: " + (ator != null ? ator.getNome() : "N/A") + ")");
        System.out.println("Filme ID: " + (filme != null ? filme.getIdFilme() : "N/A") + " (Título: " + (filme != null ? filme.getTitulo() : "N/A") + ")");
        System.out.println("Ator Principal: " + (atorPrincipal ? "Sim" : "Não"));
        System.out.println("--------------------------");
    }

    /**
     * Retorna a representação do objeto em String para persistência.
     * Formato: idElenco;cpfAtor;idFilme;atorPrincipal
     */
    @Override
    public String toString() {
        return idElenco + SEPARADOR + (ator != null ? ator.getCpf() : "null") + SEPARADOR +
               (filme != null ? filme.getIdFilme() : "0") + SEPARADOR + atorPrincipal;
    }

    /**
     * Cria um objeto Elenco a partir de uma linha do arquivo TXT.
     * Busca o Ator e o Filme associados pelos IDs/CPF.
     *
     * @param linha A linha lida do arquivo.
     * @return O objeto Elenco correspondente.
     * @throws ValidacaoException Se os dados na linha forem inválidos ou mal formatados.
     * @throws PersistenciaException Se ocorrer erro ao buscar Ator ou Filme.
     * @throws NaoEncontradoException Se o Ator ou Filme associado não for encontrado.
     */
    private static Elenco fromString(String linha) throws ValidacaoException, PersistenciaException, NaoEncontradoException {
        String[] dados = linha.split(SEPARADOR);
        if (dados.length != 4) {
            throw new ValidacaoException("Formato inválido na linha do arquivo de elencos: " + linha);
        }
        try {
            int idElenco = Integer.parseInt(dados[0]);
            String cpfAtor = dados[1];
            int idFilme = Integer.parseInt(dados[2]);
            boolean principal = Boolean.parseBoolean(dados[3]);

            // Buscar objetos associados
            Ator ator = Ator.consultar(cpfAtor);
            Filme filme = Filme.consultar(idFilme);

            return new Elenco(idElenco, ator, filme, principal);
        } catch (NumberFormatException e) {
            throw new ValidacaoException("Erro ao converter número/boolean na linha do arquivo de elencos: " + linha, e);
        } catch (NaoEncontradoException e) {
            throw new NaoEncontradoException("Ator (CPF: " + dados[1] + ") ou Filme (ID: " + dados[2] + ") associado ao elenco na linha 
'" + linha + "' não foi encontrado.", e);
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Erro de persistência ao buscar ator/filme para o elenco na linha: " + linha, e);
        }
    }

    // --- Métodos de Persistência (Implementados usando a classe Persistencia) ---

    /**
     * Insere a entrada do elenco atual no arquivo TXT.
     * @return true sempre (lança exceção em caso de erro).
     * @throws PersistenciaException Se ocorrer um erro durante a escrita no arquivo ou se ID já existir.
     */
    public boolean inserir() throws PersistenciaException {
         try {
            consultar(this.idElenco);
            throw new PersistenciaException("Erro ao inserir: Já existe uma entrada de Elenco com o ID " + this.idElenco);
        } catch (NaoEncontradoException e) {
            // ID não existe, pode inserir
            Persistencia.inserirObjeto(NOME_ARQUIVO, this.toString());
            return true;
        } catch (PersistenciaException pe) {
             throw pe;
        }
    }

    /**
     * Edita os dados desta entrada do elenco no arquivo TXT.
     * A identificação é feita pelo idElenco.
     * @return true sempre (lança exceção em caso de erro ou se não encontrado).
     * @throws PersistenciaException Se ocorrer um erro durante a leitura/escrita no arquivo.
     * @throws NaoEncontradoException Se a entrada do elenco com este ID não for encontrada para edição.
     */
    public boolean editar() throws PersistenciaException, NaoEncontradoException {
        return Persistencia.editarObjetoPorId(NOME_ARQUIVO, String.valueOf(this.idElenco), this.toString(), SEPARADOR);
    }

    /**
     * Lista todas as entradas do elenco do arquivo TXT.
     * Carrega o Ator e o Filme associados para cada entrada.
     * @return Uma lista de Elencos.
     * @throws PersistenciaException Se ocorrer um erro durante a leitura ou ao buscar/validar Ator/Filme.
     */
    public static ArrayList<Elenco> listar() throws PersistenciaException {
        List<String> linhas = Persistencia.listarLinhas(NOME_ARQUIVO);
        ArrayList<Elenco> listaElencos = new ArrayList<>();
        for (String linha : linhas) {
            try {
                listaElencos.add(fromString(linha));
            } catch (ValidacaoException | NaoEncontradoException e) {
                System.err.println("Erro ao processar linha do arquivo de elencos ou buscar associações: " + linha + " - " + e.getMessage());
                // throw new PersistenciaException("Dado inválido ou Associação não encontrada no arquivo de elencos: " + linha, e);
            }
        }
        return listaElencos;
    }

    /**
     * Consulta uma entrada do elenco pelo seu ID no arquivo TXT.
     * Carrega o Ator e o Filme associados.
     * @param idElenco O ID da entrada do elenco a ser consultada.
     * @return O objeto Elenco encontrado.
     * @throws PersistenciaException Se ocorrer um erro durante a leitura do arquivo ou na validação/busca das associações.
     * @throws NaoEncontradoException Se a entrada do elenco, seu ator ou filme associado não for encontrado.
     */
    public static Elenco consultar(int idElenco) throws PersistenciaException, NaoEncontradoException {
        String linha = Persistencia.consultarLinhaPorId(NOME_ARQUIVO, String.valueOf(idElenco), SEPARADOR);
        try {
            // O fromString já busca as associações
            return fromString(linha);
        } catch (ValidacaoException | NaoEncontradoException e) {
            throw new PersistenciaException("Erro ao validar dados ou buscar associações do elenco consultado (ID: " + idElenco + "): " + linha, e);
        }
    }

    // --- Fim dos Métodos de Persistência ---

    // Métodos equals e hashCode para comparações (baseado no idElenco)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elenco elenco = (Elenco) o;
        return idElenco == elenco.idElenco;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idElenco);
    }
}

