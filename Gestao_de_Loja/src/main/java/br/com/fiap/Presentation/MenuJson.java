package br.com.fiap.Presentation;

import br.com.fiap.Util.JsonService;

import java.util.Scanner;

public class MenuJson {
    private Scanner scanner;
    private JsonService jsonService;

    public MenuJson(Scanner scanner) {

        this.scanner = scanner;
        this.jsonService = new JsonService();
    }

    public void Exibir() {

        int opcao;

        do {

            System.out.println();
            System.out.println("=================================");
            System.out.println("          MENU JSON");
            System.out.println("=================================");
            System.out.println("1 - Exportar todos os dados");
            System.out.println("2 - Importar clientes");
            System.out.println("3 - Importar fornecedores");
            System.out.println("4 - Importar funcionários");
            System.out.println("5 - Importar produtos");
            System.out.println("6 - Importar pedidos");
            System.out.println("7 - Importar itens de pedido");
            System.out.println("8 - Importar pagamentos");
            System.out.println("0 - Voltar");
            System.out.println("=================================");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (opcao) {
                    case 1:
                        jsonService.ExportarTodos();

                        break;

                    case 2:

                        System.out.println("Clientes importados: " + jsonService.ImportarClientes().size());

                        break;

                    case 3:
                        System.out.println("Fornecedores importados: " + jsonService.ImportarFornecedores().size());

                        break;

                    case 4:
                        System.out.println("Funcionários importados: " + jsonService.ImportarFuncionarios().size());

                        break;

                    case 5:
                        System.out.println("Produtos importados: " + jsonService.ImportarProdutos().size());

                        break;

                    case 6:
                        System.out.println("Pedidos importados: " + jsonService.ImportarPedidos().size());

                        break;

                    case 7:
                        System.out.println("Itens de pedido importados: " + jsonService.ImportarItensPedido().size());

                        break;

                    case 8:
                        System.out.println("Pagamentos importados: " + jsonService.ImportarPagamentos().size());

                        break;

                    case 0:
                        System.out.println("Voltando ao menu principal...");

                        break;

                    default:
                        System.out.println("Opção inválida!");
                }

            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while (opcao != 0);
    }
}
