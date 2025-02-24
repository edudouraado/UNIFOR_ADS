function fatorialRecursivo(number) {
    if (number === 0 || number === 1) {
        return 1;
    };
    return number * fatorialRecursivo(number - 1);
}

const fatorial = fatorialRecursivo(5);
console.log(fatorial);