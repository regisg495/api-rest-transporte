package com.br.transporteapi.error.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class PosicaoParadaCadastradaException extends DataIntegrityViolationException {

    public PosicaoParadaCadastradaException(String msg) {
        super(msg);
    }

    public PosicaoParadaCadastradaException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
