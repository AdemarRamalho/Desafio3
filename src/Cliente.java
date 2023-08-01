import java.util.ArrayList;

class Cliente {
    private String nome;
    private String telefone;
    private ArrayList<String> enderecos;

    public Cliente(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
        this.enderecos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public ArrayList<String> getEnderecos() {
        return enderecos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void addEndereco(String endereco) {
        enderecos.add(endereco);
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Telefone: " + telefone + ", Endereços: " + enderecos;
    }
    public String getEnderecoEntrega() {
        if (!enderecos.isEmpty()) {
            return enderecos.get(0);
        } else {
            return null;
        }
    }
}