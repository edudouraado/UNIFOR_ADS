import Pessoa from "./pessoa";

class Funcionario extends Pessoa {
    constructor(nome, cargo) {
        super(nome);
        this.cargo = cargo;
    }

    apresentar() {
        return `Olá, meu nome é ${this.nome} e eu sou ${this.cargo}.`;
    }
}

class Estudante extends Pessoa {
    constructor(nome, curso) {
        super(nome);
        this.curso = curso;
    }

    apresentar() {
        return `Olá, meu nome é ${this.nome} e eu estudo ${this.curso}.`;
    }
}


const funcionario = new Funcionario('Carlos', 'Gerente');
const estudante = new Estudante('Ana', 'Engenharia');

console.log(funcionario.apresentar()); // Output: Olá, meu nome é Carlos e eu sou Gerente.
console.log(estudante.apresentar());   // Output: Olá, meu nome é Ana e eu estudo Engenharia.
