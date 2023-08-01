class Pizza {
    private String tamanho;
    private String sabor;

    public Pizza(String tamanho, String sabor) {
        this.tamanho = tamanho;
        this.sabor = sabor;
    }

    public String getTamanho() {
        return tamanho;
    }

    public String getSabor() {
        return sabor;
    }

    @Override
    public String toString() {
        return "Tamanho: " + tamanho + ", Sabor: " + sabor;
    }
}