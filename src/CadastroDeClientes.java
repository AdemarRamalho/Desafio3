import java.util.ArrayList;

public class CadastroDeClientes {
    private ArrayList<Cliente> clientes;

    public CadastroDeClientes() {
        this.clientes = new ArrayList<>();
    }

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cliente buscarCliente(String nome) {
        for (Cliente cliente : clientes) {
            if (cliente.getNome().equalsIgnoreCase(nome)) {
                return cliente;
            }
        }
        return null;
    }

    public void alterarDados(Cliente cliente, String novoNome, String novoTelefone) {
        cliente.setNome(novoNome);
        cliente.setTelefone(novoTelefone);
    }

    public static String limparCaracteresInvalidos(String texto) {
        return texto.replaceAll("[\\:*?\"<>|]", "_");
    }
}
