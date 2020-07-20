package com.br.transporteapi.service;

import com.br.transporteapi.model.Usuario;
import com.br.transporteapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findByEmail(String email) {
        Optional<Usuario> usuario = this.usuarioRepository.findByEmail(email);

        if (!usuario.isPresent())
            throw new UsernameNotFoundException("Usuário não encontrado com o email " + email);

        return usuario.get();

    }

}
