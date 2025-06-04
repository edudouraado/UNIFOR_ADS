package excecoes;

/**
 * Exceção lançada quando ocorre um erro durante a persistência de dados (leitura/escrita de arquivos).
 */
public class PersistenciaException extends Exception {
    public PersistenciaException(String message, Throwable cause) {
        super(message, cause);
    }
     public PersistenciaException(String message) {
        super(message);
    }
}

