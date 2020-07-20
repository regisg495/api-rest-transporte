package com.br.transporteapi.repository;

import com.br.transporteapi.model.Linha;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LinhaRepository extends CrudRepository<Linha, Long> {

    @Override
    public List<Linha> findAll();

    public Page<Linha> findAll(Pageable pageable);

    public Optional<Linha> findByNome(String nome);

    public Boolean existsByNome(String nome);

    public Page<Linha> findAllByParadasId(Pageable pageable, Long idParada);


}
