package br.com.fiap.Service;

import br.com.fiap.Domain.Fornecedor;
import br.com.fiap.Domain.Produto;
import br.com.fiap.Exception.FornecedorNaoEncontradoException;
import br.com.fiap.Exception.IDNaoPodeSerMenorQueZeroException;
import br.com.fiap.Exception.NaoPodeSerNuloException;
import br.com.fiap.Exception.ProdutoNaoEncontradoException;
import br.com.fiap.Repository.FornecedorRepository;
import br.com.fiap.Repository.ProdutoRepository;

import java.util.List;

public class ProdutoService {
    private ProdutoRepository produtoRepository;
    private FornecedorRepository fornecedorRepository;

    public ProdutoService(){produtoRepository = new ProdutoRepository();
    fornecedorRepository = new FornecedorRepository();}


    public void InserirProduto(Produto produto){
        if (produto == null)
            throw new NaoPodeSerNuloException("Produto não pode ser nulo!!! ");

        if (produto.getNome() == null || produto.getNome().trim().isEmpty())
            throw new IllegalArgumentException("Nome do produto é obrigatório!!! ");

        if (produto.getDescricao() == null || produto.getDescricao().trim().isEmpty())
            throw new IllegalArgumentException("Descrição do produto é obrigatório!!! ");

        if (produto.getPreco() <= 0)
            throw new NaoPodeSerNuloException("Preço do produto não pode ser nulo ou menor a zero!!! ");

        if (produto.getQuantidadeEstoque() <= 0)
            throw new NaoPodeSerNuloException("Quantidade do produto não pode ser nulo ou menor a zero!!! ");

        if (produto.getFornecedor() == null)
            throw new NaoPodeSerNuloException("Fornecedor do produto não pode ser nulo!!! ");

        if (produto.getFornecedor().getIdFornecedor() <= 0)
            throw new IDNaoPodeSerMenorQueZeroException("ID do fornecedor deve ser maior que zero!!! ");

        Fornecedor fornecedor = fornecedorRepository.BuscarFornecedorPorID(produto.getFornecedor().getIdFornecedor());

        if (fornecedor == null)
            throw new FornecedorNaoEncontradoException("Fornecedor não encontrado!!! ");

        produtoRepository.InserirProduto(produto);
    }

    public Produto BuscarProdutoPorID(int id){
        if (id <= 0)
            throw new IDNaoPodeSerMenorQueZeroException("ID tem que ser maior que zero!!! ");

        Produto produto = produtoRepository.BuscarProdutoPorID(id);

        if (produto == null)
            throw new ProdutoNaoEncontradoException("Produto não encontrado!!! ");

        return produto;
    }

    public List<Produto> ListarTodosProdutos(){
        return  produtoRepository.ListarTodosProdutos();
    }

    public void RemoverProduto(int id){
        if (id <= 0)
            throw new IDNaoPodeSerMenorQueZeroException("ID tem que ser maior que zero!!! ");

        Produto produto = produtoRepository.BuscarProdutoPorID(id);

        if (produto == null)
            throw new ProdutoNaoEncontradoException("Produto não encontrado!!! ");

        produtoRepository.RemoverProduto(id);
    }

    public void AtualizarProduto(Produto produto, int opcao){
        if (produto == null)
            throw new NaoPodeSerNuloException("Produto não poder ser nulo!!! ");

        if (produto.getIdProduto() <= 0)
            throw new IDNaoPodeSerMenorQueZeroException("ID tem que ser maior que zero!!! ");

        Produto produtoExiste = produtoRepository.BuscarProdutoPorID(produto.getIdProduto());

        if (produtoExiste == null)
            throw new FornecedorNaoEncontradoException("Produto não encontrado!!! ");

        switch (opcao){
            case 1:
                if (produto.getNome() == null || produto.getNome().trim().isEmpty())
                    throw new IllegalArgumentException("Nome do produto é obrigatório!!! ");

                break;

            case 2:
                if (produto.getDescricao() == null || produto.getDescricao().trim().isEmpty())
                    throw new IllegalArgumentException("Descrição do produto é obrigatório!!! ");

                break;

            case 3:
                if (produto.getPreco() <= 0)
                    throw new NaoPodeSerNuloException("Preço do produto não pode ser nulo ou menor a zero!!! ");

                break;

            case 4:
                if (produto.getQuantidadeEstoque() <= 0)
                    throw new NaoPodeSerNuloException("Quantidade do produto não pode ser nulo ou menor a zero!!! ");

                break;

            default:
                throw new IllegalArgumentException("Opção inválida!!! ");
        }

        produtoRepository.AtualizarProduto(produto, opcao);
    }
}
