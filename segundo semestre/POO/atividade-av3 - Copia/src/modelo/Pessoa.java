package modelo;

import java.util.Objects;

/**
 * Classe abstrata que representa uma Pessoa no sistema.
 * Contém atributos e métodos comuns a Ator e Cliente.
 */
public abstract class Pessoa {
    protected String cpf; // CPF pode conter pontos e traço, melhor String
    protected String nome;
    protected int idade;

    /**
     * Construtor da classe Pessoa.
     *
     * @param cpf   CPF da pessoa.
     * @param nome  Nome da pessoa.
     * @param idade Idade da pessoa.
     */
    public Pessoa(String cpf, String nome, int idade) {
        // Validações básicas podem ser adicionadas aqui se necessário
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
    }

    // Getters
    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    // Setters (permitindo modificação se necessário)
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    /**
     * Método abstrato para exibir os detalhes da pessoa.
     * As subclasses devem implementar este método.
     */
    public abstract void mostrar();

    @Override
    public String toString() {
        return "Pessoa{" +
               "cpf='" + cpf + '\'' +
               ", nome='" + nome + '\'' +
               ", idade=" + idade +
               '}';
    }

    // Métodos equals e hashCode para comparações e uso em coleções
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(cpf, pessoa.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}

