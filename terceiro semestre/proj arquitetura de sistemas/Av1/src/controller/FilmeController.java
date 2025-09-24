package controller;

import dao.FilmeDAO;
import model.Filme;
import model.Genero;

public class FilmeController {
    private FilmeDAO filmeDAO;

    public FilmeController() {
        this.filmeDAO = new FilmeDAO();
    }

    // CREATE
    public void criarFilme(int id, String titulo, int duracao, Genero genero) {
        Filme filme = new Filme(id, titulo, duracao, genero); // GRASP Creator
        filmeDAO.adicionar(filme);
    }

    // READ
    public void listarFilmes() {
        for (Filme f : filmeDAO.listarTodos()) {
            f.mostrar();
        }
    }

    // UPDATE
    public void atualizarFilme(int id, String novoTitulo, int novaDuracao, Genero novoGenero) {
        Filme filme = filmeDAO.buscarPorId(id);
        if (filme != null) {
            filme.setTitulo(novoTitulo);
            filme.setDuracao(novaDuracao);
            filme.setGenero(novoGenero);
            filmeDAO.atualizar(filme);
        }
    }

    // DELETE
    public void removerFilme(int id) {
        filmeDAO.remover(id);
    }
}