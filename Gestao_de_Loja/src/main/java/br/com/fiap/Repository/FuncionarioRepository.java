package br.com.fiap.Repository;

import br.com.fiap.Domain.Cargo;
import br.com.fiap.Domain.Cliente;
import br.com.fiap.Domain.Funcionario;
import br.com.fiap.Exception.FuncionarioNaoEncontradoException;
import br.com.fiap.Infrastructure.ConexaoBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepository {
    private ConexaoBD conexaoBD;

    public FuncionarioRepository(){conexaoBD = new ConexaoBD();}


    public void InserirFuncionario(Funcionario funcionario){
        String query = "insert into funcionario (nome, cpf, cargo, salario, data_contratacao) values (?, ?, ?, ?, NVL(?, SYSDATE))";

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)){

            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCpf());
            ps.setString(3, funcionario.getCargo().name());
            ps.setDouble(4, funcionario.getSalario());
            ps.setDate(5, funcionario.getDataContratacao() != null ?
                    java.sql.Date.valueOf(funcionario.getDataContratacao()) : null);

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    int idGerado = rs.getInt(1);
                    funcionario.setIdFuncionario(idGerado);
                }
            }

            System.out.println("Funcionário cadastrado com sucesso!!! ");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar funcionário!!!\n");
            System.out.println(e);
        }
    }

    public Funcionario BuscarFuncionarioPorID(int id){
        String query = "select * from funcionario where id_funcionario = ?";

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query)){

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()){

                if (rs.next()){
                    Funcionario funcionario = new Funcionario();

                    funcionario.setIdFuncionario(rs.getInt("id_funcionario"));
                    funcionario.setNome(rs.getString("nome"));
                    funcionario.setCpf(rs.getString("cpf"));
                    funcionario.setCargo(Cargo.valueOf(rs.getString("cargo")));
                    funcionario.setSalario(rs.getDouble("salario"));
                    Date data = rs.getDate("data_contratacao");

                    if (data != null){
                        funcionario.setDataContratacao(data.toLocalDate());
                    }

                    return funcionario;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return  null;
    }

    public Funcionario BuscarFuncionarioPorCPF(String cpf){
        String query = "select * from funcionario where cpf = ?";

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query)){

            ps.setString(1, "cpf");

            try (ResultSet rs = ps.executeQuery()){

                if (rs.next()){
                    Funcionario funcionario = new Funcionario();

                    funcionario.setIdFuncionario(rs.getInt("id_cliente"));
                    funcionario.setNome(rs.getString("nome"));
                    funcionario.setCpf(rs.getString("cpf"));
                    funcionario.setCargo(Cargo.valueOf(rs.getString("cargo")));
                    funcionario.setSalario(rs.getDouble("salario"));
                    Date data = rs.getDate("data_cadastro");

                    if (data != null)
                        funcionario.setDataContratacao(data.toLocalDate());

                    return funcionario;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public void RemoverFuncionario(int id){
        String query = "delete from funcionario where id = ?";

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query)){

            ps.setInt(1, id);

            int linhasAlteradas = ps.executeUpdate();

            if (linhasAlteradas > 0)
                System.out.println("Funcionário removido com sucesso!!! ");
            else
                throw new FuncionarioNaoEncontradoException("Funcionário não encontrado!!! ");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Funcionario> ListarTodosFuncionarios(){
        String query = "select * from funcionario";

        List<Funcionario> listaFuncionarios = new ArrayList<>();

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()){

            while (rs.next()){
                Funcionario funcionario = new Funcionario();

                funcionario.setIdFuncionario(rs.getInt("id_funcionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setCargo(Cargo.valueOf(rs.getString("cargo")));
                funcionario.setSalario(rs.getDouble("salario"));
                Date data = rs.getDate("data_contratacao");

                if (data != null)
                    funcionario.setDataContratacao(data.toLocalDate());

                listaFuncionarios.add(funcionario);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return listaFuncionarios;
    }

    public void AtualizarFuncionario(Funcionario funcionario, int opcao){
        String query;

        try (Connection conn = conexaoBD.Conexaobd()){

            switch (opcao){
                case 1:
                    query = "update funcionario set nome = ? where id_funcionario = ?";

                    break;

                case 2:
                    query = "update funcionario set cpf = ? where id_funcionario = ?";

                    break;

                case 3:
                    query = "update funcionario set cargo = ? where id_funcionario = ?";

                    break;

                case 4:
                    query = "update funcionario set salario = ? where id_funcionario = ?";

                    break;

                case 5:
                    query = "update funcionario set data_contratacao = ? where id_funcionario = ?";

                    break;

                default:
                    System.out.println("Opção inválida!!! ");

                    return;
            }

            try (PreparedStatement ps = conn.prepareStatement(query)){

                switch (opcao) {
                    case 1:
                        ps.setString(1, funcionario.getNome());

                        break;

                    case 2:
                        ps.setString(1, funcionario.getCpf());

                        break;

                    case 3:
                        ps.setString(1, funcionario.getCargo().name());

                        break;

                    case 4:
                        ps.setDouble(1, funcionario.getSalario());

                        break;

                    case 5:
                        ps.setDate(1, java.sql.Date.valueOf(funcionario.getDataContratacao()));

                        break;
                }

                ps.setInt(2, funcionario.getIdFuncionario());

                int linhasAlteradas = ps.executeUpdate();

                if (linhasAlteradas > 0)
                    System.out.println("Funcionário atualizado com sucesso!!! ");
                else
                    throw new FuncionarioNaoEncontradoException("Funcionário não encontrado!!! ");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
