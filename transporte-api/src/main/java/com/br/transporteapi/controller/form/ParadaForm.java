package com.br.transporteapi.controller.form;

import com.br.transporteapi.model.Parada;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "Classe que representa um formulário para uma parada")
public class ParadaForm {

    @ApiModelProperty(notes = "O nome da parada", required = true, example = "Parada da Rua República do Líbano")
    @Size(max = 150, message = "O nome escolhido ultrapassa o limite de caracteres")
    @NotNull(message = "O campo nome é necessário")
    @NotEmpty(message = "O campo nome não pode estar vazio")
    private String nome;

    @Valid
    private CoordenadasForm coordenadasForm;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CoordenadasForm getCoordenadasForm() {
        return coordenadasForm;
    }

    public void setCoordenadasForm(CoordenadasForm coordenadasForm) {
        this.coordenadasForm = coordenadasForm;
    }

    public Parada convert() {
        return new Parada(this.nome, this.coordenadasForm.getLatitude(), this.coordenadasForm.getLongitude());
    }
}
