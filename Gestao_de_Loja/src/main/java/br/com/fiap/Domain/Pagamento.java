package br.com.fiap.Domain;

import java.time.LocalDate;

public class Pagamento {
    private int idPagamento;
    private TipoPagamento tipoPagamento;
    private double valor;
    private LocalDate dataPagamento;
    private StatusPagamento status;
    private Pedido pedido;


    public int getIdPagamento() {
        return idPagamento;
    }
    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }
    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }
    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public StatusPagamento getStatus() {
        return status;
    }
    public void setStatus(StatusPagamento status) {
        this.status = status;
    }

    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }


    public Pagamento(){}

    public Pagamento(TipoPagamento tipoPagamento, double valor, LocalDate dataPagamento, StatusPagamento status, Pedido pedido){
        this.tipoPagamento = tipoPagamento;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.status = status;
        this.pedido = pedido;
    }


    @Override
    public String toString() {
        return "Id: " + idPagamento + "\n" + "Tipo de pagamento: " + tipoPagamento + "\n" + "Valor: R$" + valor
                + "\n" + "Data de pagamento: " + dataPagamento + "\n" + "Status: " + status + "\n" + "Pedido: " + pedido;
    }
}
