public class Endereco {
    // Atributos
    private String rua;
    private String numeroCasa; // Novo atributo para armazenar o número da casa

    // Construtor
    public Endereco(String rua, String numeroCasa) {
        this.rua = rua;
        this.numeroCasa = numeroCasa;
    }

    // Getters e Setters
    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }
}