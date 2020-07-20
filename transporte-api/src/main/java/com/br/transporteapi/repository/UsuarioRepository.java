package com.br.transporteapi.repository;

import com.br.transporteapi.model.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository <Usuario, Long> {

    public Optional<Usuario> findByEmail(String email);

}
