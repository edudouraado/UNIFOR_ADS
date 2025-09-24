public class AcademiaApp {
    public static void main(String[] args) {
        Academia academia = new Academia("Smart Fit");

        academia.cadastrarCliente(new Cliente(1, "João", 1985, "9999-1111"));
        academia.cadastrarCliente(new Cliente(2, "Maria", 2001, "9999-2222"));
        academia.cadastrarCliente(new Cliente(3, "Carlos", 1995, "9999-3333"));
        academia.cadastrarCliente(new Cliente(4, "Ana", 2005, "9999-4444"));

        System.out.println("Clientes cadastrados inicialmente:");
        academia.listarClientes();

        academia.removerClientePorId(3);

        System.out.println("\nApós remover cliente com ID 3:");
        academia.listarClientes();

        System.out.println("\nClientes nascidos após 2000:");
        for (Cliente c : academia.clientesApos2000()) {
            System.out.println(c);
        }

        academia.removerClientesAntes1990();

        System.out.println("\nApós remover clientes nascidos antes de 1990:");
        academia.listarClientes();

        System.out.println("\nNúmero total de clientes cadastrados: " + academia.numeroDeClientes());
    }
}
