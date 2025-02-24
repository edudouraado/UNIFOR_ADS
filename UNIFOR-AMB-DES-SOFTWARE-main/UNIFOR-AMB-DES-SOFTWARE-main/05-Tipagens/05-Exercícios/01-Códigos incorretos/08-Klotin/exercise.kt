class Person(val name: String, val age: Int)

fun printPersonDetails(person: Person) {
    println("Name: ${person.name}, Age: ${person.age}")
}

fun main() {
    val person = Person("John", 25)
    printPersonDetails(person)

    val incorrectAge: String = "Twenty-five"
    printPersonDetails(incorrectAge)

    val number: Int = "Ten"
    println(number)
}
