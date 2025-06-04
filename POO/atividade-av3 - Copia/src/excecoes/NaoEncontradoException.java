package excecoes;

/**
 * Exceção lançada quando um objeto não é encontrado durante uma consulta.
 */
public class NaoEncontradoException extends Exception {
    public NaoEncontradoException(String message) {
        super(message);
    }
}

