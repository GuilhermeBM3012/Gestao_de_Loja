package br.com.fiap.Presentation;

import br.com.fiap.Domain.Cargo;
import br.com.fiap.Domain.Funcionario;
import br.com.fiap.Service.FuncionarioService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuFuncionario {

    private Scanner scanner;
    private FuncionarioService funcionarioService;

    public MenuFuncionario(Scanner scanner) {
        this.scanner = scanner;
        funcionarioService = new FuncionarioService();
    }

    public void Exibir() {
        int opcao;

        do {
            System.out.println("\n=================================");
            System.out.println("        MENU FUNCIONÁRIO");
            System.out.println("=================================");
            System.out.println("1 - Cadastrar funcionário");
            System.out.println("2 - Buscar funcionário por ID");
            System.out.println("3 - Listar funcionários");
            System.out.println("4 - Atualizar funcionário");
            System.out.println("5 - Remover funcionário");
            System.out.println("0 - Voltar");
            System.out.println("=================================");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (opcao) {
                    case 1:
                        InserirFuncionario();

                        break;

                    case 2:
                        BuscarFuncionario();

                        break;

                    case 3:
                        ListarFuncionarios();

                        break;

                    case 4:
                        AtualizarFuncionario();

                        break;

                    case 5:
                        RemoverFuncionario();

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

    private void InserirFuncionario() {
        Funcionario funcionario = new Funcionario();

        System.out.println("\n--- Cadastro de Funcionário ---");

        System.out.print("Nome: ");
        funcionario.setNome(scanner.nextLine());

        System.out.print("CPF: ");
        funcionario.setCpf(scanner.nextLine());

        System.out.println("\nCargos disponíveis:");

        Cargo[] cargos = Cargo.values();

        for (int i = 0; i < cargos.length; i++) {
            System.out.println((i + 1) + " - " + cargos[i]);
        }

        System.out.print("Escolha o cargo: ");
        int opcaoCargo = scanner.nextInt();
        scanner.nextLine();

        funcionario.setCargo(cargos[opcaoCargo - 1]);

        System.out.print("Salário: ");
        funcionario.setSalario(scanner.nextDouble());
        scanner.nextLine();

        System.out.print("Data de contratação (AAAA-MM-DD): ");
        funcionario.setDataContratacao(LocalDate.parse(scanner.nextLine()));

        funcionarioService.InserirFuncionario(funcionario);

        System.out.println("Funcionário cadastrado com sucesso!!!");
    }

    private void BuscarFuncionario() {
        System.out.print("Digite o ID do funcionário: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Funcionario funcionario = funcionarioService.BuscarFuncionarioPorID(id);

        ExibirFuncionario(funcionario);
    }

    private void ListarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.ListarTodosFuncionarios();

        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado!!!");

            return;
        }

        for (Funcionario funcionario : funcionarios) {
            ExibirFuncionario(funcionario);
        }
    }

    private void AtualizarFuncionario() {
        System.out.print("Digite o ID do funcionário: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\n1 - Nome");
        System.out.println("2 - CPF");
        System.out.println("3 - Cargo");
        System.out.println("4 - Salário");
        System.out.println("5 - Data de contratação");
        System.out.print("O que deseja atualizar? ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(id);

        switch (opcao) {

            case 1:
                System.out.print("Novo nome: ");
                funcionario.setNome(scanner.nextLine());

                break;

            case 2:
                System.out.print("Novo CPF: ");
                funcionario.setCpf(scanner.nextLine());

                break;

            case 3:
                Cargo[] cargos = Cargo.values();

                for (int i = 0; i < cargos.length; i++) {
                    System.out.println((i + 1) + " - " + cargos[i]);
                }

                System.out.print("Escolha o novo cargo: ");
                int opcaoCargo = scanner.nextInt();
                scanner.nextLine();

                funcionario.setCargo(cargos[opcaoCargo - 1]);
                break;

            case 4:
                System.out.print("Novo salário: ");
                funcionario.setSalario(scanner.nextDouble());
                scanner.nextLine();

                break;

            case 5:
                System.out.print("Nova data (AAAA-MM-DD): ");
                funcionario.setDataContratacao(LocalDate.parse(scanner.nextLine()));

                break;

            default:
                System.out.println("Opção inválida!!!");

                return;
        }

        funcionarioService.AtualizarFuncionario(funcionario, opcao);

        System.out.println("Funcionário atualizado com sucesso!!!");
    }

    private void RemoverFuncionario() {
        System.out.print("Digite o ID do funcionário: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        funcionarioService.RemoverFuncionario(id);

        System.out.println("Funcionário removido com sucesso!!!");
    }

    private void ExibirFuncionario(Funcionario funcionario) {
        System.out.println("-----------------------------");
        System.out.println("ID: " + funcionario.getIdFuncionario());
        System.out.println("Nome: " + funcionario.getNome());
        System.out.println("CPF: " + funcionario.getCpf());
        System.out.println("Cargo: " + funcionario.getCargo());
        System.out.println("Salário: " + funcionario.getSalario());
        System.out.println("Data de contratação: " + funcionario.getDataContratacao());
        System.out.println("-----------------------------");
    }
}

