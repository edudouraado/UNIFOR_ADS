class Funcionario extends Pessoa {
    constructor(nome, idade, cargo) {
        super(nome, idade);
        this.cargo = cargo;
    }

    apresentar() {
        return `Olá, meu nome é ${this.nome}, eu tenho ${this.getIdade()} anos e sou ${this.cargo}.`;
    }
}

const funcionario1 = new Funcionario('Carlos', 40, 'Gerente');
console.log(funcionario1.apresentar());
