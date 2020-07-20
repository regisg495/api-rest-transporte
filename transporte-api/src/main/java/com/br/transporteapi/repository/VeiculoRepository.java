package com.br.transporteapi.repository;

import com.br.transporteapi.model.Veiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VeiculoRepository extends CrudRepository<Veiculo, Long> {

    @Override
    public List<Veiculo> findAll();

    public Page<Veiculo> findAll(Pageable pageable);

    public Page<Veiculo> findAllByLinha_Id(Pageable pageable, Long idLinha);

}
