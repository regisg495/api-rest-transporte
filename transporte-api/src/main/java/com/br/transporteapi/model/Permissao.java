package com.br.transporteapi.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "permissoes")
public class Permissao implements GrantedAuthority {

    @Id
    @Column(name = "permissao_nome")
    private String nome;

    @ManyToMany(mappedBy = "permissoes", fetch = FetchType.LAZY)
    private List<Usuario> usuarios = new ArrayList<>();

    public Permissao() {
    }

    public Permissao(String nome) {
        this.nome = nome;
    }

    public Permissao(String nome, List<Usuario> usuarios) {
        this.nome = nome;
        this.usuarios = usuarios;
    }

    @Override
    public String getAuthority() {
        return this.nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
