package br.com.fiap.Domain;

public class Fornecedor {
    private int idFornecedor;
    private String nome;
    private String cnpj;
    private String email;
    private String telefone;


    public int getIdFornecedor() {
        return idFornecedor;
    }
    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public Fornecedor(){}

    public Fornecedor(String nome, String cnpj, String email, String telefone){
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
        this.telefone = telefone;
    }


    @Override
    public String toString() {
        return "Id: " + idFornecedor + "\n" + "Nome: " + nome + "\n" + "Cnpj: " + cnpj + "\n" + "Email: " + email
                + "\n" + "Telefone: " + telefone;
    }
}
