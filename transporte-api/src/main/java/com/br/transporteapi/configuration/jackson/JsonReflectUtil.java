package com.br.transporteapi.configuration.jackson;

import com.br.transporteapi.controller.DTO.PosicaoVeiculoDTO;

import java.util.Optional;

public class JsonReflectUtil {

    private Class clazz;

    public JsonReflectUtil(Class clazz) {
        this.clazz = clazz;
    }

    public Optional<String> convertToModelName() {
        if(this.clazz == null) return Optional.empty();
        else if(this.clazz.equals(PosicaoVeiculoDTO.class)) return Optional.of("posicoesDosVeiculos");
        else return Optional.of(this.clazz.getSimpleName().replace("DTO", "s").toLowerCase());
    }



}
