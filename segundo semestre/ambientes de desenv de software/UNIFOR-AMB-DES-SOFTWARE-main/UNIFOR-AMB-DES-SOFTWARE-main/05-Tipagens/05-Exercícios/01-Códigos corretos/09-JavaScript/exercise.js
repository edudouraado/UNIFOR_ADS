function checkEquality(a, b) {
    if (a == b) {
        console.log("Equal");
    } else {
        console.log("Not equal");
    }
}

checkEquality("5", 5);  // Erro: comparação pode ser inesperada devido à coerção de tipos

let result = "10" - 2;  // Coerção implícita, resultado esperado é 8, mas pode causar confusão
console.log(result);

// Problema com concatenação e tipos
let text = "The result is: " + (10 - 2);  // Correto, mas pode ser confuso
console.log(text);
