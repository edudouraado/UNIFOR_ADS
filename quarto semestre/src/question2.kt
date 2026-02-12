//Questão 2
import kotlin.random.Random

fun main(){
    var dado = Random.nextInt(0,7)
    println("O número que caiu no dado foi $dado")
    if(dado == 0)
        println("Erro Crítico")
}