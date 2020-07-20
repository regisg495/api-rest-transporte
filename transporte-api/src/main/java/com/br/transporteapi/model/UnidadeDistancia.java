package com.br.transporteapi.model;

import com.br.transporteapi.error.exception.UnidadeDistanciaInvalidaException;

public enum UnidadeDistancia {

    Metro("m"),
    Quilometro("km"),
    Milha("mi");

    private final String unidade;


    UnidadeDistancia(String unidade) {
        this.unidade = unidade;
    }

    public String getUnidade() {
        return unidade;
    }

    public static UnidadeDistancia fromUnidade(String unidade) {
        for (UnidadeDistancia unidadeDistancia : UnidadeDistancia.values()) {
            if (unidadeDistancia.getUnidade().equals(unidade)) return unidadeDistancia;
        }
        throw new UnidadeDistanciaInvalidaException("A unidade de distância " + unidade + " é inválida ou não é permitida");
    }

}
