package br.com.fiap.Domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int idPedido;
    private LocalDate dataPedido;
    private StatusPedido status;
    private double valorTotal;
    private Funcionario funcionario;
    private Cliente cliente;

    private List<ItemPedido> itens;
    private  Pagamento pagamento;


    public int getIdPedido() {
        return idPedido;
    }
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }
    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public StatusPedido getStatus() {
        return status;
    }
    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }
    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }
    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }


    public Pedido(){this.itens = new ArrayList<>();}

    public Pedido(LocalDate dataPedido, StatusPedido status, double valorTotal, Funcionario funcionario, Cliente cliente){
        this.dataPedido = dataPedido;
        this.status = status;
        this.valorTotal = valorTotal;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
    }


    @Override
    public String toString() {
        return "Id: " + idPedido + "\n" + "Data do pedido: " + dataPedido + "\n" + "Status: " + status + "\n" +
                "Valor Total: R$" + valorTotal + "\n" + "Funcionário: " + funcionario + "\n" + "Cliente: " + cliente;
    }
}
