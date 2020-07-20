package com.br.transporteapi.error.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class NomeDaParadaJaExisteException extends DataIntegrityViolationException {

    public NomeDaParadaJaExisteException(String msg) {
        super(msg);
    }

    public NomeDaParadaJaExisteException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
