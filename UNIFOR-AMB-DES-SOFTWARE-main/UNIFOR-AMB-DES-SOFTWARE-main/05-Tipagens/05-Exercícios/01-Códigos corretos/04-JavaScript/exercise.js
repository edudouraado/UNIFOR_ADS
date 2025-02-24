function calculateTotal(price, tax) {
    return price + tax;  // Espera-se que tax seja um número
}

let itemPrice = 100;
let taxRate = "0.2";  // Erro: string ao invés de número
let total = calculateTotal(itemPrice, taxRate);
console.log("Total price: " + total);

let obj = { name: "Alice", age: 30 };
console.log(obj.salary);  // Erro: 'salary' não existe no objeto

// Problema de coerção com operadores
let result = "10" * 2;  // Coerção implícita, resultado esperado é 20, mas pode causar confusão
console.log(result);
