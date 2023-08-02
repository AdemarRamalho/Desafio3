import java.util.Scanner;
import java.util.SortedMap;

public class Main {
    public static void main(String[] args) {
    CadastroDeClientes cadastroDeClientes = new CadastroDeClientes();
    CadastroDePedidos cadastroDePedidos = new CadastroDePedidos();
    Scanner scanner = new Scanner(System.in);

    while (true){
        System.out.println("Menu de Opções:");
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Realizar Pedido");
        System.out.println("3 - Buscar Cliente");
        System.out.println("4 - Alterar Dados do Cliente");
        System.out.println("5 - Acompanhar Pedidos");
        System.out.println("6 - Encerrar pedidos");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                System.out.print("Digite o nome: ");
                String nome = scanner.nextLine();
                System.out.print("Digite o telefone: ");
                String telefone = scanner.nextLine();

                Cliente cliente = new Cliente(nome, telefone);

                while (true) {
                    System.out.println("Digite ao menos um endereço do cliente (ou digite 'concluir' para concluir o cadastro):");
                    String endereco = scanner.nextLine();

                    if (endereco.equalsIgnoreCase("concluir")) {
                        break;
                    }
                    cliente.addEndereco(endereco);
                }
                cadastroDeClientes.cadastrarCliente(cliente);
                System.out.println("Cliente cadastrado com sucesso!");
                break;

            case 2:
                System.out.print("Digite o nome do cliente: ");
                String nomeClientePedido = scanner.nextLine();
                Cliente clienteDoPedido = cadastroDeClientes.buscarCliente(nomeClientePedido);

                if (clienteDoPedido != null) {
                    Pedido pedido = new Pedido(clienteDoPedido);
                    boolean encerrarPedido = false;

                    while (!encerrarPedido) {
                        System.out.println("\nEscolha o tamanho da pizza:");
                        System.out.println("1 - Grande");
                        System.out.println("2 - Média");
                        System.out.println("3 - Pequena");
                        System.out.println("0 - Encerrar Pedido");
                        System.out.print("Escolha uma opção: ");
                        int tamanhoPizza = scanner.nextInt();
                        scanner.nextLine();
                        if (tamanhoPizza == 0) {
                            encerrarPedido = true;

                        } else if (tamanhoPizza >= 1 && tamanhoPizza < 3) {
                            String tamanho = null;
                            switch (tamanhoPizza) {
                                case 1:
                                    tamanho = "Grande";
                                    break;
                                case 2:
                                    tamanho = "Média";
                                    break;
                                case 3:
                                    tamanho = "Pequena";
                                    break;
                                default:
                                    System.out.println("Opcção invalida");
                            }
                            System.out.println("\nEscolha o sabor da pizza:");
                            System.out.println("1 - Frango");
                            System.out.println("2 - Queijo");
                            System.out.print("Escolha uma opção: ");
                            int saborPizza = scanner.nextInt();
                            scanner.nextLine();

                            if (saborPizza == 1) {
                                Pizza pizza = new Pizza(tamanho, "Frango");
                                pedido.adicionarPizza(pizza);
                            } else if (saborPizza == 2) {
                                Pizza pizza = new Pizza(tamanho, "Queijo");
                                pedido.adicionarPizza(pizza);
                            } else {
                                System.out.println("Opção inválida. Tente novamente.");
                            }

                                encerrarPedido = true;
                        }
                        cadastroDePedidos.fazerPedido(pedido);
                        System.out.println("Pedido realizado com sucesso!");
                    }
                }else{
                    System.out.println("Cliente não encontrado");
                }
                break;

            case 3:
                System.out.print("Digite o cliente que deseja Buscar: ");
                String nomeBuscado = scanner.nextLine();
                Cliente clienteEncontrado = cadastroDeClientes.buscarCliente(nomeBuscado);

                if (clienteEncontrado != null) {
                    System.out.println("Dados do cliente buscado");
                    System.out.println(clienteEncontrado);
                } else {
                    System.out.println("Cliente não encontrado.");
                }
                break;

            case 4:
                System.out.print("Digite o cliente que deseja alterar os dados: ");
                String nomeAlterar = scanner.nextLine();
                Cliente clienteParaAlterar = cadastroDeClientes.buscarCliente(nomeAlterar);

                if (clienteParaAlterar != null) {
                    System.out.println("Cliente encontrado");
                    System.out.println(clienteParaAlterar);

                    System.out.print("Digite o novo nome do cliente: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Digite o novo telefone do cliente: ");
                    String novoTelefone = scanner.nextLine();

                    cadastroDeClientes.alterarDados(clienteParaAlterar, novoNome, novoTelefone);
                    System.out.println("Dados do cliente Alterados com sucesso");
                } else {
                    System.out.println("Cliente não encontrado");
                }
                break;

            case 5:
                System.out.println("Quantidade total de pedidos: " + cadastroDePedidos.getNumeroDePedidos());
                System.out.println("Quantidade total de pedidos encerrados: " + cadastroDePedidos.getNumeroDePedidosEncerrados());
                System.out.println("Quantidade total de pedidos em Atendimento: " + cadastroDePedidos.getNumeroDePedidosEmAtendimento());

                System.out.println("\nPedidos em atendimento:");
                for(Pedido pedido:cadastroDePedidos.getPedidos()){
                    if (!pedido.isEncerrado()){
                        System.out.println(pedido);
                    }
                }

                for (Pedido pedido : cadastroDePedidos.getPedidos()) {
                    if (pedido.isEncerrado()) {
                        System.out.println(pedido);
                    }
                }
                break;

            case 6:
                System.out.print("Digite o nome do cliente para encerrar o pedido: ");
                String nomeEncerrarPedido = scanner.nextLine();
                Cliente clienteEncerrarPedido = cadastroDeClientes.buscarCliente(nomeEncerrarPedido);

                if (clienteEncerrarPedido != null) {
                    Pedido pedidoEncerrar = null;
                    for (Pedido pedido : cadastroDePedidos.getPedidos()) {
                        if (!pedido.isEncerrado() && pedido.getCliente().equals(clienteEncerrarPedido)) {
                            pedidoEncerrar = pedido;
                            break;
                        }
                    }

                    if (pedidoEncerrar != null) {
                        cadastroDePedidos.encerrarPedido(pedidoEncerrar);

                        System.out.println("Pedido encerrado e pronto para entrega:");
                        System.out.println(pedidoEncerrar);

                        String enderecoEntrega = pedidoEncerrar.getEnderecoEntrega();

                        if (enderecoEntrega != null) {
                            System.out.println("Endereço de entrega: " + enderecoEntrega);
                        } else {
                            System.out.println("Endereço de entrega não cadastrado para o cliente.");
                        }

                        // Geração do arquivo de entrega
                        if (enderecoEntrega != null) {
                            cadastroDePedidos.notaDeEntrega(pedidoEncerrar, enderecoEntrega);
                            System.out.println("Arquivo de entrega gerado com sucesso!");

                        } else {
                            System.out.println("Arquivo de entrega não gerado devido ao endereço de entrega não estar cadastrado.");
                        }
                    } else {
                        System.out.println("Pedido não encontrado para este cliente.");
                    }
                } else {
                    System.out.println("Cliente não encontrado.");
                }
                break;

            case 0:
                System.out.println("Encerrando Programa...");
                scanner.close();
                System.exit(0);
                break;

            default:
                System.out.println("Opção escolhida é invalida, tente novamente.!");
        }
    }

    }

}