package br.com.fiap.Service;

import br.com.fiap.Domain.ItemPedido;
import br.com.fiap.Exception.*;
import br.com.fiap.Repository.ItemPedidoRepository;
import br.com.fiap.Repository.PedidoRepository;
import br.com.fiap.Repository.ProdutoRepository;

import java.util.List;

public class ItemPedidoService {
    private ItemPedidoRepository itemPedidoRepository;

    private PedidoRepository pedidoRepository;
    private ProdutoRepository produtoRepository;

    public ItemPedidoService(){itemPedidoRepository = new ItemPedidoRepository();
    pedidoRepository = new PedidoRepository();
    produtoRepository = new ProdutoRepository();}

    public void InserirItemPedido(ItemPedido itemPedido) {

        if (itemPedido == null)
            throw new NaoPodeSerNuloException("Item do pedido não pode ser nulo!!! ");

        if (itemPedido.getQuantidade() <= 0)
            throw new IllegalArgumentException("Quantidade deve ser maior que zero!!! ");

        if (itemPedido.getPrecoUnitario() <= 0)
            throw new IllegalArgumentException("Preço unitário deve ser maior que zero!!! ");
        if (itemPedido.getPedido() == null)
            throw new NaoPodeSerNuloException("Pedido do item não pode ser nulo!!! ");

        if (itemPedido.getPedido().getIdPedido() <= 0)
            throw new IDNaoPodeSerMenorQueZeroException("ID do pedido deve ser maior que zero!!! ");

        if (pedidoRepository.BuscarPedidoPorID(itemPedido.getPedido().getIdPedido()) == null)
            throw new PedidoNaoEncontradoException("Pedido não encontrado!!! ");

        if (itemPedido.getProduto() == null)
            throw new NaoPodeSerNuloException("Produto do item não pode ser nulo!!! ");

        if (itemPedido.getProduto().getIdProduto() <= 0)
            throw new IDNaoPodeSerMenorQueZeroException("ID do produto deve ser maior que zero!!! ");

        if (produtoRepository.BuscarProdutoPorID(itemPedido.getProduto().getIdProduto()) == null)
            throw new ProdutoNaoEncontradoException("Produto não encontrado!!! ");

        itemPedidoRepository.InserirItemPedido(itemPedido);
    }

    public ItemPedido BuscarItemPedidoPorID(int id) {
        if (id <= 0)
            throw new IDNaoPodeSerMenorQueZeroException("ID tem que ser maior que zero!!! ");

        ItemPedido itemPedido = itemPedidoRepository.BuscarItemPedidoPorID(id);

        if (itemPedido == null)
            throw new ItemPedidoNaoEncontradoException("Item do pedido não encontrado!!! ");

        return itemPedido;
    }

    public List<ItemPedido> ListarTodosItensPedido() {
        return itemPedidoRepository.ListarTodosItensPedido();
    }

    public void RemoverItemPedido(int id) {
        if (id <= 0)
            throw new IDNaoPodeSerMenorQueZeroException("ID tem que ser maior que zero!!! ");

        ItemPedido itemPedido = itemPedidoRepository.BuscarItemPedidoPorID(id);

        if (itemPedido == null)
            throw new ItemPedidoNaoEncontradoException("Item do pedido não encontrado!!! ");

        itemPedidoRepository.RemoverItemPedido(id);
    }

    public void AtualizarItemPedido(
            ItemPedido itemPedido, int opcao) {

        if (itemPedido == null)
            throw new NaoPodeSerNuloException("Item do pedido não pode ser nulo!!! ");

        if (itemPedido.getIdItem() <= 0)
            throw new IDNaoPodeSerMenorQueZeroException("ID tem que ser maior que zero!!! ");

        ItemPedido itemPedidoExistente = itemPedidoRepository.BuscarItemPedidoPorID(itemPedido.getIdItem());

        if (itemPedidoExistente == null)
            throw new ItemPedidoNaoEncontradoException("Item do pedido não encontrado!!! ");

        switch (opcao) {
            case 1:
                if (itemPedido.getQuantidade() <= 0)
                    throw new IllegalArgumentException("Quantidade deve ser maior que zero!!! ");

                break;

            case 2:
                if (itemPedido.getPrecoUnitario() <= 0)
                    throw new IllegalArgumentException("Preço unitário deve ser maior que zero!!! ");

                break;

            default:
                throw new IllegalArgumentException("Opção inválida!!! ");
        }

        itemPedidoRepository.AtualizarItemPedido(itemPedido, opcao);
    }
}
