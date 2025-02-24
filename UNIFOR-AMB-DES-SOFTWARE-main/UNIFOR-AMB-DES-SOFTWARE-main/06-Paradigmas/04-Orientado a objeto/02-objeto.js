class Pessoa {
    constructor(nome, idade) {
        this.nome = nome;
        this.idade = idade;
    }

    apresentar() {
        return `Olá, meu nome é ${this.nome} e eu tenho ${this.idade} anos.`;
    }
}

const pessoa1 = new Pessoa('Alice', 30);
const pessoa2 = new Pessoa('Bruno', 25);

console.log(pessoa1.apresentar());
console.log(pessoa2.apresentar());


