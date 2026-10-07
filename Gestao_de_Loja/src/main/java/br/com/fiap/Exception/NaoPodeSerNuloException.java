package br.com.fiap.Exception;

public class NaoPodeSerNuloException extends RuntimeException {
    public NaoPodeSerNuloException(String mensagem) {
        super(mensagem);
    }
}
