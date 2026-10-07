package br.com.fiap.Exception;

public class IDNaoPodeSerMenorQueZeroException extends RuntimeException {
    public IDNaoPodeSerMenorQueZeroException(String mensagem){
        super(mensagem);
    }
}
