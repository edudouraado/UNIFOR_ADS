package dao;

import model.Filme;
import java.util.ArrayList;
import java.util.List;

public class FilmeDAO {
    private List<Filme> filmes = new ArrayList<>();

    public void adicionar(Filme filme) {
        filmes.add(filme);
    }

    public List<Filme> listarTodos() {
        return filmes;
    }

    public Filme buscarPorId(int id) {
        for (Filme f : filmes) {
            if (f.getIdFilme() == id) {
                return f;
            }
        }
        return null;
    }

    public void atualizar(Filme filme) {
        // Lógica para atualizar (pode ser substituída por ID ou outro critério)
        int index = -1;
        for (int i = 0; i < filmes.size(); i++) {
            if (filmes.get(i).getIdFilme() == filme.getIdFilme()) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            filmes.set(index, filme);
        }
    }

    public void remover(int id) {
        filmes.removeIf(f -> f.getIdFilme() == id);
    }
}