package com.br.transporteapi.error.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class PosicaoVeiculoCadastradaException extends DataIntegrityViolationException {

    public PosicaoVeiculoCadastradaException(String message) {
        super(message);
    }

    public PosicaoVeiculoCadastradaException(String message, Throwable cause) {
        super(message, cause);
    }
}
