package br.com.fiap.Service;

import br.com.fiap.Domain.Pedido;
import br.com.fiap.Exception.*;
import br.com.fiap.Repository.ClienteRepository;
import br.com.fiap.Repository.FuncionarioRepository;
import br.com.fiap.Repository.PedidoRepository;

import java.time.LocalDate;
import java.util.List;

public class PedidoService {
    private PedidoRepository pedidoRepository;
    private ClienteRepository clienteRepository;
    private FuncionarioRepository funcionarioRepository;

    public PedidoService(){pedidoRepository = new PedidoRepository();
    clienteRepository = new ClienteRepository();
    funcionarioRepository = new FuncionarioRepository();}


    public void InserirPedido(Pedido pedido){
        if (pedido == null)
            throw new NaoPodeSerNuloException("Pedido não pode ser nulo!!! ");

        if (pedido.getDataPedido() == null)
            throw new IllegalArgumentException("Data do pedido é obrigatória!!! ");

        if (pedido.getDataPedido().isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Data do pedido não pode ser futura!!! ");

        if (pedido.getStatus() == null)
            throw new IllegalArgumentException("Status do pedido não poder ser nulo!!! ");

        if (pedido.getValorTotal() <= 0)
            throw new IllegalArgumentException("Valor total do pedido não poder ser nulo ou menor que zero!!! ");

        if (pedido.getCliente() == null)
            throw new NaoPodeSerNuloException("Cliente do pedido não pode ser nulo!!! ");

        if (pedido.getCliente().getIdCliente() <= 0)
            throw new IDNaoPodeSerMenorQueZeroException("ID do cliente deve ser maior que zero!!! ");

        if (clienteRepository.BuscarClientePorID(pedido.getCliente().getIdCliente()) == null)
            throw new ClienteNaoEncontradoException("Cliente não encontrado!!! ");

        if (pedido.getFuncionario() == null)
            throw new NaoPodeSerNuloException("Funcionário do pedido não pode ser nulo!!! ");

        if (pedido.getFuncionario().getIdFuncionario() <= 0)
            throw new IDNaoPodeSerMenorQueZeroException("ID do funcionário deve ser maior que zero!!! ");

        if (funcionarioRepository.BuscarFuncionarioPorID(pedido.getFuncionario().getIdFuncionario()) == null)
            throw new FuncionarioNaoEncontradoException("Funcionário não encontrado!!! ");

        pedidoRepository.InserirPedido(pedido);
    }

    public Pedido BuscarPedidoPorID(int id){
        if (id <= 0)
            throw new IDNaoPodeSerMenorQueZeroException("ID tem que ser maior que zero!!! ");

        Pedido pedido = pedidoRepository.BuscarPedidoPorID(id);

        if (pedido == null)
            throw new PedidoNaoEncontradoException("Pedido não foi encontrado!!! ");

        return pedido;
    }

    public void RemoverPedido(int id){
        if (id <= 0)
            throw new IDNaoPodeSerMenorQueZeroException("ID não pode ser nulo ou menor que zero!!!");

        Pedido pedido = pedidoRepository.BuscarPedidoPorID(id);

        if (pedido == null)
            throw new PedidoNaoEncontradoException("Pedido não foi econtrado!!! ");

        pedidoRepository.RemoverPedido(id);
    }

    public List<Pedido> ListarTodosPedidos(){
        return pedidoRepository.ListarTodosPedidos();
    }

    public void AtualizarPedido(Pedido pedido, int opcao){
        if (pedido == null)
            throw new NaoPodeSerNuloException("Pedido não pode ser nulo!!! ");

        if (pedido.getIdPedido() <= 0)
            throw new IDNaoPodeSerMenorQueZeroException("ID tem que ser maior que zero!!! ");

        Pedido pedidoExistente = pedidoRepository.BuscarPedidoPorID(pedido.getIdPedido());

        if (pedidoExistente == null)
            throw new PedidoNaoEncontradoException("Pedido não foi encontrado!!! ");

        switch (opcao){
            case 1:
                if (pedido.getDataPedido() == null)
                    throw new IllegalArgumentException("Data do pedido é obrigatória!!! ");

                if (pedido.getDataPedido().isAfter(LocalDate.now()))
                    throw new IllegalArgumentException("Data do pedido não pode ser futura!!! ");

                break;

            case 2:
                if (pedido.getStatus() == null)
                    throw new IllegalArgumentException("Status do pedido não poder ser nulo!!! ");

                break;

            case 3:
                if (pedido.getValorTotal() <= 0)
                    throw new IllegalArgumentException("Valor total do pedido não poder ser nulo ou menor que zero!!! ");

                break;

            default:
                throw new IllegalArgumentException("Opção inválida!!! ");
        }

        pedidoRepository.AtualizarPedido(pedido, opcao);
    }
}
