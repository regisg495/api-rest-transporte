package com.br.transporteapi.controller.DTO;

import com.br.transporteapi.model.Parada;
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

@ApiModel(description = "Classe que representa uma parada")
@JsonTypeName("parada")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT ,use = JsonTypeInfo.Id.NAME)
public class ParadaDTO implements Serializable {

    @JsonIgnore
    private Long id;

    @ApiModelProperty(notes = "O nome da parada", example = "Parada da Rua República do Líbano")
    @JsonProperty("nomeDaParada")
    private String nome;

    @ApiModelProperty(notes = "A localização do veículo")
    @JsonProperty("coordenadas")
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY)
    private CoordenadasDTO coordenadasDTO;

    public ParadaDTO(Parada parada) {
        this.id = parada.getId();
        this.coordenadasDTO = new CoordenadasDTO(parada.getLatitude(), parada.getLongitude());
        this.nome = parada.getNome();
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public CoordenadasDTO getCoordenadasDTO() {
        return coordenadasDTO;
    }

    public static List<ParadaDTO> convert(List<Parada> paradas) {
        return paradas.stream().map(ParadaDTO::new).collect(Collectors.toList());
    }

    public static Page<ParadaDTO> convert(Page<Parada> paradas) {
        return paradas.map(ParadaDTO::new);
    }

}
