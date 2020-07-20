package com.br.transporteapi.controller.DTO;

import com.br.transporteapi.model.PosicaoVeiculo;
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

@ApiModel(description = "Classe que representa a posição de um veículo")
@JsonTypeName("posicaoDoVeiculo")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class PosicaoVeiculoDTO implements Serializable {

    @JsonIgnore
    private Long id;

    @ApiModelProperty(notes = "O veículo que está nessa posição")
    @JsonProperty("veiculo")
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY)
    private VeiculoDTO veiculoDTO;

    @ApiModelProperty(notes = "A localização")
    @JsonProperty("coordenadas")
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY)
    private CoordenadasDTO coordenadasDTO;

    public PosicaoVeiculoDTO(PosicaoVeiculo posicaoVeiculo) {
        this.id = posicaoVeiculo.getVeiculo().getId();
        this.coordenadasDTO = new CoordenadasDTO(posicaoVeiculo.getLatitude(), posicaoVeiculo.getLongitude());
        this.veiculoDTO = new VeiculoDTO(posicaoVeiculo.getVeiculo());
    }

    public Long getId() {
        return id;
    }

    public VeiculoDTO getVeiculoDTO() {
        return veiculoDTO;
    }

    public CoordenadasDTO getCoordenadasDTO() {
        return coordenadasDTO;
    }

    public static List<PosicaoVeiculoDTO> convert(List<PosicaoVeiculo> posicaoVeiculos) {
        return posicaoVeiculos.stream().map(PosicaoVeiculoDTO::new).collect(Collectors.toList());
    }

    public static Page<PosicaoVeiculoDTO> convert(Page<PosicaoVeiculo> posicaoVeiculos) {
        return posicaoVeiculos.map(PosicaoVeiculoDTO::new);
    }
}
