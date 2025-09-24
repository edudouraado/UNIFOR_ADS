package controller;

import dao.ClienteDAO;
import model.Cliente;

public class ClienteController {
    private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    public void criarCliente(String cpf, String nome, int idade, String rg, boolean estudante) {
        Cliente cliente = new Cliente(cpf, nome, idade, rg, estudante); // GRASP Creator
        clienteDAO.adicionar(cliente);
    }

    public void listarClientes() {
        for (Cliente c : clienteDAO.listarTodos()) {
            c.mostrar();
        }
    }

    public void atualizarCliente(String cpf, String novoNome, int novaIdade, String novoRg, boolean novoEstudante) {
        Cliente cliente = clienteDAO.buscarPorCpf(cpf);
        if (cliente != null) {
            cliente.setNome(novoNome);
            cliente.setIdade(novaIdade);
            cliente.setRg(novoRg);
            cliente.setEstudante(novoEstudante);
            clienteDAO.atualizar(cliente);
        }
    }

    public void removerCliente(String cpf) {
        clienteDAO.remover(cpf);
    }
}