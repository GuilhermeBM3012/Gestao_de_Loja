package br.com.fiap.Domain;

import java.time.LocalDate;

public class Funcionario {
    private int idFuncionario;
    private String nome;
    private String cpf;
    private Cargo cargo;
    private double salario;
    private LocalDate dataContratacao;


    public int getIdFuncionario() {
        return idFuncionario;
    }
    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
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

    public Cargo getCargo() {
        return cargo;
    }
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }
    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }


    public Funcionario(){}

    public Funcionario(String nome, String cpf, Cargo cargo, double salario, LocalDate dataContratacao){
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
        this.salario = salario;
        this.dataContratacao = dataContratacao;
    }


    @Override
    public String toString() {
        return "Id: " + idFuncionario + "\n" + "Nome: " + nome + "\n" + "Cpf: " + cpf + "\n" + "Cargo: " + cargo
                + "\n" + "Salário: R$" + salario + "\n" + "Data de contratação: " + dataContratacao;
    }
}
