package com.br.transporteapi.controller.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "Classe que representa um token de acesso")
@JsonTypeName("token")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class TokenDTO implements Serializable {

    @ApiModelProperty(notes = "O token de acesso")
    @JsonProperty("acessKey")
    private final String token;

    @ApiModelProperty(notes = "O tipo de autenticação")
    @JsonProperty("authenticationType")
    private final String authencationType;

    public TokenDTO(String token, String authencationType) {
        this.token = token;
        this.authencationType = authencationType;
    }

    public String getToken() {
        return token;
    }

    public String getAuthencationType() {
        return authencationType;
    }

}
