package com.br.transporteapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "linhas")
public class Linha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "linha_id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "parada_linha",
            joinColumns = @JoinColumn(name = "linha_id"),
            inverseJoinColumns = @JoinColumn(name = "parada_id"))
    private List<Parada> paradas = new ArrayList<>();

    @OneToMany(mappedBy = "linha", fetch = FetchType.LAZY)
    private List<Veiculo> veiculos = new ArrayList<>();

    public Linha() {

    }

    public Linha(String nome) {
        this.nome = nome;
    }

    public Linha(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Linha(String nome, List<Parada> paradas) {
        this.nome = nome;
        this.paradas = paradas;
    }

    public Linha(Long id, String nome, List<Parada> paradas) {
        this.id = id;
        this.nome = nome;
        this.paradas = paradas;
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

    public List<Parada> getParadas() {
        return paradas;
    }

    public void setParadas(List<Parada> paradas) {
        this.paradas = paradas;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }
}
