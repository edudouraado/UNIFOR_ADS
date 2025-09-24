function fibonacciRecursive(n) {
    if (n === 0) return 0;
    if (n === 1) return 1;
    return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
}

function fibonacciIterative(n) {
    if (n === 0) return 0;
    if (n === 1) return 1;

    let a = 0, b = 1, result;
    for (let i = 2; i <= n; i++) {
        result = a + b;
        a = b;
        b = result;
    }
    return result;
}

function measureExecutionTime(func, n) {
    const start = performance.now();
    const result = func(n);
    const end = performance.now();
    const time = end - start;
    return { result, time };
}

const n = 8;

const recursiveResult = measureExecutionTime(fibonacciRecursive, n);
console.log(`Fibonacci Recursivo de ${n}: ${recursiveResult.result}, Tempo: ${recursiveResult.time.toFixed(4)}ms`);

const iterativeResult = measureExecutionTime(fibonacciIterative, n);
console.log(`Fibonacci Iterativo de ${n}: ${iterativeResult.result}, Tempo: ${iterativeResult.time.toFixed(4)}ms`);