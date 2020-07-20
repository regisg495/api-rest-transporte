package com.br.transporteapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "posicao_veiculo")
public class PosicaoVeiculo {

    @Id
    @Column(name = "veiculo_id")
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    public PosicaoVeiculo() {

    }

    public PosicaoVeiculo(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public PosicaoVeiculo(Long id, Double latitude, Double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public PosicaoVeiculo(Veiculo veiculo, Double latitude, Double longitude) {
        this.veiculo = veiculo;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public PosicaoVeiculo(Long id, Veiculo veiculo, Double latitude, Double longitude) {
        this.id = id;
        this.veiculo = veiculo;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

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
