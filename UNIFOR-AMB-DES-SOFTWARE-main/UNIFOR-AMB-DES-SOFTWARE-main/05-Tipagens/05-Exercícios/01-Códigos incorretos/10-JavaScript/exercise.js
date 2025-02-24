function processData(data) {
    if (typeof data !== "object" || !data.hasOwnProperty("value")) {
        throw new TypeError("Expected an object with a 'value' property");
    }
    console.log("Processed value:", data.value);
}

processData({ value: 42 });

processData("Invalid data");

let value = "10" / 2;
console.log(value);
