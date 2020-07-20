package com.br.transporteapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "veiculo_id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "modelo")
    private String modelo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "linha_id")
    private Linha linha;

    @OneToOne(mappedBy = "veiculo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PosicaoVeiculo posicaoVeiculo;

    public Veiculo() {

    }

    public Veiculo(String nome, String modelo) {
        this.nome = nome;
        this.modelo = modelo;
    }

    public Veiculo(String nome, String modelo, Linha linha) {
        this.nome = nome;
        this.modelo = modelo;
        this.linha = linha;
    }

    public Veiculo(String nome, String modelo, Linha linha, PosicaoVeiculo posicaoVeiculo) {
        this.nome = nome;
        this.modelo = modelo;
        this.linha = linha;
        this.posicaoVeiculo = posicaoVeiculo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    public PosicaoVeiculo getPosicaoVeiculo() {
        return posicaoVeiculo;
    }

    public void setPosicaoVeiculo(PosicaoVeiculo posicaoVeiculo) {
        this.posicaoVeiculo = posicaoVeiculo;
    }
}
