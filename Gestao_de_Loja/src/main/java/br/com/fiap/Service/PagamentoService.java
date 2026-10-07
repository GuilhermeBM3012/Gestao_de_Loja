package br.com.fiap.Service;

import br.com.fiap.Domain.Pagamento;
import br.com.fiap.Exception.IDNaoPodeSerMenorQueZeroException;
import br.com.fiap.Exception.NaoPodeSerNuloException;
import br.com.fiap.Exception.PagamentoNaoEncontradoException;
import br.com.fiap.Exception.PedidoNaoEncontradoException;
import br.com.fiap.Repository.PagamentoRepository;
import br.com.fiap.Repository.PedidoRepository;

import java.time.LocalDate;
import java.util.List;

public class PagamentoService {
    private PagamentoRepository pagamentoRepository;
    private PedidoRepository pedidoRepository;

    public PagamentoService() {pagamentoRepository = new PagamentoRepository();
        pedidoRepository = new PedidoRepository();
    }


    public void InserirPagamento(Pagamento pagamento) {
        if (pagamento == null)
            throw new NaoPodeSerNuloException("Pagamento não pode ser nulo!!! ");

        if (pagamento.getTipoPagamento() == null)
            throw new IllegalArgumentException("Tipo de pagamento não pode ser nulo!!! ");

        if (pagamento.getValor() <= 0)
            throw new IllegalArgumentException("Valor do pagamento deve ser maior que zero!!! ");

        if (pagamento.getDataPagamento() == null)
            throw new IllegalArgumentException("Data do pagamento é obrigatória!!! ");

        if (pagamento.getDataPagamento().isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Data do pagamento não pode ser futura!!! ");

        if (pagamento.getStatus() == null)
            throw new IllegalArgumentException("Status do pagamento não pode ser nulo!!! ");

        if (pagamento.getPedido() == null)
            throw new NaoPodeSerNuloException("Pedido do pagamento não pode ser nulo!!! ");

        if (pagamento.getPedido().getIdPedido() <= 0)
            throw new IDNaoPodeSerMenorQueZeroException("ID do pedido deve ser maior que zero!!! ");

        if (pedidoRepository.BuscarPedidoPorID(
                pagamento.getPedido().getIdPedido()) == null)
            throw new PedidoNaoEncontradoException("Pedido não encontrado!!! ");

        pagamentoRepository.InserirPagamento(pagamento);
    }

    public Pagamento BuscarPagamentoPorID(int id) {
        if (id <= 0)
            throw new IDNaoPodeSerMenorQueZeroException("ID tem que ser maior que zero!!! ");

        Pagamento pagamento = pagamentoRepository.BuscarPagamentoPorID(id);

        if (pagamento == null)
            throw new PagamentoNaoEncontradoException("Pagamento não encontrado!!! ");

        return pagamento;
    }

    public List<Pagamento> ListarTodosPagamentos() {
        return pagamentoRepository.ListarTodosPagamentos();
    }

    public void RemoverPagamento(int id) {
        if (id <= 0)
            throw new IDNaoPodeSerMenorQueZeroException("ID tem que ser maior que zero!!! ");

        Pagamento pagamento = pagamentoRepository.BuscarPagamentoPorID(id);

        if (pagamento == null)
            throw new PagamentoNaoEncontradoException("Pagamento não encontrado!!! ");

        pagamentoRepository.RemoverPagamento(id);
    }

    public void AtualizarPagamento(Pagamento pagamento, int opcao) {
        if (pagamento == null)
            throw new NaoPodeSerNuloException("Pagamento não pode ser nulo!!! ");

        if (pagamento.getIdPagamento() <= 0)
            throw new IDNaoPodeSerMenorQueZeroException("ID tem que ser maior que zero!!! ");

        Pagamento pagamentoExistente = pagamentoRepository.BuscarPagamentoPorID(pagamento.getIdPagamento());

        if (pagamentoExistente == null)
            throw new PagamentoNaoEncontradoException("Pagamento não encontrado!!! ");

        switch (opcao) {
            case 1:
                if (pagamento.getTipoPagamento() == null)
                    throw new IllegalArgumentException("Tipo de pagamento não pode ser nulo!!! ");

                break;

            case 2:
                if (pagamento.getValor() <= 0)
                    throw new IllegalArgumentException("Valor do pagamento deve ser maior que zero!!! ");

                break;

            case 3:
                if (pagamento.getDataPagamento() == null)
                    throw new IllegalArgumentException("Data do pagamento é obrigatória!!! ");

                if (pagamento.getDataPagamento().isAfter(LocalDate.now()))
                    throw new IllegalArgumentException("Data do pagamento não pode ser futura!!! ");

                break;

            case 4:
                if (pagamento.getStatus() == null)
                    throw new IllegalArgumentException("Status do pagamento não pode ser nulo!!! ");

                break;

            default:
                throw new IllegalArgumentException("Opção inválida!!! ");
        }

        pagamentoRepository.AtualizarPagamento(pagamento, opcao);
    }
}
