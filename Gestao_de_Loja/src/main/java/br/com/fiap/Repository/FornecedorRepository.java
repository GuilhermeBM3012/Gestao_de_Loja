package br.com.fiap.Repository;

import br.com.fiap.Domain.Fornecedor;
import br.com.fiap.Exception.FornecedorNaoEncontradoException;
import br.com.fiap.Infrastructure.ConexaoBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedorRepository {
    private ConexaoBD conexaoBD;

    public FornecedorRepository(){conexaoBD = new ConexaoBD();}


    public void InserirFornecedor(Fornecedor fornecedor){
        String query = "insert into fornecedor (nome, cnpj, email, telefone) values (?, ?, ?, ?)";

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)){

            ps.setString(1, fornecedor.getNome());
            ps.setString(2, fornecedor.getCnpj());
            ps.setString(3, fornecedor.getEmail());
            ps.setString(4, fornecedor.getTelefone());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    fornecedor.setIdFornecedor(idGerado);
                }
            }

            System.out.println("Fornecedor cadastrado com sucesso!!! ");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar fornecedor!!!\n");
            System.out.println(e);
        }
    }

    public Fornecedor BuscarFornecedorPorID(int id){
        String query = "select * from fornecedor where id_fornecedor = ?";

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query)){

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()){

                if (rs.next()){
                    Fornecedor fornecedor = new Fornecedor();

                    fornecedor.setIdFornecedor(rs.getInt("id_fornecedor"));
                    fornecedor.setNome(rs.getString("nome"));
                    fornecedor.setCnpj(rs.getString("cnpj"));
                    fornecedor.setEmail(rs.getString("email"));
                    fornecedor.setTelefone(rs.getString("telefone"));

                    return fornecedor;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public Fornecedor BuscarFornecedorPorCNPJ(String cnpj){
        String query = "select * from fornecedor where cnpj = ?";

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query)){

            ps.setString(1, cnpj);

            try (ResultSet rs = ps.executeQuery()){

                if (rs.next()){
                    Fornecedor fornecedor = new Fornecedor();

                    fornecedor.setIdFornecedor(rs.getInt("id_fornecedor"));
                    fornecedor.setNome(rs.getString("nome"));
                    fornecedor.setCnpj(rs.getString("cnpj"));
                    fornecedor.setEmail(rs.getString("email"));
                    fornecedor.setTelefone(rs.getString("telefone"));

                    return fornecedor;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public void RemoverFornecedor(int id){
        String query = "delete from fornecedor where id_fornecedor = ?";

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query)){

            ps.setInt(1, id);

            int linhasAlteradas = ps.executeUpdate();

            if (linhasAlteradas > 0)
                System.out.println("Fornecedor removido com sucesso!!! ");
            else
                throw new FornecedorNaoEncontradoException("Fornecedor não encontrado!!! ");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Fornecedor> ListarTodosFornecedores(){
        String query = "select * from fornecedor";

        List<Fornecedor> listaFornecedor = new ArrayList<>();

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()){

            while (rs.next()){
                Fornecedor fornecedor = new Fornecedor();

                fornecedor.setIdFornecedor(rs.getInt("id_fornecedor"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setEmail(rs.getString("email"));
                fornecedor.setTelefone(rs.getString("telefone"));

                listaFornecedor.add(fornecedor);
            }


        } catch (Exception e) {
            System.out.println(e);
        }

        return listaFornecedor;
    }

    public void AtualizarFornecedor(Fornecedor fornecedor, int opcao){
        String query;

        try (Connection conn = conexaoBD.Conexaobd()){

            switch (opcao){
                case 1:
                    query = "update fornecedor set nome = ? where id_fornecedor = ?";

                    break;

                case 2:
                    query = "update fornecedor set cnpj = ? where id_fornecedor = ?";

                    break;

                case 3:
                    query = "update fornecedor set email = ? where id_fornecedor = ?";

                    break;

                case 4:
                    query = "update fornecedor set telefone = ? where id_fornecedor = ?";

                    break;

                default:
                    System.out.println("Opção inválida!!! ");

                    return;
            }

            try (PreparedStatement ps = conn.prepareStatement(query)){

                switch (opcao){
                    case 1:
                        ps.setString(1, fornecedor.getNome());

                        break;

                    case 2:
                        ps.setString(1, fornecedor.getCnpj());

                        break;

                    case 3:
                        ps.setString(1, fornecedor.getEmail());

                        break;

                    case 4:
                        ps.setString(1, fornecedor.getTelefone());

                        break;
                }

                ps.setInt(2, fornecedor.getIdFornecedor());

                int linhasAlteradas = ps.executeUpdate();

                if (linhasAlteradas > 0)
                    System.out.println("Fornecedor atualizado com sucesso!!! ");
                else
                    throw new FornecedorNaoEncontradoException("Fornecedor não encontrado!!! ");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
