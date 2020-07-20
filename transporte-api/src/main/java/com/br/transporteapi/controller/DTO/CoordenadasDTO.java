package com.br.transporteapi.controller.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "Classe que representa a localização")
@JsonTypeName("coordenadas")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class CoordenadasDTO implements Serializable {

    @ApiModelProperty(notes = "A latitude", example = "-23.00")
    @JsonProperty("latitude")
    private Double latitude;

    @ApiModelProperty(notes = "A longitude", example = "-46.00")
    @JsonProperty("longitude")
    private Double longitude;

    public CoordenadasDTO(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
