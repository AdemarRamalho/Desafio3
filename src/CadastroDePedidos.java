import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CadastroDePedidos {
    private ArrayList<Pedido> pedidos;

    public CadastroDePedidos(){
        this.pedidos = new ArrayList<>();
    }
    public void fazerPedido(Pedido pedido){
        pedidos.add(pedido);
    }
    public void encerrarPedido(Pedido pedido){
        pedido.setEncerrado(true);
        try {
            gerarNotaEntrega(pedido);
            System.out.println("Arquivo de nota de entrega gerado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao escrever o arquivo de nota de entrega: " + e.getMessage());
        }
    }

    public ArrayList<Pedido> getPedidos(){
        return pedidos;
    }

     public int getNumeroDePedidos(){
        return pedidos.size();
     }
     public int getNumeroDePedidosEncerrados(){
        int x =0;
        for (Pedido pedido : pedidos){
            if(pedido.isEncerrado()){
                x++;
            }
        }
        return x;
     }

     public int getNumeroDePedidosEmAtendimento(){
        int x=0;
        for (Pedido pedido : pedidos){
            if(!pedido.isEncerrado()){
                x++;
            }
        }
         return x;
     }
    private String limparCaracteresInvalidos(Cliente input) {
        return input.replaceAll("[\\\\/:*?\"<>|]", "_");
    }
     public void notaDeEntrega(Pedido pedido, String enderecoEntrega){
        String Notas = "Pedido_" + limparCaracteresInvalidos(pedido.getCliente()) + ".txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(Notas))){
            writer.println("Pedido Encerrado - Entrega para: " + pedido.getCliente().getNome());
            writer.println("Endereço de Entrega: " + enderecoEntrega);
            writer.println("Descrição do pedido: " + pedido.getPizzas());
        }catch (IOException e){
            System.err.println("Erro ao escrever o arquivo " + e.getMessage());
        }
     }

    private void gerarArquivoPedido(Pedido pedido) {
        try {
            String nomeCliente = pedido.getCliente().getNome();
            String enderecoCliente = pedido.getCliente().getEnderecoEntrega().getRua() + ", Número: " + pedido.getCliente().getEnderecoEntrega().getNumeroCasa();

            String nomeArquivo = "pedido_" + CadastroDeClientes.limparCaracteresInvalidos(nomeCliente) + ".txt";
            FileWriter fileWriter = new FileWriter(nomeArquivo);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println("Nome do cliente: " + nomeCliente);
            printWriter.println("Endereço: " + enderecoCliente);
            printWriter.println("Pedido:");

            int numPizza = 1;
            for (Pizza pizza : pedido.getPizzas()) {
                printWriter.println("Pizza " + numPizza + ": Tamanho " + pizza.getTamanho() + ", Sabor " + pizza.getSabor());
                numPizza++;
            }

            printWriter.close();
        } catch (IOException e) {
        }
    }
    private void gerarNotaEntrega(Pedido pedido) throws IOException {
        String nomeCliente = pedido.getCliente().getNome();
        String enderecoCliente = pedido.getCliente().getEnderecoEntrega().getRua() + ", Número: " + pedido.getCliente().getEnderecoEntrega().getNumeroCasa();

        String nomeArquivo = "pedido_" + CadastroDeClientes.limparCaracteresInvalidos(nomeCliente) + "_nota_entrega.txt";
        nomeArquivo = nomeArquivo.replaceAll("[\\:*?\"<>|]", "_");
        FileWriter fileWriter = new FileWriter(nomeArquivo);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println("Nota de Entrega");
        printWriter.println("Nome do cliente: " + nomeCliente);
        printWriter.println("Endereço: " + enderecoCliente);
        printWriter.println("Pedido:");

        int numPizza = 1;
        for (Pizza pizza : pedido.getPizzas()) {
            printWriter.println("Pizza " + numPizza + ": Tamanho " + pizza.getTamanho() + ", Sabor " + pizza.getSabor());
            numPizza++;
        }

        printWriter.close();
    }
}
