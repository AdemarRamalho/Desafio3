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
    private String limparCaracteresInvalidos(String input) {
        return input.replaceAll("[\\\\/:*?\"<>|]", "_");
    }
     public void notaDeEntrega(Pedido pedido, String enderecoEntrega){
        String Notas = "Pedido_" + limparCaracteresInvalidos(String.valueOf(pedidos)) + ".txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(Notas))){
            writer.println("Pedido Encerrado - Entrega para: " + pedido.getCliente().getNome());
            writer.println("Endereço de Entrega: " + enderecoEntrega);
            writer.println("Descrição do pedido: " + pedido.getPizzas());
        }catch (IOException e){
            System.err.println("Erro ao escrever o arquivo " + e.getMessage());
        }
     }
}
