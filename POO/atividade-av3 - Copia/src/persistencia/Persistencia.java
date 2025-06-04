package persistencia;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import excecoes.PersistenciaException;
import excecoes.NaoEncontradoException;

/**
 * Classe responsável pela leitura e escrita de dados em arquivos TXT.
 * Centraliza as operações de persistência para todas as entidades.
 */
public class Persistencia {

    /**
     * Escreve uma lista de strings em um arquivo, sobrescrevendo o conteúdo existente.
     *
     * @param nomeArquivo O caminho completo do arquivo.
     * @param linhas      A lista de strings a serem escritas.
     * @throws PersistenciaException Se ocorrer um erro de I/O durante a escrita.
     */
    public static void sobrescreverArquivo(String nomeArquivo, List<String> linhas) throws PersistenciaException {
        criarDiretorioSeNaoExistir(nomeArquivo);
        try {
            Files.write(Paths.get(nomeArquivo),
                        linhas,
                        StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE, // Cria o arquivo se não existir
                        StandardOpenOption.TRUNCATE_EXISTING); // Apaga o conteúdo antes de escrever
        } catch (IOException e) {
            throw new PersistenciaException("Erro ao sobrescrever o arquivo: " + nomeArquivo, e);
        }
    }

    /**
     * Adiciona uma nova linha ao final de um arquivo.
     *
     * @param nomeArquivo O caminho completo do arquivo.
     * @param linha       A string a ser adicionada como uma nova linha.
     * @throws PersistenciaException Se ocorrer um erro de I/O durante a escrita.
     */
    public static void adicionarLinha(String nomeArquivo, String linha) throws PersistenciaException {
        criarDiretorioSeNaoExistir(nomeArquivo);
        try {
            Files.write(Paths.get(nomeArquivo),
                        (linha + System.lineSeparator()).getBytes(StandardCharsets.UTF_8),
                        StandardOpenOption.CREATE, // Cria o arquivo se não existir
                        StandardOpenOption.APPEND); // Adiciona ao final do arquivo
        } catch (IOException e) {
            throw new PersistenciaException("Erro ao adicionar linha ao arquivo: " + nomeArquivo, e);
        }
    }

    /**
     * Lê todas as linhas de um arquivo TXT.
     *
     * @param nomeArquivo O caminho completo do arquivo.
     * @return Uma lista de strings, onde cada string é uma linha do arquivo.
     * @throws PersistenciaException Se ocorrer um erro de I/O durante a leitura ou se o arquivo não for encontrado.
     */
    public static List<String> lerArquivo(String nomeArquivo) throws PersistenciaException {
        if (!Files.exists(Paths.get(nomeArquivo))) {
             // Se o arquivo não existe (pode ser a primeira execução), retorna lista vazia
            return new ArrayList<>();
        }
        try {
            return Files.readAllLines(Paths.get(nomeArquivo), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new PersistenciaException("Erro ao ler o arquivo: " + nomeArquivo, e);
        }
    }

     /**
     * Cria o diretório pai do arquivo, se ele não existir.
     *
     * @param nomeArquivo O caminho completo do arquivo.
     * @throws PersistenciaException Se ocorrer um erro ao criar o diretório.
     */
    private static void criarDiretorioSeNaoExistir(String nomeArquivo) throws PersistenciaException {
        try {
            File arquivo = new File(nomeArquivo);
            File diretorioPai = arquivo.getParentFile();
            if (diretorioPai != null && !diretorioPai.exists()) {
                if (!diretorioPai.mkdirs()) {
                    throw new PersistenciaException("Não foi possível criar o diretório: " + diretorioPai.getAbsolutePath());
                }
            }
        } catch (SecurityException e) {
             throw new PersistenciaException("Erro de segurança ao tentar criar diretório para: " + nomeArquivo, e);
        }
    }

    // --- Métodos Genéricos para CRUD (Exemplos) ---
    // Estes métodos podem ser usados como base nas classes de modelo

    /**
     * Insere um objeto (representado por sua string formatada) no arquivo correspondente.
     *
     * @param nomeArquivo O arquivo onde inserir.
     * @param objetoString A representação em string do objeto a ser inserido.
     * @throws PersistenciaException Em caso de erro de I/O.
     */
    public static void inserirObjeto(String nomeArquivo, String objetoString) throws PersistenciaException {
        adicionarLinha(nomeArquivo, objetoString);
    }

    /**
     * Edita um objeto no arquivo, identificado por um ID (primeiro campo da linha).
     *
     * @param nomeArquivo O arquivo a ser editado.
     * @param idParaEditar O ID (como string) do objeto a ser editado.
     * @param novoObjetoString A nova representação em string do objeto.
     * @param separador O caractere separador usado no arquivo (ex: ';').
     * @return true se o objeto foi encontrado e editado, false caso contrário.
     * @throws PersistenciaException Em caso de erro de I/O.
     * @throws NaoEncontradoException Se o objeto com o ID especificado não for encontrado.
     */
    public static boolean editarObjetoPorId(String nomeArquivo, String idParaEditar, String novoObjetoString, String separador) throws PersistenciaException, NaoEncontradoException {
        List<String> linhas = lerArquivo(nomeArquivo);
        boolean encontrado = false;
        List<String> novasLinhas = new ArrayList<>();

        for (String linha : linhas) {
            if (linha.trim().isEmpty()) continue; // Ignora linhas vazias
            String[] campos = linha.split(separador, 2); // Divide apenas no primeiro separador
            if (campos.length > 0 && campos[0].equals(idParaEditar)) {
                novasLinhas.add(novoObjetoString); // Substitui pela nova linha
                encontrado = true;
            } else {
                novasLinhas.add(linha); // Mantém a linha original
            }
        }

        if (!encontrado) {
            throw new NaoEncontradoException("Objeto com ID " + idParaEditar + " não encontrado no arquivo " + nomeArquivo + " para edição.");
        }

        sobrescreverArquivo(nomeArquivo, novasLinhas);
        return true;
    }

     /**
     * Consulta (retorna a linha) de um objeto no arquivo pelo seu ID (primeiro campo).
     *
     * @param nomeArquivo O arquivo onde consultar.
     * @param idParaConsultar O ID (como string) do objeto a ser consultado.
     * @param separador O caractere separador usado no arquivo (ex: ';').
     * @return A linha correspondente ao objeto encontrado.
     * @throws PersistenciaException Em caso de erro de I/O.
     * @throws NaoEncontradoException Se o objeto com o ID não for encontrado.
     */
    public static String consultarLinhaPorId(String nomeArquivo, String idParaConsultar, String separador) throws PersistenciaException, NaoEncontradoException {
        List<String> linhas = lerArquivo(nomeArquivo);
        for (String linha : linhas) {
            if (linha.trim().isEmpty()) continue;
            String[] campos = linha.split(separador, 2);
            if (campos.length > 0 && campos[0].equals(idParaConsultar)) {
                return linha;
            }
        }
        throw new NaoEncontradoException("Objeto com ID " + idParaConsultar + " não encontrado no arquivo " + nomeArquivo + ".");
    }

    /**
     * Lista todas as linhas não vazias de um arquivo.
     *
     * @param nomeArquivo O arquivo a ser listado.
     * @return Uma lista com as linhas do arquivo.
     * @throws PersistenciaException Em caso de erro de I/O.
     */
    public static List<String> listarLinhas(String nomeArquivo) throws PersistenciaException {
        return lerArquivo(nomeArquivo).stream()
                                     .filter(linha -> !linha.trim().isEmpty())
                                     .collect(Collectors.toList());
    }
}

