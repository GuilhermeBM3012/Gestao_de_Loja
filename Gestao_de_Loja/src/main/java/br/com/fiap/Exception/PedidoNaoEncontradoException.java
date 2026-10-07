package br.com.fiap.Exception;

public class PedidoNaoEncontradoException extends RuntimeException {
    public PedidoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
