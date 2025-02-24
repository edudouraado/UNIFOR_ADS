package primeiraAula;

import java.time.Year;

public class pessoa {
    private String nome;
    private int anoNascimento;
    private double peso;
    private double altura;

    public pessoa(String nome, int anoNascimento, double peso, double altura) {
        this.nome = nome;
        this.anoNascimento = anoNascimento;
        this.peso = peso;
        this.altura = altura;
    }

    public double calcularIMC() {
        return peso / (altura * altura);
    }

    public int calcularIdade() {
        int anoAtual = Year.now().getValue();
        return anoAtual - anoNascimento;
    }

    public double calcularAguaDiaria() {
        return peso * 0.035;
    }

    public void mostra() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + calcularIdade() + " anos");
        System.out.println("Peso: " + peso + " kg");
        System.out.println("Altura: " + altura + " m");
        System.out.printf("IMC: %.2f\n", calcularIMC());
        System.out.printf("Necessidade de água diária: %.2f litros\n", calcularAguaDiaria());
    }

    public String getNome() {
        return nome;
    }

    public double getIMC() {
        return calcularIMC();
    }
}
