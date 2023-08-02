import java.util.List;
import java.util.Scanner;
import java.util.SortedMap;

public class Main {
    private static final CadastroDeClientes cadastroDeClientes = new CadastroDeClientes();
    private static final CadastroDePedidos cadastroDePedidos = new CadastroDePedidos();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CadastroDeClientes cadastroDeClientes = new CadastroDeClientes();
        CadastroDePedidos cadastroDePedidos = new CadastroDePedidos();
        Scanner scanner = new Scanner(System.in);

        while (true) {
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
                    cadastrarCliente();
                    break;
                case 2:
                    realizarPedido();
                    break;

                case 3:
                    buscarCliente();
                    break;

                case 4:
                    alterarDados();
                    break;

                case 5:
                    acompanharPedidos();
                    break;

                case 6:
                    encerrarPedidos();
                    break;

                case 0:
                    encerrarPrograma();
                    break;

                default:
                    System.out.println("Opção escolhida é invalida, tente novamente.!");
            }
        }

    }

    private static void cadastrarCliente() {
        System.out.print("Digite o nome do cliente: ");
        String nomeCliente = scanner.nextLine();

        System.out.print("Digite o telefone do cliente: ");
        String telefoneCliente = scanner.nextLine();

        Cliente cliente = new Cliente(nomeCliente, telefoneCliente);

        boolean adicionarEndereco = true;
        while (adicionarEndereco) {
            System.out.print("Digite o nome da rua: ");
            String nomeRua = scanner.nextLine();

            System.out.print("Digite o número da casa: ");
            String numeroCasa = scanner.nextLine();

            Endereco endereco = new Endereco(nomeRua, numeroCasa);
            cliente.addEndereco(endereco);

            System.out.print("Deseja adicionar mais um endereço para o cliente (S/N)? ");
            String opcaoAdicionarEndereco = scanner.nextLine();
            adicionarEndereco = opcaoAdicionarEndereco.equalsIgnoreCase("S");
        }

        cadastroDeClientes.cadastrarCliente(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
        return;
    }

    private static void realizarPedido() {
        System.out.print("Digite o nome do cliente que deseja realizar o pedido: ");
        String nomeClientePedido = scanner.nextLine();
        Cliente clientePedido = cadastroDeClientes.buscarCliente(nomeClientePedido);

        if (clientePedido != null) {
            System.out.println("Endereços cadastrados para o cliente:");
            List<Endereco> enderecosCliente = clientePedido.getEnderecos();
            for (int i = 0; i < enderecosCliente.size(); i++) {
                Endereco endereco = enderecosCliente.get(i);
                System.out.println((i + 1) + " - Rua: " + endereco.getRua() + ", Número: " + endereco.getNumeroCasa());
            }

            System.out.print("Selecione o número do endereço de entrega: ");
            int opcaoEnderecoEntrega = scanner.nextInt();
            scanner.nextLine();

            if (opcaoEnderecoEntrega > 0 && opcaoEnderecoEntrega <= enderecosCliente.size()) {
                Endereco enderecoEntrega = enderecosCliente.get(opcaoEnderecoEntrega - 1);

                Pedido pedido = new Pedido(clientePedido, enderecoEntrega);
                pedido.setEnderecoEntrega(enderecoEntrega);
                boolean encerrarPedido = false;

                while (!encerrarPedido) {
                    System.out.println("\nEscolha o tamanho da pizza:");
                    System.out.println("1 - Grande");
                    System.out.println("2 - Média");
                    System.out.println("3 - Pequena");
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
            }
            }else {
            System.out.println("Cliente não encontrado");
        }
    }

    private static void buscarCliente() {
        System.out.print("Digite o cliente que deseja Buscar: ");
        String nomeBuscado = scanner.nextLine();
        Cliente clienteEncontrado = cadastroDeClientes.buscarCliente(nomeBuscado);

        if (clienteEncontrado != null) {
            System.out.println("Informações do cliente: " + clienteEncontrado.getNome());
            System.out.println("Telefone: " + clienteEncontrado.getTelefone());
            System.out.println("Endereços:");

            for (Endereco enderecoCliente : clienteEncontrado.getEnderecos()) {
                System.out.println("Rua: " + enderecoCliente.getRua() + ", Número: " + enderecoCliente.getNumeroCasa());
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void alterarDados() {
        System.out.print("Digite o cliente que deseja alterar os dados: ");
        String nomeAlterar = scanner.nextLine();
        Cliente clienteParaAlterar = cadastroDeClientes.buscarCliente(nomeAlterar);

                        if(clienteParaAlterar !=null)

        {
            System.out.println("Cliente encontrado");
            System.out.println(clienteParaAlterar);

            System.out.print("Digite o novo nome do cliente: ");
            String novoNome = scanner.nextLine();
            System.out.print("Digite o novo telefone do cliente: ");
            String novoTelefone = scanner.nextLine();

            cadastroDeClientes.alterarDados(clienteParaAlterar, novoNome, novoTelefone);
            System.out.println("Dados do cliente Alterados com sucesso");
        } else

        {
            System.out.println("Cliente não encontrado");
        }
    }
    private static void acompanharPedidos(){
        System.out.println("Quantidade total de pedidos: " + cadastroDePedidos.getNumeroDePedidos());
        System.out.println("Quantidade total de pedidos encerrados: " + cadastroDePedidos.getNumeroDePedidosEncerrados());
        System.out.println("Quantidade total de pedidos em Atendimento: " + cadastroDePedidos.getNumeroDePedidosEmAtendimento());

        System.out.println("\nPedidos em atendimento:");
        for (Pedido pedido : cadastroDePedidos.getPedidos()) {
            if (!pedido.isEncerrado()) {
                System.out.println(pedido);
            }
        }

        for (Pedido pedido : cadastroDePedidos.getPedidos()) {
            if (pedido.isEncerrado()) {
                System.out.println(pedido);
            }
        }
    }
    private static void encerrarPedidos(){
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

                Endereco enderecoEntrega = pedidoEncerrar.getEnderecoEntrega();

                if (enderecoEntrega != null) {
                    System.out.println("Endereço de entrega: " + enderecoEntrega.getRua() + ", Número: " + enderecoEntrega.getNumeroCasa());
                } else {
                    System.out.println("Endereço de entrega não cadastrado para o cliente.");
                }

                if (enderecoEntrega != null) {
                    cadastroDePedidos.notaDeEntrega(pedidoEncerrar, enderecoEntrega.getRua() + ", Número: " + enderecoEntrega.getNumeroCasa());
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
    }
    private static void encerrarPrograma(){
        System.out.println("Encerrando Programa...");
        scanner.close();
        System.exit(0);
    }

}
