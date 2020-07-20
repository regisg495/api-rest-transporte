package com.br.transporteapi.controller.form;

import com.br.transporteapi.model.Linha;
import com.br.transporteapi.model.Veiculo;
import com.br.transporteapi.service.LinhaService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "Classe que representa um formulário para um veículo")
public class VeiculoForm {

    @ApiModelProperty(notes = "O nome do veículo", required = true, example = "V01-X")
    @Size(max = 100, message = "O nome escolhido ultrapassa o limite de caracteres")
    @NotNull(message = "O campo nome é necessário")
    @NotEmpty(message = "O campo nome não pode estar vazio")
    private String nome;

    @ApiModelProperty(notes = "O modelo do veículo", required = true, example = "Porsche Carrera GT")
    @Size(max = 100, message = "O modelo escolhido ultrapassa o limite de caracteres")
    private String modelo;

    @ApiModelProperty(notes = "O ID da linha a que o veículo pertence", required = true, example = "1")
    @Min(value = 1, message = "O valor do campo é invalido")
    @NotNull(message = "O campo linha é necessário")
    private Long idLinha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Long getIdLinha() {
        return idLinha;
    }

    public void setIdLinha(Long idLinha) {
        this.idLinha = idLinha;
    }

    public Veiculo convert(LinhaService linhaService) {
        Linha linha = linhaService.findById(this.idLinha);
        return new Veiculo(this.nome, this.modelo, linha);
    }



}
