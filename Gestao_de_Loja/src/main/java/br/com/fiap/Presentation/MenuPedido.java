package br.com.fiap.Presentation;

import br.com.fiap.Domain.Cliente;
import br.com.fiap.Domain.Funcionario;
import br.com.fiap.Domain.Pedido;
import br.com.fiap.Domain.StatusPedido;
import br.com.fiap.Service.PedidoService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuPedido {

    private Scanner scanner;
    private PedidoService pedidoService;

    public MenuPedido(Scanner scanner) {
        this.scanner = scanner;
        pedidoService = new PedidoService();
    }

    public void Exibir() {
        int opcao;

        do {
            System.out.println("\n=================================");
            System.out.println("           MENU PEDIDO");
            System.out.println("=================================");
            System.out.println("1 - Cadastrar pedido");
            System.out.println("2 - Buscar pedido por ID");
            System.out.println("3 - Listar pedidos");
            System.out.println("4 - Atualizar pedido");
            System.out.println("5 - Remover pedido");
            System.out.println("0 - Voltar");
            System.out.println("=================================");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (opcao) {
                    case 1:
                        InserirPedido();

                        break;

                    case 2:
                        BuscarPedido();

                        break;

                    case 3:
                        ListarPedidos();

                        break;

                    case 4:
                        AtualizarPedido();

                        break;

                    case 5:
                        RemoverPedido();

                        break;

                    case 0:
                        break;

                    default:
                        System.out.println("Opção inválida!!!");
                }

            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while (opcao != 0);
    }

    private void InserirPedido() {
        Pedido pedido = new Pedido();

        System.out.println("\n--- Cadastro de Pedido ---");

        System.out.print("Data do pedido (AAAA-MM-DD): ");
        pedido.setDataPedido(LocalDate.parse(scanner.nextLine()));

        System.out.println("\nStatus do pedido:");

        StatusPedido[] status = StatusPedido.values();

        for (int i = 0; i < status.length; i++) {
            System.out.println((i + 1) + " - " + status[i]);
        }

        System.out.print("Escolha o status: ");
        int opcaoStatus = scanner.nextInt();
        scanner.nextLine();

        pedido.setStatus(status[opcaoStatus - 1]);

        System.out.print("Valor total: ");
        pedido.setValorTotal(scanner.nextDouble());

        System.out.print("ID do cliente: ");
        int idCliente = scanner.nextInt();

        System.out.print("ID do funcionário: ");
        int idFuncionario = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente = new Cliente();
        cliente.setIdCliente(idCliente);

        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(idFuncionario);

        pedido.setCliente(cliente);
        pedido.setFuncionario(funcionario);

        pedidoService.InserirPedido(pedido);

        System.out.println("Pedido cadastrado com sucesso!!!");
    }

    private void BuscarPedido() {
        System.out.print("Digite o ID do pedido: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Pedido pedido = pedidoService.BuscarPedidoPorID(id);

        ExibirPedido(pedido);
    }

    private void ListarPedidos() {
        List<Pedido> pedidos = pedidoService.ListarTodosPedidos();

        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado!!!");

            return;
        }

        for (Pedido pedido : pedidos) {
            ExibirPedido(pedido);
        }
    }

    private void AtualizarPedido() {
        System.out.print("Digite o ID do pedido: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\n1 - Data do pedido");
        System.out.println("2 - Status");
        System.out.println("3 - Valor total");
        System.out.print("O que deseja atualizar? ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        Pedido pedido = new Pedido();
        pedido.setIdPedido(id);

        switch (opcao) {
            case 1:
                System.out.print("Nova data (AAAA-MM-DD): ");
                pedido.setDataPedido(LocalDate.parse(scanner.nextLine()));

                break;

            case 2:
                System.out.println("\nStatus do pedido:");

                StatusPedido[] status = StatusPedido.values();

                for (int i = 0; i < status.length; i++) {
                    System.out.println((i + 1) + " - " + status[i]);
                }

                System.out.print("Escolha o novo status: ");
                int opcaoStatus = scanner.nextInt();
                scanner.nextLine();

                pedido.setStatus(status[opcaoStatus - 1]);

                break;

            case 3:
                System.out.print("Novo valor total: ");
                pedido.setValorTotal(scanner.nextDouble());
                scanner.nextLine();

                break;

            default:
                System.out.println("Opção inválida!!!");

                return;
        }

        pedidoService.AtualizarPedido(pedido, opcao);

        System.out.println("Pedido atualizado com sucesso!!!");
    }

    private void RemoverPedido() {
        System.out.print("Digite o ID do pedido: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        pedidoService.RemoverPedido(id);

        System.out.println("Pedido removido com sucesso!!!");
    }

    private void ExibirPedido(Pedido pedido) {
        System.out.println("-----------------------------");
        System.out.println("ID: " + pedido.getIdPedido());
        System.out.println("Data: " + pedido.getDataPedido());
        System.out.println("Status: " + pedido.getStatus());
        System.out.println("Valor total: " + pedido.getValorTotal());

        if (pedido.getCliente() != null) {
            System.out.println("Cliente: " + pedido.getCliente().getIdCliente());
        }

        if (pedido.getFuncionario() != null) {
            System.out.println("Funcionário: " + pedido.getFuncionario().getIdFuncionario());
        }
        System.out.println("-----------------------------");
    }
}

