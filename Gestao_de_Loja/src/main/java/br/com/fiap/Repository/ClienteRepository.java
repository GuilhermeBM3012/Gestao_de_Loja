package br.com.fiap.Repository;

import br.com.fiap.Domain.Cliente;
import br.com.fiap.Exception.ClienteNaoEncontradoException;
import br.com.fiap.Infrastructure.ConexaoBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {
    private ConexaoBD conexaoBD;

    public ClienteRepository(){conexaoBD = new ConexaoBD();}


    public void InserirCliente(Cliente cliente){
        String query = "insert into cliente (nome, cpf, email, telefone, data_cadastro) values (?, ?, ?, ?, NVL(?, SYSDATE))";

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)){

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getTelefone());
            ps.setDate(5, cliente.getDataCadastro() != null ?
                    java.sql.Date.valueOf(cliente.getDataCadastro()) : null);

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    int idGerado = rs.getInt(1);
                    cliente.setIdCliente(idGerado);
                }
            }

            System.out.println("Cliente cadastrado com sucesso!!! ");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente!!!\n");
            System.out.println(e);
        }
    }

    public Cliente BuscarClientePorID(int id){
        String query = "select * from cliente where id_cliente = ?";

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query)){

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()){

                if (rs.next()){
                    Cliente cliente = new Cliente();

                    cliente.setIdCliente(rs.getInt("id_cliente"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setTelefone(rs.getString("telefone"));
                    Date data = rs.getDate("data_cadastro");

                    if (data != null)
                        cliente.setDataCadastro(data.toLocalDate());

                    return cliente;
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public Cliente BuscarClientePorCPF(String cpf){
        String query = "select * from cliente where cpf = ?";

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query)){

            ps.setString(1, "cpf");

            try (ResultSet rs = ps.executeQuery()){

                if (rs.next()){
                    Cliente cliente = new Cliente();

                    cliente.setIdCliente(rs.getInt("id_cliente"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setTelefone(rs.getString("telefone"));
                    Date data = rs.getDate("data_cadastro");

                    if (data != null)
                        cliente.setDataCadastro(data.toLocalDate());

                    return cliente;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public void RemoverCliente(int id){
        String query = "delete from cliente where id_cliente = ?";

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query)){

            ps.setInt(1, id);

            int linhasAlteradas = ps.executeUpdate();

            if (linhasAlteradas > 0)
                System.out.println("Cliente removido com sucesso!!! ");
            else
                throw new ClienteNaoEncontradoException("Cliente não encontrado!!! ");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Cliente> ListarTodosClientes(){
        String query = "select * from cliente";

        List<Cliente> listaClientes = new ArrayList<>();

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()){

            while (rs.next()){
                Cliente cliente = new Cliente();

                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                Date data = rs.getDate("data_cadastro");

                if (data != null)
                    cliente.setDataCadastro(data.toLocalDate());

                listaClientes.add(cliente);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return listaClientes;
    }

    public void AtualizarCliente(Cliente cliente, int opcao) {
        String query;

        try (Connection conn = conexaoBD.Conexaobd()) {

            switch (opcao) {
                case 1:
                    query = "update cliente set nome = ? where id_cliente = ?";

                    break;

                case 2:
                    query = "update cliente set cpf = ? where id_cliente = ?";

                    break;

                case 3:
                    query = "update cliente set email = ? where id_cliente = ?";

                    break;

                case 4:
                    query = "update cliente set telefone = ? where id_cliente = ?";

                    break;

                case 5:
                    query = "update cliente set data_cadastro = ? where id_cliente = ?";

                    break;

                default:
                    System.out.println("Opção inválida!!!");

                    return;
            }

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                switch (opcao) {
                    case 1:
                        ps.setString(1, cliente.getNome());

                        break;

                    case 2:
                        ps.setString(1, cliente.getCpf());

                        break;

                    case 3:
                        ps.setString(1, cliente.getEmail());

                        break;

                    case 4:
                        ps.setString(1, cliente.getTelefone());

                        break;

                    case 5:
                        ps.setDate(1, java.sql.Date.valueOf(cliente.getDataCadastro()));

                        break;
                }

                ps.setInt(2, cliente.getIdCliente());

                int linhasAlteradas = ps.executeUpdate();

                if (linhasAlteradas > 0)
                    System.out.println("Cliente atualizado com sucesso!!!");
                else
                    throw new ClienteNaoEncontradoException("Cliente não encontrado!!!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
