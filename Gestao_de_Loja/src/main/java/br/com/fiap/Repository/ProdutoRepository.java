package br.com.fiap.Repository;

import br.com.fiap.Domain.Fornecedor;
import br.com.fiap.Domain.Produto;
import br.com.fiap.Exception.PagamentoNaoEncontradoException;
import br.com.fiap.Exception.PedidoNaoEncontradoException;
import br.com.fiap.Exception.ProdutoNaoEncontradoException;
import br.com.fiap.Infrastructure.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {
    private ConexaoBD conexaoBD;

    public ProdutoRepository(){conexaoBD = new ConexaoBD();}


    public void InserirProduto(Produto produto){
        String query = "insert into produto (nome, descricao, preco, quantidade_estoque, id_fornecedor) values (?, ?, ?, ?, ?)";

        try(Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)){

            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getDescricao());
            ps.setDouble(3, produto.getPreco());
            ps.setInt(4, produto.getQuantidadeEstoque());
            ps.setInt(5, produto.getFornecedor().getIdFornecedor());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    int idGerado =rs.getInt(1);
                    produto.setIdProduto(idGerado);
                }
            }

            System.out.println("Produto cadastrado com sucesso!!! ");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar produto!!!\n");
            System.out.println(e);
        }
    }

    public Produto BuscarProdutoPorID(int id){
        String query = "select * from produto where id_produto = ?";

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query)){

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()){

                if (rs.next()){
                    Produto produto = new Produto();

                    produto.setIdProduto(rs.getInt("id_produto"));
                    produto.setDescricao(rs.getString("descricao"));
                    produto.setPreco(rs.getDouble("preco"));
                    produto.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));

                    int idFornecedor = rs.getInt("id_fornecedor");

                    FornecedorRepository fornecedorRepository = new FornecedorRepository();

                    Fornecedor fornecedor = fornecedorRepository.BuscarFornecedorPorID(idFornecedor);

                    produto.setFornecedor(fornecedor);

                    return produto;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public void RemoverProduto(int id){
        String query = "delete from produto where id_produto = ?";

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, id);

            int linhasAlteradas = ps.executeUpdate();

            if (linhasAlteradas > 0)
                System.out.println("Produto removido com sucesso!!! ");
            else
                throw new ProdutoNaoEncontradoException("Produto não encontrado!!! ");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Produto> ListarTodosProdutos(){
        String query = "select * from produto";

        List<Produto> listaProdutos = new ArrayList<>();

        try (Connection conn = conexaoBD.Conexaobd(); PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery();){

            while (rs.next()){

                Produto produto = new Produto();

                produto.setIdProduto(rs.getInt("id_produto"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));

                int idFornecedor = rs.getInt("id_fornecedor");

                FornecedorRepository fornecedorRepository = new FornecedorRepository();

                Fornecedor fornecedor = fornecedorRepository.BuscarFornecedorPorID(idFornecedor);

                produto.setFornecedor(fornecedor);

                listaProdutos.add(produto);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return listaProdutos;
    }

    public void AtualizarProduto(Produto produto, int opcao){
        String query;

        try (Connection conn = conexaoBD.Conexaobd()) {

            switch (opcao) {
                case 1:
                    query = "update produto set nome = ? where id_produto = ?";

                    break;

                case 2:
                    query = "update produto set descricao = ? where id_produto = ?";

                    break;

                case 3:
                    query = "update produto set preco = ? where id_produto = ?";

                    break;

                case 4:
                    query = "update produto set quantidade_estoque = ? where id_produto = ?";

                    break;

                default:
                    System.out.println("Opção inválida!!! ");

                    return;
            }

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                switch (opcao) {
                    case 1:
                        ps.setString(1, produto.getNome());

                        break;

                    case 2:
                        ps.setString(1, produto.getDescricao());

                        break;

                    case 3:
                        ps.setDouble(1, produto.getPreco());

                        break;

                    case 4:
                        ps.setInt(1, produto.getQuantidadeEstoque());

                        break;
                }

                ps.setInt(2, produto.getIdProduto());

                int linhasAlteradas = ps.executeUpdate();

                if (linhasAlteradas > 0)
                    System.out.println("Produto atualizado com sucesso!!! ");
                else
                    throw new ProdutoNaoEncontradoException("Produto não encontrado!!! ");

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
