package excecoes;

/**
 * Exceção lançada quando ocorre um erro de validação de dados.
 */
public class ValidacaoException extends Exception {
    
    /**
     * Construtor que aceita apenas uma mensagem.
     * @param message A mensagem de erro.
     */
    public ValidacaoException(String message) {
        super(message);
    }

    /**
     * Construtor que aceita uma mensagem e a causa original do erro.
     * @param message A mensagem de erro.
     * @param cause A exceção original que causou a falha na validação (e.g., NumberFormatException).
     */
    public ValidacaoException(String message, Throwable cause) {
        super(message, cause);
    }
}

