class MyClass {
    static func main() {
        let number = 3.147367826432
        let integerPart = Int(number)
        let rightResult = Int(number) + integerPart
        let wrongResult = number + Double(integerPart)
    }
}


