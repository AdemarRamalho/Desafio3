import java.util.ArrayList;

class Pedido {
    private ArrayList<Pizza> pizzas;
    private boolean encerrado;
    private Cliente cliente;

    private Endereco enderecoEntrega;

    public Pedido(Cliente cliente, Endereco enderecoEntrega) {
        this.pizzas = new ArrayList<>();
        this.encerrado = false;
        this.cliente = cliente;
    }

    private Endereco enderecoEntrega() {
        return enderecoEntrega;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
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


    @Override
    public String toString() {
        return "Cliente: " + cliente.getNome() + ", Pizzas: " + pizzas + ", Encerrado: " + encerrado;
    }
}