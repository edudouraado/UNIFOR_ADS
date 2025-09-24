function factorialImperative(n) {
    let result = 1;
    for (let i = 1; i <= n; i++) {
        result *= i;
    }
    return result;
}

function factorialFunctional(n) {
    return n <= 1 ? 1 : n * factorialFunctional(n - 1);
}
