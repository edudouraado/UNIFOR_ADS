public class App {
    public static void main (String [] args) throws Exception {
        Funcionario f2 = new Funcionario("Lucas", "123456789", 1000);
        Presidente p2 = new Presidente("Ana", "987654321", 2000, 1000);

        System.out.println("Funcionario 1: " + f2.getNome() + ", CPF: " + f2.getCpf() + ", Salario: " + f2.getSalario());
        System.out.println("Bonus Funcionario 1: " + f2.bonus());


        System.out.println("Presidente 1: " + p2.getNome() + ", CPF: " + p2.getCpf() + ", Salario: " + p2.getSalario() + ", Acoes: " + p2.getAcoes());
        System.out.println("Bonus Presidente 1: " + p2.bonus());
    }
}
