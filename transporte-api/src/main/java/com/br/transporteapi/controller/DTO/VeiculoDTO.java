package com.br.transporteapi.controller.DTO;

import com.br.transporteapi.model.Veiculo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel(description = "Classe que representa um veículo")
@JsonTypeName("veiculo")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class VeiculoDTO implements Serializable {

    @JsonIgnore
    private Long id;

    @ApiModelProperty(notes = "O nome do veículo", example = "V01-X")
    @JsonProperty("nomeDoVeiculo")
    private String nome;

    @ApiModelProperty(notes = "O modelo do veículo", example = "Porsche Carrera GT")
    @JsonProperty("modelo")
    private String modelo;

    public VeiculoDTO(Veiculo veiculo) {
        this.id = veiculo.getId();
        this.modelo = veiculo.getModelo();
        this.nome = veiculo.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getModelo() {
        return modelo;
    }

    public static List<VeiculoDTO> convert(List<Veiculo> veiculos) {
        return veiculos.stream().map(VeiculoDTO::new).collect(Collectors.toList());
    }

    public static Page<VeiculoDTO> convert(Page<Veiculo> veiculos) {
        return veiculos.map(VeiculoDTO::new);
    }
}
