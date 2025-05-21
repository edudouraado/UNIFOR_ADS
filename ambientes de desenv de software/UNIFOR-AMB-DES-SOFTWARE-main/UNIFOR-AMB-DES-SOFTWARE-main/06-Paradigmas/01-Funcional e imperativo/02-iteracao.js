function fatorialIterativo(number) {
    let finalResult = number;
    for (let index = number - 1; index > 1; index--) {
        finalResult *= index;
    }
    return finalResult;
}

const fatorial = fatorialIterativo(5);
console.log(fatorial);