package br.com.fiap.Domain;

import java.time.LocalDate;

public class Cliente {
    private int idCliente;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private LocalDate dataCadastro;


    public int getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }


    public Cliente(){}

    public Cliente(String nome, String cpf, String email, String telefone, LocalDate dataCadastro){
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.dataCadastro = dataCadastro;
    }


    @Override
    public String toString() {
        return "Id: " + idCliente + "\n" + "Nome: " + nome + "\n" + "Cpf: " + cpf + "\n" + "Email: " + email
                + "\n" + "Telefone: " + telefone + "\n" + "Data de cadastro: " + dataCadastro;
    }
}
