class Person(val name: String, val age: Int)

fun printPersonDetails(person: Person) {
    println("Name: ${person.name}, Age: ${person.age}")
}

fun main() {
    val person = Person("John", 25)
    printPersonDetails(person)

    // Erro: passando um tipo incorreto para a função
    val incorrectAge: String = "Twenty-five"  // Erro: incompatibilidade de tipo
    printPersonDetails(incorrectAge)  // Erro: parâmetros incorretos

    // Erro ao tentar operar com tipos incorretos
    val number: Int = "Ten"  // Erro: incompatibilidade de tipo
    println(number)
}
