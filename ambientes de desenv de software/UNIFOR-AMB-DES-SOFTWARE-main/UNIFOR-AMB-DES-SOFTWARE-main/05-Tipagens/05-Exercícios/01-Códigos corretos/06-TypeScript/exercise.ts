class Person {
    constructor(public name: string, public age: number) {}
}

function greet(person: Person) {
    console.log(`Hello, ${person.name}. You are ${person.age} years old.`);
}

const person = new Person("Alice", 30);
greet(person);

// Erro: o tipo fornecido não é compatível com Person
const notPerson: string = "This is a string";
greet(notPerson);  // Erro: o tipo fornecido não é compatível

// Erro ao usar tipos incompatíveis
const number: number = "This is a string";  // Erro: incompatibilidade de tipo
