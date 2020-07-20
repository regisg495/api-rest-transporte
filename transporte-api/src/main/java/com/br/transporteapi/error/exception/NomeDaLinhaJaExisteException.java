package com.br.transporteapi.error.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class NomeDaLinhaJaExisteException extends DataIntegrityViolationException {

    public NomeDaLinhaJaExisteException(String msg) {
        super(msg);
    }

    public NomeDaLinhaJaExisteException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
