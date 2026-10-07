package br.com.fiap.Repository;

import br.com.fiap.Domain.Cliente;
import br.com.fiap.Domain.Funcionario;
import br.com.fiap.Domain.Pedido;
import br.com.fiap.Domain.StatusPedido;
import br.com.fiap.Exception.PagamentoNaoEncontradoException;
import br.com.fiap.Exception.PedidoNaoEncontradoException;
import br.com.fiap.Infrastructure.ConexaoBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepository {
    private ConexaoBD conexaoBD;

    public PedidoRepository(){conexaoBD = new ConexaoBD();}


    public void InserirPedido(Pedido pedido){
        String query = "insert into pedido (data_pedido, status, valor_total, id_cliente, id_funcionario) values (?, ?, ?, ?, ?)";

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)){

            ps.setDate(1, pedido.getDataPedido() != null ?
                    java.sql.Date.valueOf(pedido.getDataPedido()) : null);
            ps.setString(2, pedido.getStatus().name());
            ps.setDouble(3, pedido.getValorTotal());
            ps.setInt(4, pedido.getCliente().getIdCliente());
            ps.setInt(5, pedido.getFuncionario().getIdFuncionario());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    int idGerado = rs.getInt(1);
                    pedido.setIdPedido(idGerado);
                }
            }

            System.out.println("Pedido cadastrado com sucesso!!! ");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar pedido!!! ");
            System.out.println(e);
        }
    }

    public Pedido BuscarPedidoPorID(int id){
        String query = "select * from pedido where id_pedido = ?";

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query)){

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()){

                if (rs.next()){
                    Pedido pedido = new Pedido();

                    pedido.setIdPedido(rs.getInt("id_pedido"));
                    Date data = rs.getDate("data_pedido");

                    if (data != null)
                        pedido.setDataPedido(data.toLocalDate());

                    pedido.setStatus(StatusPedido.valueOf(rs.getString("status")));
                    pedido.setValorTotal(rs.getDouble("valor_total"));

                    int idCliente = rs.getInt("id_cliente");

                    ClienteRepository clienteRepository = new ClienteRepository();

                    Cliente cliente = clienteRepository.BuscarClientePorID(idCliente);

                    pedido.setCliente(cliente);

                    int idFuncionario = rs.getInt("id_funcionario");

                    FuncionarioRepository funcionarioRepository = new FuncionarioRepository();

                    Funcionario funcionario = funcionarioRepository.BuscarFuncionarioPorID(idFuncionario);

                    pedido.setFuncionario(funcionario);

                    return pedido;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public void RemoverPedido(int id){
        String query = "delete from pedido where id_pedido = ?";

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query)){

            ps.setInt(1, id);

            int linhasAlteradas = ps.executeUpdate();

            if (linhasAlteradas > 0)
                System.out.println("Pedido removido com sucesso!!! ");
            else
                throw new PedidoNaoEncontradoException("Pedido não encontrado!!! ");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Pedido> ListarTodosPedidos(){
        String query = "select * from pedido";

        List<Pedido> listaPedidos = new ArrayList<>();

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery();){

            while (rs.next()){
                Pedido pedido = new Pedido();

                pedido.setIdPedido(rs.getInt("id_pedido"));
                Date data = rs.getDate("data_pedido");

                if (data != null)
                    pedido.setDataPedido(data.toLocalDate());

                pedido.setStatus(StatusPedido.valueOf(rs.getString("status")));
                pedido.setValorTotal(rs.getDouble("valor_total"));

                int idCliente = rs.getInt("id_cliente");

                ClienteRepository clienteRepository = new ClienteRepository();

                Cliente cliente = clienteRepository.BuscarClientePorID(idCliente);

                pedido.setCliente(cliente);

                int idFuncionario = rs.getInt("id_funcionario");

                FuncionarioRepository funcionarioRepository = new FuncionarioRepository();

                Funcionario funcionario = funcionarioRepository.BuscarFuncionarioPorID(idFuncionario);

                pedido.setFuncionario(funcionario);

                listaPedidos.add(pedido);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return listaPedidos;
    }

    public void AtualizarPedido(Pedido pedido, int opcao){
        String query;

        try (Connection conn = conexaoBD.Conexaobd()){

            switch (opcao){
                case 1:
                    query = "update pedido set data_pedido = ? where id_pedido = ?";

                    break;

                case 2:
                    query = "update pedido set status = ? where id_pedido = ?";

                    break;

                case 3:
                    query = "update pedido set valor_total = ? where id_pedido = ?";

                    break;

                default:
                    System.out.println("Opção inválida!!! ");

                    return;
            }

            try (PreparedStatement ps = conn.prepareStatement(query)){

                switch (opcao){
                    case 1:
                        ps.setDate(1, java.sql.Date.valueOf(pedido.getDataPedido()));

                        break;

                    case 2:
                        ps.setString(1, pedido.getStatus().name());

                        break;

                    case 3:
                        ps.setDouble(1, pedido.getValorTotal());

                        break;
                }

                ps.setInt(2, pedido.getIdPedido());

                int linhasAlteradas = ps.executeUpdate();

                if (linhasAlteradas> 0)
                    System.out.println("Pedido atualizado com sucesso!!! ");
                else
                    throw new PedidoNaoEncontradoException("Pedido não encontrado!!! ");

            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
