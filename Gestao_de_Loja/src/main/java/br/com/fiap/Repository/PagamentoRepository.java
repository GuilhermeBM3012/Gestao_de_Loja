package br.com.fiap.Repository;

import br.com.fiap.Domain.Pagamento;
import br.com.fiap.Domain.Pedido;
import br.com.fiap.Domain.StatusPagamento;
import br.com.fiap.Domain.TipoPagamento;
import br.com.fiap.Exception.PagamentoNaoEncontradoException;
import br.com.fiap.Infrastructure.ConexaoBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagamentoRepository {
    private ConexaoBD conexaoBD;

    public PagamentoRepository(){conexaoBD = new ConexaoBD();}


    public void InserirPagamento(Pagamento pagamento){
        String query = "insert into pagamento (tipo_pagamento, valor, data_pagamento, status, id_pedido) values (?, ?, NVL(?, SYSDATE), " +
                "?, ?)";

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)){

            ps.setString(1, pagamento.getTipoPagamento().name());
            ps.setDouble(2, pagamento.getValor());
            ps.setDate(3, pagamento.getDataPagamento() != null ?
                    java.sql.Date.valueOf(pagamento.getDataPagamento()) : null);
            ps.setString(4, pagamento.getStatus().name());
            ps.setInt(5, pagamento.getPedido().getIdPedido());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    int idGerado = rs.getInt(1);
                    pagamento.setIdPagamento(idGerado);
                }
            }

            System.out.println("Pagamento cadastrado com sucesso!!! ");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar pagamento!!!\n");
            System.out.println(e);
        }
    }

    public Pagamento BuscarPagamentoPorID(int id){
        String query = "select * from pagamento where id_pagamento = ?";

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query)){

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()){

                if (rs.next()){
                    Pagamento pagamento = new Pagamento();

                    pagamento.setIdPagamento(rs.getInt("id_pagamento"));
                    pagamento.setTipoPagamento(TipoPagamento.valueOf(rs.getString("tipo_pagamento")));
                    pagamento.setValor(rs.getDouble("valor"));
                    Date data = rs.getDate("data_pagamento");

                    if (data != null)
                        pagamento.setDataPagamento(data.toLocalDate());

                    pagamento.setStatus(StatusPagamento.valueOf(rs.getString("status")));

                    int idPedido = rs.getInt("id_pedido");

                    PedidoRepository pedidoRepository = new PedidoRepository();

                    Pedido pedido = pedidoRepository.BuscarPedidoPorID(idPedido);

                    pagamento.setPedido(pedido);

                    return pagamento;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public void RemoverPagamento(int id){
        String query = "delete from pagamento where id_pagamento = ?";

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query)){

            ps.setInt(1, id);

            int linhasAlteradas = ps.executeUpdate();

            if (linhasAlteradas > 0)
                System.out.println("Pagamento removido com sucesso!!! ");
            else
                throw new PagamentoNaoEncontradoException("Pagamento não encontrado!!! ");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Pagamento> ListarTodosPagamentos(){
        String query = "select * from pagamento";

        List<Pagamento> listaPagamentos = new ArrayList<>();

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery();){

            while (rs.next()){
                Pagamento pagamento = new Pagamento();

                pagamento.setIdPagamento(rs.getInt("id_pagamento"));
                pagamento.setTipoPagamento(TipoPagamento.valueOf(rs.getString("tipo_pagamento")));
                pagamento.setValor(rs.getDouble("valor"));
                Date data = rs.getDate("data_pagamento");

                if (data != null)
                    pagamento.setDataPagamento(data.toLocalDate());

                pagamento.setStatus(StatusPagamento.valueOf(rs.getString("status")));

                int idPedido = rs.getInt("id_pedido");

                PedidoRepository pedidoRepository = new PedidoRepository();

                Pedido pedido = pedidoRepository.BuscarPedidoPorID(idPedido);

                pagamento.setPedido(pedido);

                listaPagamentos.add(pagamento);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return listaPagamentos;
    }

    public void AtualizarPagamento(Pagamento pagamento, int opcao){
        String query;

        try (Connection conn = conexaoBD.Conexaobd()){

            switch (opcao){
                case 1:
                    query = "update pagamento set tipo_pagamento = ? where id_pagamento = ?";

                    break;

                case 2:
                    query = "update pagamento set valor = ? where id_pagamento = ?";

                    break;

                case 3:
                    query = "update pagamento set data_pagamento = ? where id_pagamento = ?";

                    break;

                case 4:
                    query = "update pagamento set status = ? where id_pagamento = ?";

                    break;

                default:
                    System.out.println("Opção inválida!!! ");

                    return;
            }

            try (PreparedStatement ps = conn.prepareStatement(query)){

                switch (opcao){
                    case 1:
                        ps.setString(1, pagamento.getTipoPagamento().name());

                        break;

                    case 2:
                        ps.setDouble(1, pagamento.getValor());

                        break;

                    case 3:
                        ps.setDate(1, java.sql.Date.valueOf(pagamento.getDataPagamento()));

                        break;

                    case 4:
                        ps.setString(1, pagamento.getStatus().name());

                        break;
                }

                ps.setInt(2, pagamento.getIdPagamento());

                int linhasAlteradas = ps.executeUpdate();

                if (linhasAlteradas> 0)
                    System.out.println("Pagamento atualizado com sucesso!!! ");
                else
                    throw new PagamentoNaoEncontradoException("Pagamento não encontrado!!! ");

            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
