function factorialRecursive(number) {
    if (number === 0 || number === 1) {
        return 1;
    };
    return number * factorialRecursive(number - 1);
}

function factorialIterative(number) {
    let finalResult = number;
    for (let index = number - 1; index > 1; index--) {
        finalResult *= index;
    }
    return finalResult;
}

function measureExecutionTime(functionToExecute, number) {
    const start = performance.now();
    const result = functionToExecute(number);
    const end = performance.now();
    const time = end - start;
    return { result, time };
}

const n = 5;

const recursiveResult = measureExecutionTime(factorialRecursive, n);
console.log(`Fatorial Recursivo de ${n}: ${recursiveResult.result}, Tempo: ${recursiveResult.time.toFixed(4)}ms`);

const iterativeResult = measureExecutionTime(factorialIterative, n);
console.log(`Fatorial Iterativo de ${n}: ${iterativeResult.result}, Tempo: ${iterativeResult.time.toFixed(4)}ms`);
