package com.br.transporteapi.controller;

import com.br.transporteapi.configuration.swagger.ApiPageableDefaultFields;
import com.br.transporteapi.controller.DTO.ParadaDTO;
import com.br.transporteapi.controller.DTO.ParadaDistanciaDTO;
import com.br.transporteapi.controller.form.ParadaForm;
import com.br.transporteapi.model.Parada;
import com.br.transporteapi.service.ParadaService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;

import java.net.URI;

@RestController
public class ParadaController {

    @Autowired
    private ParadaService paradaService;

    @ApiOperation(value = "Retorna uma lista com as paradas perto de um ponto específico (através da latitude e longitude)", response = ParadaDistanciaDTO.class)
    @GetMapping(value = "/paradas/proximas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllParadasNear(@ApiParam(name = "unidadeDeMedida", value = "A unidade de medida da área", required = true, example = "km")
                                             @RequestParam(name = "unidadeDeMedida") String unidadeDeMedida,
                                             @ApiParam(name = "latitude", value = "A latitude que você deseja", required = true, example = "-23.556346")
                                             @RequestParam(name = "latitude") Double latitude,
                                             @ApiParam(name = "longitude", value = "A longitude que você deseja", required = true, example = "-46.639942")
                                             @RequestParam(name = "longitude") Double longitude,
                                             @ApiParam(name = "area", value = "O raio da área em que deseja procurar (de acordo com a sua medida). Exemplo: unidade: km, raio: 1. area = 1 km", required = true, example = "1")
                                             @RequestParam(name = "area") Double area) {
        return ResponseEntity.ok(
                this.paradaService.findAllParadasProximas(latitude, longitude, area, unidadeDeMedida));
    }

    @ApiOperation(value = "Retorna todas as paradas existentes", response = ParadaDTO.class)
    @GetMapping(value = "/paradas/todas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllParadas() {
        return ResponseEntity.ok(ParadaDTO.convert(this.paradaService.findAll()));
    }

    @ApiOperation(value = "Retorna uma lista paginada de paradas", response = ParadaDTO.class)
    @GetMapping(value = "/paradas", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiPageableDefaultFields
    @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
            value = "Específica o campo pelo qual você deseja ordenar os registros",
            defaultValue = "nome")
    public ResponseEntity<?> findAllParadasPage(@PageableDefault(sort = "nome", direction = Sort.Direction.ASC, page = 0, size = 10)
                                         @ApiIgnore Pageable pageable) {
        return ResponseEntity.ok(ParadaDTO.convert(this.paradaService.findAll(pageable)));
    }

    @ApiOperation(value = "Retorna uma parada específica", response = ParadaDTO.class)
    @GetMapping(value = "/parada/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findParada(@ApiParam(name = "id", type = "Long",
            value = "ID da parada", example = "1", required = true) @PathVariable("id") Long id) {
        return ResponseEntity.ok(new ParadaDTO(this.paradaService.findById(id)));
    }

    @ApiOperation(value = "Deleta uma parada específica", response = ResponseEntity.class)
    @DeleteMapping("/parada/{id}/deletar")
    public ResponseEntity<?> deleteParada(@ApiParam(name = "id", type = "Long",
            value = "ID da parada", example = "1", required = true) @PathVariable("id") Long id) {
        this.paradaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Cadastra uma parada", response = ParadaDTO.class)
    @PostMapping(value = "/parada/cadastrar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveParada(@RequestBody @Validated ParadaForm paradaForm, UriComponentsBuilder uriComponentsBuilder) {
        Parada parada = this.paradaService.save(paradaForm);

        URI uri = uriComponentsBuilder.path("/parada/{id}").buildAndExpand(parada.getId()).toUri();

        return ResponseEntity.created(uri).body(new ParadaDTO(parada));
    }

    @ApiOperation(value = "Edita os campos de uma parada específica", response = ParadaDTO.class)
    @PutMapping(value = "/parada/{id}/editar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editParada(@ApiParam(name = "id", type = "Long",
            value = "O ID da parada", example = "1", required = true)
                                    @PathVariable("id") Long id, @RequestBody @Validated ParadaForm paradaForm) {
        Parada parada = this.paradaService.edit(id, paradaForm);
        return ResponseEntity.ok(new ParadaDTO(parada));
    }

}
