package excecoes;

/**
 * Exceção lançada quando ocorre um erro interno inesperado no sistema.
 */
public class ErroInternoException extends RuntimeException { // Usando RuntimeException para erros inesperados
    public ErroInternoException(String message, Throwable cause) {
        super(message, cause);
    }
     public ErroInternoException(String message) {
        super(message);
    }
}

