package com.br.transporteapi.controller.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@ApiModel(description = "Classe que representa um formulário para uma localização")
public class CoordenadasForm {

    @ApiModelProperty(notes = "A latitude", required = true, example = "-23.00")
    @NotNull(message = "O campo latitude é necessário")
    @Min(value = -90, message = "A latitude deve ter no mínimo -90º")
    @Max(value = 90, message = "A latitude deve ter no máximo 90º")
    private Double latitude;

    @ApiModelProperty(notes = "A longitude", required = true, example = "-46.00")
    @NotNull(message = "O campo longitude é necessário")
    @Min(value = -180, message = "A longitude deve ter no mínimo -180º")
    @Max(value = 180, message = "A longitude deve ter no mínimo 180º")
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

}
