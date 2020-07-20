package com.br.transporteapi.error.exception;

import javax.persistence.EntityNotFoundException;

public class ReferenciaNaoEncontradaException extends EntityNotFoundException {

    public ReferenciaNaoEncontradaException() {
    }

    public ReferenciaNaoEncontradaException(String message) {
        super(message);
    }
}
