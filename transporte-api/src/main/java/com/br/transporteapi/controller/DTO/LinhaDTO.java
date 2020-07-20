package com.br.transporteapi.controller.DTO;

import com.br.transporteapi.model.Linha;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel(description = "Classe que representa uma linha")
@JsonTypeName("linha")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class LinhaDTO implements Serializable {

    @ApiModelProperty(notes = "O nome da linha", example = "IXX-01")
    @JsonProperty("nomeDaLinha")
    private String nome;

    public LinhaDTO(Linha linha) {
        this.nome = linha.getNome();
    }

    public String getNome() {
        return nome;
    }

    public static List<LinhaDTO> convert(List<Linha> linhas) {
        return linhas.stream().map(LinhaDTO::new).collect(Collectors.toList());
    }

    public static Page<LinhaDTO> convert(Page<Linha> linhas) {
        return linhas.map(LinhaDTO::new);
    }

}
