package br.com.fiap.Repository;

import br.com.fiap.Domain.ItemPedido;
import br.com.fiap.Domain.Pedido;
import br.com.fiap.Domain.Produto;
import br.com.fiap.Exception.ItemPedidoNaoEncontradoException;
import br.com.fiap.Infrastructure.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemPedidoRepository {
    private ConexaoBD conexaoBD;

    public void InserirItemPedido(ItemPedido itemPedido) {

        String query = "insert into item_pedido (quantidade, preco_unitario, id_pedido, id_produto) values (?, ?, ?, ?)";

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, itemPedido.getQuantidade());
            ps.setDouble(2, itemPedido.getPrecoUnitario());
            ps.setInt(3, itemPedido.getPedido().getIdPedido());
            ps.setInt(4, itemPedido.getProduto().getIdProduto());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    itemPedido.setIdItem(idGerado);
                }
            }

            System.out.println("Item do pedido cadastrado com sucesso!!! ");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar item do pedido!!!\n");
            System.out.println(e);
        }
    }

    public ItemPedido BuscarItemPedidoPorID(int id) {

        String query = "select * from item_pedido where id_item = ?";

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    ItemPedido itemPedido = new ItemPedido();

                    itemPedido.setIdItem(rs.getInt("id_item"));
                    itemPedido.setQuantidade(rs.getInt("quantidade"));
                    itemPedido.setPrecoUnitario(rs.getDouble("preco_unitario"));

                    int idPedido = rs.getInt("id_pedido");

                    PedidoRepository pedidoRepository = new PedidoRepository();

                    Pedido pedido = pedidoRepository.BuscarPedidoPorID(idPedido);

                    itemPedido.setPedido(pedido);

                    int idProduto = rs.getInt("id_produto");

                    ProdutoRepository produtoRepository = new ProdutoRepository();

                    Produto produto = produtoRepository.BuscarProdutoPorID(idProduto);

                    itemPedido.setProduto(produto);

                    return itemPedido;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public List<ItemPedido> ListarTodosItensPedido() {

        String query = "select * from item_pedido";

        List<ItemPedido> listaItens = new ArrayList<>();

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ItemPedido itemPedido = new ItemPedido();

                itemPedido.setIdItem(rs.getInt("id_item"));
                itemPedido.setQuantidade(rs.getInt("quantidade"));
                itemPedido.setPrecoUnitario(rs.getDouble("preco_unitario"));

                int idPedido = rs.getInt("id_pedido");

                PedidoRepository pedidoRepository = new PedidoRepository();

                Pedido pedido = pedidoRepository.BuscarPedidoPorID(idPedido);

                itemPedido.setPedido(pedido);

                int idProduto = rs.getInt("id_produto");

                ProdutoRepository produtoRepository = new ProdutoRepository();

                Produto produto = produtoRepository.BuscarProdutoPorID(idProduto);

                itemPedido.setProduto(produto);

                listaItens.add(itemPedido);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return listaItens;
    }

    public void RemoverItemPedido(int id) {

        String query = "delete from item_pedido where id_item = ?";

        try (Connection conn = conexaoBD.Conexaobd();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);

            int linhasAlteradas = ps.executeUpdate();

            if (linhasAlteradas > 0)
                System.out.println("Item do pedido removido com sucesso!!! ");
            else
                throw new ItemPedidoNaoEncontradoException("Item do pedido não encontrado!!! ");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void AtualizarItemPedido(ItemPedido itemPedido, int opcao) {
        String query;

        try (Connection conn = conexaoBD.Conexaobd()){

            switch (opcao) {
                case 1:
                    query = "update item_pedido set quantidade = ? where id_item = ?";

                    break;

                case 2:
                    query = "update item_pedido set preco_unitario = ? where id_item = ?";

                    break;

                default:
                    System.out.println("Opção inválida!!! ");

                    return;
            }

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                switch (opcao) {
                    case 1:
                        ps.setInt(1, itemPedido.getQuantidade());

                        break;

                    case 2:
                        ps.setDouble(1, itemPedido.getPrecoUnitario());

                        break;
                }

                ps.setInt(2, itemPedido.getIdItem());

                int linhasAlteradas = ps.executeUpdate();

                if (linhasAlteradas > 0)
                    System.out.println("Item do pedido atualizado com sucesso!!! ");
                else
                    throw new ItemPedidoNaoEncontradoException("Item do pedido não encontrado!!! ");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
