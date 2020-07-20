package com.br.transporteapi.repository;

import com.br.transporteapi.controller.DTO.ParadaDistanciaDTO;
import com.br.transporteapi.model.Parada;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ParadaRepository extends CrudRepository<Parada, Long> {

    @Query(value = "SELECT p.nome AS nome, p.latitude AS latitude, p.longitude AS longitude, " +
            "CONCAT(TRUNCATE(111.111 * DEGREES(ACOS(LEAST(COS(RADIANS(:latitude)) * " +
            "COS(RADIANS(p.latitude)) * " +
            "COS(RADIANS(:longitude - p.longitude)) + " +
            "SIN(RADIANS(:latitude)) * " +
            "SIN(RADIANS(p.latitude)), 1.0))), 3), 'km')  AS distancia " +
            "FROM paradas p " +
            "WHERE 111.111 * DEGREES(ACOS(LEAST(COS(RADIANS(:latitude)) *" +
            "COS(RADIANS(p.latitude)) * " +
            "COS(RADIANS(:longitude - p.longitude)) + " +
            "SIN(RADIANS(:latitude)) * " +
            "SIN(RADIANS(p.latitude)), 1.0))) < :areaCobertaEmKm " +
            "ORDER BY distancia", nativeQuery = true)
    List<ParadaDistanciaDTO> findAllParadasProximasInKm(@Param("latitude") Double latitude,
                                                        @Param("longitude") Double longitude,
                                                        @Param("areaCobertaEmKm") Double areaCobertaEmKm);

    @Query(value = "SELECT p.nome AS nome, p.latitude AS latitude, p.longitude AS longitude, " +
            "CONCAT(TRUNCATE(111.111 * DEGREES(ACOS(LEAST(COS(RADIANS(:latitude)) * " +
            "COS(RADIANS(p.latitude)) * " +
            "COS(RADIANS(:longitude - p.longitude)) + " +
            "SIN(RADIANS(:latitude)) * " +
            "SIN(RADIANS(p.latitude)), 1.0))) * 1000, 2), 'm') AS distancia " +
            "FROM paradas p " +
            "WHERE 111.111 * DEGREES(ACOS(LEAST(COS(RADIANS(:latitude)) *" +
            "COS(RADIANS(p.latitude)) * " +
            "COS(RADIANS(:longitude - p.longitude)) + " +
            "SIN(RADIANS(:latitude)) * " +
            "SIN(RADIANS(p.latitude)), 1.0))) * 1000 < :areaCobertaEmMetros", nativeQuery = true)
    List<ParadaDistanciaDTO> findAllParadasProximasInMeters(@Param("latitude") Double latitude,
                                                     @Param("longitude") Double longitude,
                                                     @Param("areaCobertaEmMetros") Double areaCobertaEmMetros);

    @Query(value = "SELECT p.nome AS nome, p.latitude AS latitude, p.longitude AS longitude, " +
            "CONCAT(TRUNCATE(111.111 * DEGREES(ACOS(LEAST(COS(RADIANS(:latitude)) * " +
            "COS(RADIANS(p.latitude)) * " +
            "COS(RADIANS(:longitude - p.longitude)) + " +
            "SIN(RADIANS(:latitude)) * " +
            "SIN(RADIANS(p.latitude)), 1.0))) / 1.609344, 3), 'mi') AS distancia " +
            "FROM paradas p " +
            "WHERE 111.111 * DEGREES(ACOS(LEAST(COS(RADIANS(:latitude)) *" +
            "COS(RADIANS(p.latitude)) * " +
            "COS(RADIANS(:longitude - p.longitude)) + " +
            "SIN(RADIANS(:latitude)) * " +
            "SIN(RADIANS(p.latitude)), 1.0))) / 1.609344 < :areaCobertaEmMilhas", nativeQuery = true)
    List<ParadaDistanciaDTO> findAllParadasProximasInMiles(@Param("latitude") Double latitude,
                                                     @Param("longitude") Double longitude,
                                                     @Param("areaCobertaEmMilhas") Double areaCobertaEmMilhas);

    @Override
    public List<Parada> findAll();

    public Page<Parada> findAll(Pageable pageable);

    public Optional<Parada> findByNome(String nome);

    public List<Parada> findByLatitudeAndLongitude(Double latitude, Double longitude);

}
