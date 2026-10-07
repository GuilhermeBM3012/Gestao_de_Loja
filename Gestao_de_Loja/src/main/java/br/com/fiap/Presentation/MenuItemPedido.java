package br.com.fiap.Presentation;

import br.com.fiap.Domain.ItemPedido;
import br.com.fiap.Domain.Pedido;
import br.com.fiap.Domain.Produto;
import br.com.fiap.Service.ItemPedidoService;

import java.util.List;
import java.util.Scanner;

public class MenuItemPedido {

    private Scanner scanner;
    private ItemPedidoService itemPedidoService;

    public MenuItemPedido(Scanner scanner) {
        this.scanner = scanner;
        itemPedidoService = new ItemPedidoService();
    }

    public void Exibir() {
        int opcao;

        do {
            System.out.println("\n=================================");
            System.out.println("       MENU ITEM DO PEDIDO");
            System.out.println("=================================");
            System.out.println("1 - Cadastrar item");
            System.out.println("2 - Buscar item por ID");
            System.out.println("3 - Listar itens");
            System.out.println("4 - Atualizar item");
            System.out.println("5 - Remover item");
            System.out.println("0 - Voltar");
            System.out.println("=================================");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (opcao) {
                    case 1:
                        InserirItem();

                        break;

                    case 2:
                        BuscarItem();

                        break;

                    case 3:
                        ListarItens();

                        break;

                    case 4:
                        AtualizarItem();

                        break;

                    case 5:
                        RemoverItem();

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

    private void InserirItem() {
        ItemPedido item = new ItemPedido();

        System.out.println("\n--- Cadastro de Item ---");

        System.out.print("Quantidade: ");
        item.setQuantidade(scanner.nextInt());

        System.out.print("Preço unitário: ");
        item.setPrecoUnitario(scanner.nextDouble());

        System.out.print("ID do pedido: ");
        int idPedido = scanner.nextInt();

        System.out.print("ID do produto: ");
        int idProduto = scanner.nextInt();
        scanner.nextLine();

        Pedido pedido = new Pedido();
        pedido.setIdPedido(idPedido);

        Produto produto = new Produto();
        produto.setIdProduto(idProduto);

        item.setPedido(pedido);
        item.setProduto(produto);

        itemPedidoService.InserirItemPedido(item);

        System.out.println("Item cadastrado com sucesso!!!");
    }

    private void BuscarItem() {
        System.out.print("Digite o ID do item: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        ItemPedido item = itemPedidoService.BuscarItemPedidoPorID(id);

        ExibirItem(item);
    }

    private void ListarItens() {
        List<ItemPedido> itens = itemPedidoService.ListarTodosItensPedido();

        if (itens.isEmpty()) {
            System.out.println("Nenhum item cadastrado!!!");

            return;
        }

        for (ItemPedido item : itens) {
            ExibirItem(item);
        }
    }

    private void AtualizarItem() {
        System.out.print("Digite o ID do item: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\n1 - Quantidade");
        System.out.println("2 - Preço unitário");
        System.out.print("O que deseja atualizar? ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        ItemPedido item = new ItemPedido();
        item.setIdItem(id);

        switch (opcao) {
            case 1:
                System.out.print("Nova quantidade: ");
                item.setQuantidade(scanner.nextInt());
                scanner.nextLine();

                break;

            case 2:
                System.out.print("Novo preço unitário: ");
                item.setPrecoUnitario(scanner.nextDouble());
                scanner.nextLine();

                break;

            default:
                System.out.println("Opção inválida!!!");

                return;
        }

        itemPedidoService.AtualizarItemPedido(item, opcao);

        System.out.println("Item atualizado com sucesso!!!");
    }

    private void RemoverItem() {
        System.out.print("Digite o ID do item: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        itemPedidoService.RemoverItemPedido(id);

        System.out.println("Item removido com sucesso!!!");
    }

    private void ExibirItem(ItemPedido item) {
        System.out.println("-----------------------------");
        System.out.println("ID: " + item.getIdItem());
        System.out.println("Quantidade: " + item.getQuantidade());
        System.out.println("Preço unitário: " + item.getPrecoUnitario());

        if (item.getPedido() != null) {
            System.out.println("Pedido: " + item.getPedido().getIdPedido());
        }

        if (item.getProduto() != null) {
            System.out.println("Produto: " + item.getProduto().getIdProduto());
        }
        System.out.println("-----------------------------");
    }
}

