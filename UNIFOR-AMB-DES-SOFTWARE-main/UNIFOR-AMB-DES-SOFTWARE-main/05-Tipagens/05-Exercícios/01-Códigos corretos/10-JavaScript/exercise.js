function processData(data) {
    if (typeof data !== "object" || !data.hasOwnProperty("value")) {
        throw new TypeError("Expected an object with a 'value' property");
    }
    console.log("Processed value:", data.value);
}

processData({ value: 42 });  // Correto

// Erro: tipo fornecido não é um objeto com a propriedade 'value'
processData("Invalid data");  // Erro: TypeError

// Problema com tipos e operações
let value = "10" / 2;  // Coerção implícita, resultado é 5
console.log(value);
