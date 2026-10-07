package br.com.fiap.Presentation;

import br.com.fiap.Domain.Fornecedor;
import br.com.fiap.Service.FornecedorService;

import java.util.List;
import java.util.Scanner;

public class MenuFornecedor {

    private Scanner scanner;
    private FornecedorService fornecedorService;

    public MenuFornecedor(Scanner scanner) {
        this.scanner = scanner;
        fornecedorService = new FornecedorService();
    }

    public void Exibir() {
        int opcao;

        do {
            System.out.println("\n=================================");
            System.out.println("        MENU FORNECEDOR");
            System.out.println("=================================");
            System.out.println("1 - Cadastrar fornecedor");
            System.out.println("2 - Buscar fornecedor por ID");
            System.out.println("3 - Listar fornecedores");
            System.out.println("4 - Atualizar fornecedor");
            System.out.println("5 - Remover fornecedor");
            System.out.println("0 - Voltar");
            System.out.println("=================================");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (opcao) {
                    case 1:
                        InserirFornecedor();

                        break;

                    case 2:
                        BuscarFornecedor();

                        break;

                    case 3:
                        ListarFornecedores();

                        break;

                    case 4:
                        AtualizarFornecedor();

                        break;

                    case 5:
                        RemoverFornecedor();

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

    private void InserirFornecedor() {
        Fornecedor fornecedor = new Fornecedor();

        System.out.println("\n--- Cadastro de Fornecedor ---");

        System.out.print("Nome: ");
        fornecedor.setNome(scanner.nextLine());

        System.out.print("CNPJ: ");
        fornecedor.setCnpj(scanner.nextLine());

        System.out.print("E-mail: ");
        fornecedor.setEmail(scanner.nextLine());

        System.out.print("Telefone: ");
        fornecedor.setTelefone(scanner.nextLine());

        fornecedorService.InserirFornecedor(fornecedor);

        System.out.println("Fornecedor cadastrado com sucesso!!!");
    }

    private void BuscarFornecedor() {
        System.out.print("Digite o ID do fornecedor: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Fornecedor fornecedor = fornecedorService.BuscarFornecedorPorID(id);

        ExibirFornecedor(fornecedor);
    }

    private void ListarFornecedores() {
        List<Fornecedor> fornecedores = fornecedorService.ListarTodosFornecedores();

        if (fornecedores.isEmpty()) {
            System.out.println("Nenhum fornecedor cadastrado!!!");

            return;
        }

        for (Fornecedor fornecedor : fornecedores) {
            ExibirFornecedor(fornecedor);
        }
    }

    private void AtualizarFornecedor() {
        System.out.print("Digite o ID do fornecedor: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\n1 - Nome");
        System.out.println("2 - CNPJ");
        System.out.println("3 - E-mail");
        System.out.println("4 - Telefone");
        System.out.print("O que deseja atualizar? ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setIdFornecedor(id);

        switch (opcao) {
            case 1:
                System.out.print("Novo nome: ");
                fornecedor.setNome(scanner.nextLine());

                break;

            case 2:
                System.out.print("Novo CNPJ: ");
                fornecedor.setCnpj(scanner.nextLine());

                break;

            case 3:
                System.out.print("Novo e-mail: ");
                fornecedor.setEmail(scanner.nextLine());

                break;

            case 4:
                System.out.print("Novo telefone: ");
                fornecedor.setTelefone(scanner.nextLine());

                break;

            default:
                System.out.println("Opção inválida!!!");

                return;
        }

        fornecedorService.AtualizarFornecedor(fornecedor, opcao);

        System.out.println("Fornecedor atualizado com sucesso!!!");
    }

    private void RemoverFornecedor() {
        System.out.print("Digite o ID do fornecedor: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        fornecedorService.RemoverFornecedor(id);

        System.out.println("Fornecedor removido com sucesso!!!");
    }

    private void ExibirFornecedor(Fornecedor fornecedor) {
        System.out.println("-----------------------------");
        System.out.println("ID: " + fornecedor.getIdFornecedor());
        System.out.println("Nome: " + fornecedor.getNome());
        System.out.println("CNPJ: " + fornecedor.getCnpj());
        System.out.println("E-mail: " + fornecedor.getEmail());
        System.out.println("Telefone: " + fornecedor.getTelefone());
        System.out.println("-----------------------------");
    }
}
