function sumOfSquaresImperative(arr) {
    let total = 0;
    for (let i = 0; i < arr.length; i++) {
        if (arr[i] % 2 === 0) {
            total += arr[i] * arr[i];
        }
    }
    return total;
}

function sumOfSquaresFunctional(arr) {
    return arr
        .filter(num => num % 2 === 0)
        .map(num => num * num)
        .reduce((acc, num) => acc + num, 0);
}
