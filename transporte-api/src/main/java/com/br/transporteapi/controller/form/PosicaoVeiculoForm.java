package com.br.transporteapi.controller.form;

import com.br.transporteapi.model.PosicaoVeiculo;
import com.br.transporteapi.model.Veiculo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

@ApiModel(description = "Classe que representa um formulário para a posição de um veículo")
public class PosicaoVeiculoForm {

    @ApiModelProperty(notes = "O ID do veículo", example = "1")
    @JsonIgnore
    private Long idVeiculo;

    @JsonIgnore
    private Veiculo veiculo;

    @Valid
    private CoordenadasForm coordenadasForm;

    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public Long getIdVeiculo() {
        return idVeiculo;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public CoordenadasForm getCoordenadasForm() {
        return coordenadasForm;
    }

    public void setCoordenadasForm(CoordenadasForm coordenadasForm) {
        this.coordenadasForm = coordenadasForm;
    }

    public PosicaoVeiculo convert() {
        return new PosicaoVeiculo(this.idVeiculo, this.veiculo, this.coordenadasForm.getLatitude(), this.coordenadasForm.getLongitude());
    }
}
