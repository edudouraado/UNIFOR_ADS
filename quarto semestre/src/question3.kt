//Questão 3
class Pessoa {
    var name: String = ""
    var age: Int = 0
    var profissao: String = ""

    constructor(name: String, age: Int, profissao: String) {
        this.name = name
        this.age = age
        this.profissao = profissao
    }
}
fun main() {
    var p1 = Pessoa("Eduardo", 25, "Analista")
    var p2 = Pessoa("Ana Kelly", 22, "Desenvolvedora")
    var p3 = Pessoa("Leandro", 20, "Desempregado")
    println("A pessoa ${p1.name} tem ${p1.age} anos de idade e sua profissao atual é ${p1.profissao}")
    println("A pessoa ${p2.name} tem ${p2.age} anos de idade e sua profissao atual é ${p2.profissao}")
    println("A pessoa ${p3.name} tem ${p3.age} anos de idade e sua profissao atual é ${p3.profissao}")
}