import java.util.Random

fun main() {
    val random = Random()
    var pares = 0
    var impares = 0

    for (i in 1..10) {
        val numero = random.nextInt(50) + 1
        println(numero)

        if (numero % 2 == 0) {
            pares++
        } else {
            impares++
        }

    println("Total de números pares: $pares")
    println("Total de número impares: $impares")
    }
}