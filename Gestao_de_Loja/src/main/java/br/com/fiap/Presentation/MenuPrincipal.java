package br.com.fiap.Presentation;

import java.util.Scanner;

public class MenuPrincipal {
    private Scanner scanner;

    private MenuCliente menuCliente;
    private MenuFornecedor menuFornecedor;
    private MenuFuncionario menuFuncionario;
    private MenuProduto menuProduto;
    private MenuPedido menuPedido;
    private MenuItemPedido menuItemPedido;
    private MenuPagamento menuPagamento;
    private MenuJson menuJson;

    public MenuPrincipal() {
        scanner = new Scanner(System.in);

        menuCliente = new MenuCliente(scanner);
        menuFornecedor = new MenuFornecedor(scanner);
        menuFuncionario = new MenuFuncionario(scanner);
        menuProduto = new MenuProduto(scanner);
        menuPedido = new MenuPedido(scanner);
        menuItemPedido = new MenuItemPedido(scanner);
        menuPagamento = new MenuPagamento(scanner);
        menuJson = new MenuJson(scanner);
    }

    public void Exibir() {
        int opcao;

        do {
            System.out.println("\n=================================");
            System.out.println("         MENU PRINCIPAL");
            System.out.println("=================================");
            System.out.println("1 - Clientes");
            System.out.println("2 - Fornecedores");
            System.out.println("3 - Funcionários");
            System.out.println("4 - Produtos");
            System.out.println("5 - Pedidos");
            System.out.println("6 - Itens de Pedido");
            System.out.println("7 - Pagamentos");
            System.out.println("8 - JSON");
            System.out.println("0 - Sair");
            System.out.println("=================================");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcao) {

                    case 1:
                        menuCliente.Exibir();

                        break;

                    case 2:
                        menuFornecedor.Exibir();

                        break;

                    case 3:
                        menuFuncionario.Exibir();

                        break;

                    case 4:
                        menuProduto.Exibir();

                        break;

                    case 5:
                        menuPedido.Exibir();

                        break;

                    case 6:
                        menuItemPedido.Exibir();

                        break;

                    case 7:
                        menuPagamento.Exibir();

                        break;

                    case 8:

                        menuJson.Exibir();

                        break;

                    case 0:
                        System.out.println("Programa encerrado!!!");

                        break;

                    default:
                        System.out.println("Opção inválida!!!");
                }

            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while (opcao != 0);
    }
}

