import java.util.ArrayList;

public class Academia {
    private ArrayList<Cliente> clientes;

    public Academia(String nome) {
        this.clientes = new ArrayList<>();
    }

    public void cadastrarCliente(Cliente c) {
        clientes.add(c);
    }

    public boolean removerClientePorId(int id) {
        return clientes.removeIf(c -> c.getId() == id);
    }

    public int numeroDeClientes() {
        return clientes.size();
    }

    public ArrayList<Cliente> clientesApos2000() {
        ArrayList<Cliente> resultado = new ArrayList<>();
        for (Cliente c : clientes) {
            if (c.getAnoNascimento() > 2000) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public void removerClientesAntes1990() {
        clientes.removeIf(c -> c.getAnoNascimento() < 1990);
    }

    public void listarClientes() {
        for (Cliente c : clientes) {
            System.out.println(c);
        }
    }
}