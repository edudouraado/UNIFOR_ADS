class Estudante extends Pessoa {
    constructor(nome, idade, curso) {
        super(nome, idade);
        this.curso = curso;
    }

    apresentar() {
        return `Olá, meu nome é ${this.nome}, eu tenho ${this.getIdade()} anos e estudo ${this.curso}.`;
    }
}

const estudante1 = new Estudante('Ana', 22, 'Engenharia');
const funcionario1 = new Funcionario('Carlos', 40, 'Gerente');

console.log(estudante1.apresentar()); // Output: Olá, meu nome é Ana, eu tenho 22 anos e estudo Engenharia.
console.log(funcionario1.apresentar()); // Output: Olá, meu nome é Carlos, eu tenho 40 anos e sou Gerente.
