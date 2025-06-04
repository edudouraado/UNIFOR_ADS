package excecoes;

/**
 * Exceção lançada quando o usuário digita uma opção inválida no menu.
 */
public class MenuOpcaoInvalidaException extends Exception {
    public MenuOpcaoInvalidaException(String message) {
        super(message);
    }
}

