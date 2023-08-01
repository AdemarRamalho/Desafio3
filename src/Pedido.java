import java.util.ArrayList;

class Pedido {
    private ArrayList<Pizza> pizzas;
    private boolean encerrado;
    private Cliente cliente;

    public Pedido(Cliente cliente) {
        this.pizzas = new ArrayList<>();
        this.encerrado = false;
        this.cliente = cliente;
    }

    public void adicionarPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public boolean isEncerrado() {
        return encerrado;
    }

    public void setEncerrado(boolean encerrado) {
        this.encerrado = encerrado;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public String getEnderecoEntrega() {
        return cliente.getEnderecoEntrega();
    }

    @Override
    public String toString() {
        return "Cliente: " + cliente.getNome() + ", Pizzas: " + pizzas + ", Encerrado: " + encerrado;
    }
}