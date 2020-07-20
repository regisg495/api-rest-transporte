package com.br.transporteapi.error.exception;

import javax.persistence.PersistenceException;

public class ErroAoCadastrarException extends PersistenceException {

    public ErroAoCadastrarException(String message) {
        super(message);
    }

    public ErroAoCadastrarException(String message, Throwable cause) {
        super(message, cause);
    }
}
