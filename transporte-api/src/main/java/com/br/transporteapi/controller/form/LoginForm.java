package com.br.transporteapi.controller.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "Classe que representa um formulário para login")
public class LoginForm {

    @ApiModelProperty(notes = "O email de login", required = true, example = "aiko", dataType = "string")
    @Size(max = 60, message = "O campo email ultrapassa o limite de caracteres")
    @NotNull(message = "O campo email é necessário")
    @NotEmpty(message = "O campo email é necessário")
    private String email;

    @ApiModelProperty(notes = "A senha para o login", required = true, example = "aiko", dataType = "string")
    @NotNull(message = "O campo senha é necessário")
    @NotEmpty(message = "O campo senha é necessário")
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken convert() {
        return new UsernamePasswordAuthenticationToken(this.email, this.senha);
    }


}
