package br.com.fiap.Service;

import br.com.fiap.Domain.Cliente;
import br.com.fiap.Exception.ClienteNaoEncontradoException;
import br.com.fiap.Exception.IDNaoPodeSerMenorQueZeroException;
import br.com.fiap.Exception.NaoPodeSerNuloException;
import br.com.fiap.Repository.ClienteRepository;

import java.time.LocalDate;
import java.util.List;

public class ClienteService {
    private ClienteRepository clienteRepository;

    public ClienteService() {clienteRepository = new ClienteRepository();}


    public void InserirCliente(Cliente cliente) {
        if (cliente == null)
            throw new NaoPodeSerNuloException("Cliente não pode ser nulo!!! ");

        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty())
            throw new IllegalArgumentException("Nome do cliente é obrigatório!!! ");

        if (cliente.getCpf() == null || cliente.getCpf().trim().isEmpty())
            throw new IllegalArgumentException("CPF do cliente é obrigatório!!! ");

        if (cliente.getCpf().length() != 11)
            throw new IllegalArgumentException("CPF deve possuir 11 caracteres!!! ");

        if (cliente.getEmail() != null && !cliente.getEmail().trim().isEmpty()) {
            if (!cliente.getEmail().contains("@"))
                throw new IllegalArgumentException("E-mail inválido!!! ");
        }

        if (cliente.getTelefone() != null && !cliente.getTelefone().trim().isEmpty()) {
            if (cliente.getTelefone().length() < 8)
                throw new IllegalArgumentException("Telefone inválido!!! ");
        }

        if (cliente.getDataCadastro() != null)
            throw new IllegalArgumentException("Data de cadastro é obrigatória!!! ");

        if (cliente.getDataCadastro().isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Data de cadastro não pode ser futura!!! ");

        Cliente clienteExistente = clienteRepository.BuscarClientePorCPF(cliente.getCpf());

        if (clienteExistente != null)
            throw new IllegalArgumentException("Já existe um cliente cadastrado com esse CPF.");

        clienteRepository.InserirCliente(cliente);
    }


    public Cliente BuscarClientePorID(int id) {
        if (id <= 0)
            throw new IDNaoPodeSerMenorQueZeroException("ID do cliente deve ser maior que zero!!! ");

        Cliente cliente = clienteRepository.BuscarClientePorID(id);

        if (cliente == null)
            throw new ClienteNaoEncontradoException("Cliente não encontrado!!! ");

        return cliente;
    }

    public List<Cliente> ListarTodosClientes() {
        return clienteRepository.ListarTodosClientes();
    }

    public void RemoverCliente(int id) {
        if (id <= 0)
            throw new IDNaoPodeSerMenorQueZeroException("ID do cliente deve ser maior que zero!!! ");


        Cliente cliente = clienteRepository.BuscarClientePorID(id);

        if (cliente == null)
            throw new ClienteNaoEncontradoException("Cliente não encontrado!!! ");


        clienteRepository.RemoverCliente(id);
    }

    public void AtualizarCliente(Cliente cliente, int opcao) {
        if (cliente == null)
            throw new NaoPodeSerNuloException("Cliente não pode ser nulo!!! ");


        if (cliente.getIdCliente() <= 0)
            throw new IDNaoPodeSerMenorQueZeroException("ID do cliente inválido!!! ");


        Cliente clienteExistente = clienteRepository.BuscarClientePorID(cliente.getIdCliente());

        if (clienteExistente == null)
            throw new ClienteNaoEncontradoException("Cliente não encontrado!!! ");


        switch (opcao) {
            case 1:
                if (cliente.getNome() == null || cliente.getNome().trim().isEmpty())
                    throw new IllegalArgumentException("Nome do cliente é obrigatório!!! ");

                break;

            case 2:
                if (cliente.getCpf() == null || cliente.getCpf().trim().isEmpty())
                    throw new IllegalArgumentException("CPF do cliente é obrigatório!!! ");

                if (cliente.getCpf().length() != 11)
                    throw new IllegalArgumentException("CPF deve possuir 11 caracteres!!! ");

                Cliente cliExistente = clienteRepository.BuscarClientePorCPF(cliente.getCpf());

                if (cliExistente != null && cliExistente.getIdCliente() != cliente.getIdCliente())
                    throw new IllegalArgumentException("Já existe um cliente cadastrado com esse CPF.");

                break;

            case 3:
                if (cliente.getEmail() != null && !cliente.getEmail().trim().isEmpty()) {
                    if (!cliente.getEmail().contains("@"))
                        throw new IllegalArgumentException("E-mail inválido!!! ");
                }

                break;

            case 4:
                if (cliente.getTelefone() != null && !cliente.getTelefone().trim().isEmpty()) {
                    if (cliente.getTelefone().length() < 8)
                        throw new IllegalArgumentException("Telefone inválido!!! ");
                }

                break;

            case 5:
                if (cliente.getDataCadastro() == null)
                    throw new IllegalArgumentException("Data de cadastro é obrigatória!!! ");


                if (cliente.getDataCadastro().isAfter(LocalDate.now()))
                    throw new IllegalArgumentException("Data de cadastro não pode ser futura!!! ");

                break;

            default:
                throw new IllegalArgumentException("Opção de atualização inválida!!! ");
        }

        clienteRepository.AtualizarCliente(cliente, opcao);

    }
}
