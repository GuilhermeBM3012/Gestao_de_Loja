package br.com.fiap.Presentation;

import br.com.fiap.Domain.Cliente;
import br.com.fiap.Service.ClienteService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuCliente {

    private Scanner scanner;
    private ClienteService clienteService;

    public MenuCliente(Scanner scanner) {
        this.scanner = scanner;

        clienteService = new ClienteService();
    }

    public void Exibir() {
        int opcao;

        do {
            System.out.println("\n=================================");
            System.out.println("          MENU CLIENTE");
            System.out.println("=================================");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Buscar cliente por ID");
            System.out.println("3 - Listar clientes");
            System.out.println("4 - Atualizar cliente");
            System.out.println("5 - Remover cliente");
            System.out.println("0 - Voltar");
            System.out.println("=================================");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (opcao) {
                    case 1:
                        InserirCliente();

                        break;

                    case 2:
                        BuscarCliente();

                        break;

                    case 3:
                        ListarClientes();

                        break;

                    case 4:
                        AtualizarCliente();

                        break;

                    case 5:
                        RemoverCliente();

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

    private void InserirCliente() {
        Cliente cliente = new Cliente();

        System.out.println("\n--- Cadastro de Cliente ---");

        System.out.print("Nome: ");
        cliente.setNome(scanner.nextLine());

        System.out.print("CPF: ");
        cliente.setCpf(scanner.nextLine());

        System.out.print("E-mail: ");
        cliente.setEmail(scanner.nextLine());

        System.out.print("Telefone: ");
        cliente.setTelefone(scanner.nextLine());

        System.out.print("Data de cadastro (AAAA-MM-DD): ");
        String data = scanner.nextLine();

        if (!data.isEmpty())
            cliente.setDataCadastro(LocalDate.parse(data));

        clienteService.InserirCliente(cliente);

        System.out.println("Cliente cadastrado com sucesso!!!");
    }

    private void BuscarCliente() {
        System.out.print("Digite o ID do cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente = clienteService.BuscarClientePorID(id);

        ExibirCliente(cliente);
    }

    private void ListarClientes() {
        List<Cliente> clientes = clienteService.ListarTodosClientes();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado!!!");
            return;
        }

        System.out.println("\n--- Clientes cadastrados ---");

        for (Cliente cliente : clientes) {
            ExibirCliente(cliente);
        }
    }

    private void AtualizarCliente() {
        System.out.print("Digite o ID do cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\n1 - Nome");
        System.out.println("2 - CPF");
        System.out.println("3 - E-mail");
        System.out.println("4 - Telefone");
        System.out.println("5 - Data de cadastro");
        System.out.print("O que deseja atualizar? ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente = new Cliente();
        cliente.setIdCliente(id);

        switch (opcao) {
            case 1:
                System.out.print("Novo nome: ");
                cliente.setNome(scanner.nextLine());

                break;

            case 2:
                System.out.print("Novo CPF: ");
                cliente.setCpf(scanner.nextLine());

                break;

            case 3:
                System.out.print("Novo e-mail: ");
                cliente.setEmail(scanner.nextLine());

                break;

            case 4:
                System.out.print("Novo telefone: ");
                cliente.setTelefone(scanner.nextLine());

                break;

            case 5:
                System.out.print("Nova data (AAAA-MM-DD): ");
                cliente.setDataCadastro(LocalDate.parse(scanner.nextLine()));

                break;

            default:
                System.out.println("Opção inválida!!!");

                return;
        }

        clienteService.AtualizarCliente(cliente, opcao);

        System.out.println("Cliente atualizado com sucesso!!!");
    }

    private void RemoverCliente() {
        System.out.print("Digite o ID do cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        clienteService.RemoverCliente(id);

        System.out.println("Cliente removido com sucesso!!!");
    }

    private void ExibirCliente(Cliente cliente) {
        System.out.println("-----------------------------");
        System.out.println("ID: " + cliente.getIdCliente());
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("E-mail: " + cliente.getEmail());
        System.out.println("Telefone: " + cliente.getTelefone());
        System.out.println("Data de cadastro: " +
                cliente.getDataCadastro());
        System.out.println("-----------------------------");
    }
}
