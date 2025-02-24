class Pessoa {
    constructor(nome) {
        if (new.target === Pessoa) {
            throw new Error("Não é possível instanciar a classe Pessoa diretamente.");
        }
        this.nome = nome;
    }

    apresentar() {
        throw new Error("Método 'apresentar()' deve ser implementado pela subclasse.");
    }
}

export default Pessoa;

