import java.util.ArrayList;
import java.util.List;

class Cliente {
    private String nome;
    private String telefone;
    private ArrayList<Endereco> enderecos;

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


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void addEndereco(Endereco endereco) {
        enderecos.add(endereco);
    }
    public List<Endereco> getEnderecos(){
        return enderecos;
    }
    public Endereco getEnderecoEntrega() {
        if (enderecos.isEmpty()) {
            return null;
        }
        return enderecos.get(0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(nome).append(", Telefone: ").append(telefone).append(", Endereços: ");
        for (Endereco endereco : enderecos) {
            sb.append(endereco).append("; ");
        }
        return sb.toString();
    }


    public String replaceAll(String s, String s1) {

        return s;
    }

}