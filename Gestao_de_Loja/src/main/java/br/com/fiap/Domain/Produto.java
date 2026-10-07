package br.com.fiap.Domain;

public class Produto {
    private int idProduto;
    private String nome;
    private String descricao;
    private double preco;
    private int quantidadeEstoque;
    private Fornecedor fornecedor;


    public int getIdProduto() {
        return idProduto;
    }
    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }
    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }


    public Produto(){}

    public Produto(String nome, String descricao, double preco, int quantidadeEstoque, Fornecedor fornecedor){
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.fornecedor = fornecedor;
    }


    @Override
    public String toString() {
        return "Id: " + idProduto + "\n" + "Nome: " + nome + "\n" + "Descrição: " + descricao + "\n" + "Preço: R$" + preco
                + "\n" + "Quantidade em estoque: " + quantidadeEstoque + "\n" + "Fornecedor: " + fornecedor;
    }
}
