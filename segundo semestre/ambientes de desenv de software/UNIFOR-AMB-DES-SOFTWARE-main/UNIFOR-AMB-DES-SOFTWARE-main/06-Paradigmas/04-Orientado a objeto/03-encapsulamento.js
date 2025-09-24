class Pessoa {
    #idade;
    _peso;

    constructor(nome, idade, peso) {
        this.nome = nome;
        this.#idade = idade;
        this._peso = peso;
    }

    getIdade() {
        return this.#idade;
    }
    setIdade(novaIdade) {
        if (novaIdade > 0) {
            this.#idade = novaIdade;
        }
    }
    apresentar() {
        return `Olá, meu nome é ${this.nome} e eu tenho ${this.#idade} anos.`;
    }
}

const pessoa1 = new Pessoa('Alice', 30, 60);
console.log(pessoa1.apresentar());

pessoa1.setIdade(35);
console.log(pessoa1.apresentar());

console.log(pessoa1.getIdade()); 
