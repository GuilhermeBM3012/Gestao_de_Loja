package br.com.fiap.Presentation;

import br.com.fiap.Domain.Fornecedor;
import br.com.fiap.Domain.Produto;
import br.com.fiap.Service.ProdutoService;

import java.util.List;
import java.util.Scanner;

public class MenuProduto {

    private Scanner scanner;
    private ProdutoService produtoService;

    public MenuProduto(Scanner scanner) {
        this.scanner = scanner;
        produtoService = new ProdutoService();
    }

    public void Exibir() {
        int opcao;

        do {
            System.out.println("\n=================================");
            System.out.println("          MENU PRODUTO");
            System.out.println("=================================");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Buscar produto por ID");
            System.out.println("3 - Listar produtos");
            System.out.println("4 - Atualizar produto");
            System.out.println("5 - Remover produto");
            System.out.println("0 - Voltar");
            System.out.println("=================================");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (opcao) {
                    case 1:
                        InserirProduto();

                        break;

                    case 2:
                        BuscarProduto();

                        break;

                    case 3:
                        ListarProdutos();

                        break;

                    case 4:
                        AtualizarProduto();

                        break;

                    case 5:
                        RemoverProduto();

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

    private void InserirProduto() {
        Produto produto = new Produto();

        System.out.println("\n--- Cadastro de Produto ---");

        System.out.print("Nome: ");
        produto.setNome(scanner.nextLine());

        System.out.print("Descrição: ");
        produto.setDescricao(scanner.nextLine());

        System.out.print("Preço: ");
        produto.setPreco(scanner.nextDouble());

        System.out.print("Quantidade em estoque: ");
        produto.setQuantidadeEstoque(scanner.nextInt());

        System.out.print("ID do fornecedor: ");
        int idFornecedor = scanner.nextInt();
        scanner.nextLine();

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setIdFornecedor(idFornecedor);

        produto.setFornecedor(fornecedor);

        produtoService.InserirProduto(produto);

        System.out.println("Produto cadastrado com sucesso!!!");
    }

    private void BuscarProduto() {
        System.out.print("Digite o ID do produto: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Produto produto = produtoService.BuscarProdutoPorID(id);

        ExibirProduto(produto);
    }

    private void ListarProdutos() {
        List<Produto> produtos = produtoService.ListarTodosProdutos();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado!!!");

            return;
        }

        for (Produto produto : produtos) {
            ExibirProduto(produto);
        }
    }

    private void AtualizarProduto() {
        System.out.print("Digite o ID do produto: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\n1 - Nome");
        System.out.println("2 - Descrição");
        System.out.println("3 - Preço");
        System.out.println("4 - Quantidade em estoque");
        System.out.print("O que deseja atualizar? ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        Produto produto = new Produto();
        produto.setIdProduto(id);

        switch (opcao) {

            case 1:
                System.out.print("Novo nome: ");
                produto.setNome(scanner.nextLine());

                break;

            case 2:
                System.out.print("Nova descrição: ");
                produto.setDescricao(scanner.nextLine());

                break;

            case 3:
                System.out.print("Novo preço: ");
                produto.setPreco(scanner.nextDouble());
                scanner.nextLine();

                break;

            case 4:
                System.out.print("Nova quantidade em estoque: ");
                produto.setQuantidadeEstoque(scanner.nextInt());
                scanner.nextLine();

                break;

            default:
                System.out.println("Opção inválida!!!");

                return;
        }

        produtoService.AtualizarProduto(produto, opcao);

        System.out.println("Produto atualizado com sucesso!!!");
    }

    private void RemoverProduto() {
        System.out.print("Digite o ID do produto: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        produtoService.RemoverProduto(id);

        System.out.println("Produto removido com sucesso!!!");
    }

    private void ExibirProduto(Produto produto) {
        System.out.println("-----------------------------");
        System.out.println("ID: " + produto.getIdProduto());
        System.out.println("Nome: " + produto.getNome());
        System.out.println("Descrição: " + produto.getDescricao());
        System.out.println("Preço: " + produto.getPreco());
        System.out.println("Estoque: " + produto.getQuantidadeEstoque());

        if (produto.getFornecedor() != null) {
            System.out.println("Fornecedor: " + produto.getFornecedor().getIdFornecedor());
        }
        System.out.println("-----------------------------");
    }
}