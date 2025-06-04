package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import excecoes.PersistenciaException;
import excecoes.ValidacaoException;
import excecoes.NaoEncontradoException;
import persistencia.Persistencia; // Importa a classe de persistência

/**
 * Classe que representa um Ingresso.
 * Possui associações com Sessao e Cliente.
 */
public class Ingresso {
    private int idIngresso;
    private double valor;
    private boolean meiaEntrada;
    private boolean gratuidade;
    private boolean utilizado;
    private Sessao sessao; // Associação com Sessao
    private Cliente cliente; // Associação com Cliente

    // Constante para o nome do arquivo de persistência
    private static final String NOME_ARQUIVO = "data/ingressos.txt";
    private static final String SEPARADOR = ";";

    /**
     * Construtor da classe Ingresso.
     *
     * @param idIngresso  ID do ingresso.
     * @param valor       Valor do ingresso.
     * @param meiaEntrada Indica se é meia entrada.
     * @param gratuidade  Indica se há gratuidade.
     * @param utilizado   Indica se o ingresso já foi utilizado.
     * @param sessao      Objeto Sessao associado ao ingresso.
     * @param cliente     Objeto Cliente associado ao ingresso.
     * @throws ValidacaoException Se algum dado for inválido.
     */
    public Ingresso(int idIngresso, double valor, boolean meiaEntrada, boolean gratuidade, boolean utilizado, Sessao sessao, Cliente cliente) throws ValidacaoException {
        if (idIngresso <= 0) {
            throw new ValidacaoException("ID do Ingresso deve ser um número positivo.");
        }
        if (valor < 0) {
            throw new ValidacaoException("Valor do Ingresso não pode ser negativo.");
        }
        if (meiaEntrada && gratuidade) {
            throw new ValidacaoException("Ingresso não pode ser meia entrada e ter gratuidade ao mesmo tempo.");
        }
        if (sessao == null) {
            throw new ValidacaoException("Ingresso deve ter uma Sessão associada.");
        }
        if (cliente == null) {
            throw new ValidacaoException("Ingresso deve ter um Cliente associado.");
        }

        // Verifica se a idade do cliente é compatível com a classificação da sessão
        if (cliente.getIdade() < sessao.getClassificacao()) {
             throw new ValidacaoException("Cliente (idade " + cliente.getIdade() + ") não tem idade suficiente para a classificação da sessão (" + sessao.getClassificacao() + " anos).");
        }

        this.idIngresso = idIngresso;
        // Arredonda o valor para 2 casas decimais para consistência
        this.valor = Math.round(valor * 100.0) / 100.0;
        this.meiaEntrada = meiaEntrada;
        this.gratuidade = gratuidade;
        this.utilizado = utilizado;
        this.sessao = sessao;
        this.cliente = cliente;
    }

    // Getters
    public int getIdIngresso() {
        return idIngresso;
    }

    public double getValor() {
        return valor;
    }

    public boolean isMeiaEntrada() {
        return meiaEntrada;
    }

    public boolean isGratuidade() {
        return gratuidade;
    }

