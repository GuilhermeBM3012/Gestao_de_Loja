package br.com.fiap.Presentation;

import br.com.fiap.Domain.Pagamento;
import br.com.fiap.Domain.Pedido;
import br.com.fiap.Domain.StatusPagamento;
import br.com.fiap.Domain.TipoPagamento;
import br.com.fiap.Service.PagamentoService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuPagamento {

    private Scanner scanner;
    private PagamentoService pagamentoService;

    public MenuPagamento(Scanner scanner) {
        this.scanner = scanner;
        pagamentoService = new PagamentoService();
    }

    public void Exibir() {
        int opcao;

        do {
            System.out.println("\n=================================");
            System.out.println("         MENU PAGAMENTO");
            System.out.println("=================================");
            System.out.println("1 - Cadastrar pagamento");
            System.out.println("2 - Buscar pagamento por ID");
            System.out.println("3 - Listar pagamentos");
            System.out.println("4 - Atualizar pagamento");
            System.out.println("5 - Remover pagamento");
            System.out.println("0 - Voltar");
            System.out.println("=================================");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (opcao) {
                    case 1:
                        InserirPagamento();

                        break;

                    case 2:
                        BuscarPagamento();

                        break;

                    case 3:
                        ListarPagamentos();

                        break;

                    case 4:
                        AtualizarPagamento();

                        break;

                    case 5:
                        RemoverPagamento();

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

    private void InserirPagamento() {
        Pagamento pagamento = new Pagamento();

        System.out.println("\n--- Cadastro de Pagamento ---");

        System.out.println("\nTipos de pagamento:");

        TipoPagamento[] tipos = TipoPagamento.values();

        for (int i = 0; i < tipos.length; i++) {
            System.out.println((i + 1) + " - " + tipos[i]);
        }

        System.out.print("Escolha o tipo: ");
        int opcaoTipo = scanner.nextInt();
        scanner.nextLine();

        pagamento.setTipoPagamento(tipos[opcaoTipo - 1]);

        System.out.print("Valor: ");
        pagamento.setValor(scanner.nextDouble());
        scanner.nextLine();

        System.out.print("Data do pagamento (AAAA-MM-DD): ");
        pagamento.setDataPagamento(LocalDate.parse(scanner.nextLine()));

        System.out.println("\nStatus do pagamento:");

        StatusPagamento[] status = StatusPagamento.values();

        for (int i = 0; i < status.length; i++) {
            System.out.println((i + 1) + " - " + status[i]);
        }

        System.out.print("Escolha o status: ");
        int opcaoStatus = scanner.nextInt();

        System.out.print("ID do pedido: ");
        int idPedido = scanner.nextInt();
        scanner.nextLine();

        pagamento.setStatus(status[opcaoStatus - 1]);

        Pedido pedido = new Pedido();
        pedido.setIdPedido(idPedido);

        pagamento.setPedido(pedido);

        pagamentoService.InserirPagamento(pagamento);

        System.out.println("Pagamento cadastrado com sucesso!!!");
    }

    private void BuscarPagamento() {
        System.out.print("Digite o ID do pagamento: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Pagamento pagamento = pagamentoService.BuscarPagamentoPorID(id);

        ExibirPagamento(pagamento);
    }

    private void ListarPagamentos() {
        List<Pagamento> pagamentos = pagamentoService.ListarTodosPagamentos();

        if (pagamentos.isEmpty()) {
            System.out.println("Nenhum pagamento cadastrado!!!");

            return;
        }

        for (Pagamento pagamento : pagamentos) {
            ExibirPagamento(pagamento);
        }
    }

    private void AtualizarPagamento() {
        System.out.print("Digite o ID do pagamento: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\n1 - Tipo de pagamento");
        System.out.println("2 - Valor");
        System.out.println("3 - Data do pagamento");
        System.out.println("4 - Status");
        System.out.print("O que deseja atualizar? ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        Pagamento pagamento = new Pagamento();
        pagamento.setIdPagamento(id);

        switch (opcao) {
            case 1:
                TipoPagamento[] tipos = TipoPagamento.values();

                for (int i = 0; i < tipos.length; i++) {
                    System.out.println((i + 1) + " - " + tipos[i]);
                }

                System.out.print("Escolha o novo tipo: ");
                int opcaoTipo = scanner.nextInt();
                scanner.nextLine();

                pagamento.setTipoPagamento(tipos[opcaoTipo - 1]);

                break;

            case 2:
                System.out.print("Novo valor: ");
                pagamento.setValor(scanner.nextDouble());
                scanner.nextLine();

                break;

            case 3:
                System.out.print("Nova data (AAAA-MM-DD): ");

                pagamento.setDataPagamento(LocalDate.parse(scanner.nextLine()));

                break;

            case 4:
                StatusPagamento[] status = StatusPagamento.values();

                for (int i = 0; i < status.length; i++) {
                    System.out.println((i + 1) + " - " + status[i]
                    );
                }

                System.out.print("Escolha o novo status: ");
                int opcaoStatus = scanner.nextInt();
                scanner.nextLine();

                pagamento.setStatus(status[opcaoStatus - 1]);

                break;

            default:
                System.out.println("Opção inválida!!!");

                return;
        }

        pagamentoService.AtualizarPagamento(pagamento, opcao);

        System.out.println("Pagamento atualizado com sucesso!!!");
    }

    private void RemoverPagamento() {
        System.out.print("Digite o ID do pagamento: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        pagamentoService.RemoverPagamento(id);

        System.out.println("Pagamento removido com sucesso!!!");
    }

    private void ExibirPagamento(Pagamento pagamento) {
        System.out.println("-----------------------------");
        System.out.println("ID: " + pagamento.getIdPagamento());
        System.out.println("Tipo: " + pagamento.getTipoPagamento());
        System.out.println("Valor: " + pagamento.getValor());
        System.out.println("Data: " + pagamento.getDataPagamento());
        System.out.println("Status: " + pagamento.getStatus());

        if (pagamento.getPedido() != null) {
            System.out.println("Pedido: " + pagamento.getPedido().getIdPedido());
        }
        System.out.println("-----------------------------");
    }
}

