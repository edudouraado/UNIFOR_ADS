function calculateTotal(price, tax) {
    return price + tax;
}

let itemPrice = 100;
let taxRate = "0.2";
let total = calculateTotal(itemPrice, taxRate);
console.log("Total price: " + total);

let obj = { name: "Alice", age: 30 };
console.log(obj.salary);

let result = "10" * 2;
console.log(result);