    public boolean isUtilizado() {
        return utilizado;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    // Setters
    public void setIdIngresso(int idIngresso) throws ValidacaoException {
         if (idIngresso <= 0) {
            throw new ValidacaoException("ID do Ingresso deve ser um número positivo.");
        }
        this.idIngresso = idIngresso;
    }

    public void setValor(double valor) throws ValidacaoException {
        if (valor < 0) {
            throw new ValidacaoException("Valor do Ingresso não pode ser negativo.");
        }
        this.valor = Math.round(valor * 100.0) / 100.0;
    }

    public void setMeiaEntrada(boolean meiaEntrada) throws ValidacaoException {
         if (meiaEntrada && this.gratuidade) {
            throw new ValidacaoException("Ingresso não pode ser meia entrada e ter gratuidade ao mesmo tempo.");
        }
        this.meiaEntrada = meiaEntrada;
    }

    public void setGratuidade(boolean gratuidade) throws ValidacaoException {
        if (this.meiaEntrada && gratuidade) {
            throw new ValidacaoException("Ingresso não pode ser meia entrada e ter gratuidade ao mesmo tempo.");
        }
        this.gratuidade = gratuidade;
    }

    public void setUtilizado(boolean utilizado) {
        this.utilizado = utilizado;
    }

    public void setSessao(Sessao sessao) throws ValidacaoException {
        if (sessao == null) {
            throw new ValidacaoException("Ingresso deve ter uma Sessão associada.");
        }
         // Re-verifica a idade do cliente atual com a nova sessão
        if (this.cliente != null && this.cliente.getIdade() < sessao.getClassificacao()) {
             throw new ValidacaoException("Cliente (idade " + this.cliente.getIdade() + ") não tem idade suficiente para a classificação da nova sessão (" + sessao.getClassificacao() + " anos).");
        }
        this.sessao = sessao;
    }

    public void setCliente(Cliente cliente) throws ValidacaoException {
        if (cliente == null) {
            throw new ValidacaoException("Ingresso deve ter um Cliente associado.");
        }
        // Re-verifica a idade do novo cliente com a sessão atual
        if (this.sessao != null && cliente.getIdade() < this.sessao.getClassificacao()) {
             throw new ValidacaoException("Novo cliente (idade " + cliente.getIdade() + ") não tem idade suficiente para a classificação da sessão (" + this.sessao.getClassificacao() + " anos).");
        }
        this.cliente = cliente;
    }

    /**
     * Exibe os detalhes do ingresso.
     */
    public void mostrar() {
        System.out.println("--- Detalhes do Ingresso ---");
        System.out.println("ID Ingresso: " + idIngresso);
        System.out.println("Valor: R$ " + String.format("%.2f", valor));
        System.out.println("Meia Entrada: " + (meiaEntrada ? "Sim" : "Não"));
        System.out.println("Gratuidade: " + (gratuidade ? "Sim" : "Não"));
        System.out.println("Utilizado: " + (utilizado ? "Sim" : "Não"));
        System.out.println("Sessão ID: " + (sessao != null ? sessao.getIdSessao() : "N/A") + " (Filme: " + (sessao != null && sessao.getFilme() != null ? sessao.getFilme().getTitulo() : "N/A") + ")");
        System.out.println("Cliente CPF: " + (cliente != null ? cliente.getCpf() : "N/A") + " (Nome: " + (cliente != null ? cliente.getNome() : "N/A") + ")");
        System.out.println("----------------------------");
    }

    /**
     * Retorna a representação do objeto em String para persistência.
     * Formato: idIngresso;valor;meiaEntrada;gratuidade;utilizado;idSessao;cpfCliente
     */
    @Override
    public String toString() {
        return idIngresso + SEPARADOR + valor + SEPARADOR + meiaEntrada + SEPARADOR + gratuidade + SEPARADOR + utilizado + SEPARADOR +
               (sessao != null ? sessao.getIdSessao() : "0") + SEPARADOR + (cliente != null ? cliente.getCpf() : "null");
    }

    /**
     * Cria um objeto Ingresso a partir de uma linha do arquivo TXT.
     * Busca a Sessao e o Cliente associados pelos IDs/CPF.
     *
     * @param linha A linha lida do arquivo.
     * @return O objeto Ingresso correspondente.
     * @throws ValidacaoException Se os dados na linha forem inválidos ou mal formatados.
     * @throws PersistenciaException Se ocorrer erro ao buscar Sessao ou Cliente.
     * @throws NaoEncontradoException Se a Sessao ou Cliente associado não for encontrado.
     */
    private static Ingresso fromString(String linha) throws ValidacaoException, PersistenciaException, NaoEncontradoException {
        String[] dados = linha.split(SEPARADOR);
        if (dados.length != 7) {
            throw new ValidacaoException("Formato inválido na linha do arquivo de ingressos: " + linha);
        }
        try {
            int idIngresso = Integer.parseInt(dados[0]);
            double valor = Double.parseDouble(dados[1]);
            boolean meia = Boolean.parseBoolean(dados[2]);
            boolean gratuidade = Boolean.parseBoolean(dados[3]);
            boolean utilizado = Boolean.parseBoolean(dados[4]);
            int idSessao = Integer.parseInt(dados[5]);
            String cpfCliente = dados[6];

            // Buscar objetos associados
            Sessao sessao = Sessao.consultar(idSessao);
            Cliente cliente = Cliente.consultar(cpfCliente);

            return new Ingresso(idIngresso, valor, meia, gratuidade, utilizado, sessao, cliente);
        } catch (NumberFormatException e) {
            throw new ValidacaoException("Erro ao converter número/boolean na linha do arquivo de ingressos: " + linha, e);
        } catch (NaoEncontradoException e) {
            throw new NaoEncontradoException("Sessão (ID: " + dados[5] + ") ou Cliente (CPF: " + dados[6] + ") associado ao ingresso na linha 
'" + linha + "' não foi encontrado.", e);
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Erro de persistência ao buscar sessão/cliente para o ingresso na linha: " + linha, e);
        }
    }

    // --- Métodos de Persistência (Implementados usando a classe Persistencia) ---

    /**
     * Insere o ingresso atual no arquivo TXT.
     * @return true sempre (lança exceção em caso de erro).
     * @throws PersistenciaException Se ocorrer um erro durante a escrita no arquivo ou se ID já existir.
     */
    public boolean inserir() throws PersistenciaException {
         try {
            consultar(this.idIngresso);
            throw new PersistenciaException("Erro ao inserir: Já existe um Ingresso com o ID " + this.idIngresso);
        } catch (NaoEncontradoException e) {
            // ID não existe, pode inserir
            Persistencia.inserirObjeto(NOME_ARQUIVO, this.toString());
            return true;
        } catch (PersistenciaException pe) {
             throw pe;
        }
    }

    /**
     * Edita os dados deste ingresso no arquivo TXT.
     * A identificação é feita pelo idIngresso.
     * @return true sempre (lança exceção em caso de erro ou se não encontrado).
     * @throws PersistenciaException Se ocorrer um erro durante a leitura/escrita no arquivo.
     * @throws NaoEncontradoException Se o ingresso com este ID não for encontrado para edição.
     */
    public boolean editar() throws PersistenciaException, NaoEncontradoException {
        return Persistencia.editarObjetoPorId(NOME_ARQUIVO, String.valueOf(this.idIngresso), this.toString(), SEPARADOR);
    }

    /**
     * Lista todos os ingressos do arquivo TXT.
     * Carrega a Sessao e o Cliente associados para cada ingresso.
     * @return Uma lista de Ingressos.
     * @throws PersistenciaException Se ocorrer um erro durante a leitura ou ao buscar/validar Sessao/Cliente.
     */
    public static ArrayList<Ingresso> listar() throws PersistenciaException {
        List<String> linhas = Persistencia.listarLinhas(NOME_ARQUIVO);
        ArrayList<Ingresso> listaIngressos = new ArrayList<>();
        for (String linha : linhas) {
            try {
                listaIngressos.add(fromString(linha));
            } catch (ValidacaoException | NaoEncontradoException e) {
                System.err.println("Erro ao processar linha do arquivo de ingressos ou buscar associações: " + linha + " - " + e.getMessage());
                // throw new PersistenciaException("Dado inválido ou Associação não encontrada no arquivo de ingressos: " + linha, e);
            }
        }
        return listaIngressos;
    }

    /**
     * Consulta um ingresso pelo seu ID no arquivo TXT.
     * Carrega a Sessao e o Cliente associados.
     * @param idIngresso O ID do ingresso a ser consultado.
     * @return O objeto Ingresso encontrado.
     * @throws PersistenciaException Se ocorrer um erro durante a leitura do arquivo ou na validação/busca das associações.
     * @throws NaoEncontradoException Se o ingresso, sua sessão ou cliente associado não for encontrado.
     */
    public static Ingresso consultar(int idIngresso) throws PersistenciaException, NaoEncontradoException {
        String linha = Persistencia.consultarLinhaPorId(NOME_ARQUIVO, String.valueOf(idIngresso), SEPARADOR);
        try {
            // O fromString já busca as associações
            return fromString(linha);
        } catch (ValidacaoException | NaoEncontradoException e) {
            throw new PersistenciaException("Erro ao validar dados ou buscar associações do ingresso consultado (ID: " + idIngresso + "): " + linha, e);
        }
    }

    // --- Fim dos Métodos de Persistência ---

    // Métodos equals e hashCode para comparações (baseado no idIngresso)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingresso ingresso = (Ingresso) o;
        return idIngresso == ingresso.idIngresso;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idIngresso);
    }
}

