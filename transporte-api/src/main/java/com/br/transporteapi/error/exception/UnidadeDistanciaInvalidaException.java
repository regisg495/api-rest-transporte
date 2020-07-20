package com.br.transporteapi.error.exception;

public class UnidadeDistanciaInvalidaException extends IllegalArgumentException {

    public UnidadeDistanciaInvalidaException(String s) {
        super(s);
    }

    public UnidadeDistanciaInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }
}
