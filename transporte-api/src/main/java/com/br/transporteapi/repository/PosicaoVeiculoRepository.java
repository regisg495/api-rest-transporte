package com.br.transporteapi.repository;

import com.br.transporteapi.model.PosicaoVeiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PosicaoVeiculoRepository extends CrudRepository<PosicaoVeiculo, Long> {

    @Override
    public List<PosicaoVeiculo> findAll();

    public Page<PosicaoVeiculo> findAll(Pageable pageable);

    public Optional<PosicaoVeiculo> findByLatitudeAndLongitude(Double latitude, Double longitude);

}
