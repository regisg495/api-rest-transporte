package com.br.transporteapi.controller.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Classe que representa as paradas próximas")
@JsonTypeName("paradaDistancia")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public interface ParadaDistanciaDTO {

    @ApiModelProperty(notes = "O nome da parada", example = "Parada da Rua República do Líbano")
    @JsonProperty("nomeDaParada")
    public String getNome();

    @ApiModelProperty(notes = "A latitude da parada")
    @JsonProperty("latitude")
    public Double getLatitude();

    @ApiModelProperty(notes = "A longitude da parada")
    @JsonProperty("longitude")
    public Double getLongitude();

    @ApiModelProperty(notes = "A sua distância relação a parada")
    @JsonProperty("distancia")
    public String getDistancia();

}
