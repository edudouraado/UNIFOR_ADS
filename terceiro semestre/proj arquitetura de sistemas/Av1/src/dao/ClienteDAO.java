package dao;

import model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private List<Cliente> clientes = new ArrayList<>();

    public void adicionar(Cliente cliente) {
        clientes.add(cliente);
    }

    public List<Cliente> listarTodos() {
        return clientes;
    }

    public Cliente buscarPorCpf(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }

    public void atualizar(Cliente cliente) {
        int index = -1;
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getCpf().equals(cliente.getCpf())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            clientes.set(index, cliente);
        }
    }

    public void remover(String cpf) {
        clientes.removeIf(c -> c.getCpf().equals(cpf));
    }
}