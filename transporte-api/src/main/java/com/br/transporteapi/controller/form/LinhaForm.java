package com.br.transporteapi.controller.form;

import com.br.transporteapi.model.Linha;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "Classe que representa um formulário para uma linha")
public class LinhaForm {

    @ApiModelProperty(notes = "O nome da linha", required = true, example = "IXX-01", dataType = "string")
    @Size(max = 150, message = "O nome escolhido ultrapassa o limite de caracteres")
    @NotNull(message = "O campo nome é necessário")
    @NotEmpty(message = "O campo nome não pode estar vazio")
    private String nome;

    @ApiModelProperty(notes = "O ID das paradas em que a linha passa", example = "[1, 2, 3]", dataType = "long")
    private List<Long> idParadas = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Long> getIdParadas() {
        return idParadas;
    }

    public Linha convert() {
        return new Linha(this.nome);
    }

}
