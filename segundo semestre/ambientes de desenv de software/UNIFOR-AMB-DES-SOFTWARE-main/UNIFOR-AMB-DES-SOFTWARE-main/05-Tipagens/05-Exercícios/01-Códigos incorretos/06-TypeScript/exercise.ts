class Person {
    constructor(public name: string, public age: number) {}
}

function greet(person: Person) {
    console.log(`Hello, ${person.name}. You are ${person.age} years old.`);
}

const person = new Person("Alice", 30);
greet(person);

const notPerson: string = "This is a string";
greet(notPerson);

const number: number = "This is a string";
