function fibonacciImperative(n) {
    let a = 0, b = 1, temp;
    for (let i = 0; i < n; i++) {
        temp = a;
        a = b;
        b = temp + b;
    }
    return a;
}
function fibonacciFunctional(n) {
    if (n <= 1) return n;
    return fibonacciFunctional(n - 1) + fibonacciFunctional(n - 2);
}
