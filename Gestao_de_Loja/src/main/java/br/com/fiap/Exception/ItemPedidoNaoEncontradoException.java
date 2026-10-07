package br.com.fiap.Exception;

public class ItemPedidoNaoEncontradoException extends RuntimeException {
    public ItemPedidoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
