package com.br.transporteapi.controller;

import com.br.transporteapi.configuration.swagger.ApiPageableDefaultFields;
import com.br.transporteapi.controller.DTO.PosicaoVeiculoDTO;
import com.br.transporteapi.controller.form.PosicaoVeiculoForm;
import com.br.transporteapi.model.PosicaoVeiculo;
import com.br.transporteapi.service.PosicaoVeiculoService;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;

import java.net.URI;

@RestController
public class PosicaoVeiculoController {

    @Autowired
    private PosicaoVeiculoService posicaoVeiculoService;

    @ApiOperation(value = "Retorna a posição de todos os veículos existentes", response = PosicaoVeiculoDTO.class)
    @GetMapping(value = "/veiculos/posicao/todos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllPosicaoVeiculos() {
        return ResponseEntity.ok(PosicaoVeiculoDTO.convert(this.posicaoVeiculoService.findAll()));
    }

    @ApiOperation(value = "Retorna uma lista paginada da posição dos veículos", response = PosicaoVeiculoDTO.class)
    @ApiPageableDefaultFields
    @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
            value = "Específica o campo pelo qual você deseja ordenar os registros",
            defaultValue = "id")
    @GetMapping(value = "/veiculos/posicao", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllPosicaoVeiculosPage(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) @ApiIgnore Pageable pageable) {
        return ResponseEntity.ok(PosicaoVeiculoDTO.convert(this.posicaoVeiculoService.findAll(pageable)));
    }

    @ApiOperation(value = "Retorna a posição de um veículo específico", response = PosicaoVeiculoDTO.class)
    @GetMapping(value = "/veiculo/{idVeiculo}/posicao", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findPosicaoVeiculo(@ApiParam(name = "idVeiculo", type = "Long",
            value = "ID do veículo", example = "1", required = true)
                                            @PathVariable("idVeiculo") Long idVeiculo) {
        return ResponseEntity.ok(new PosicaoVeiculoDTO(this.posicaoVeiculoService.findById(idVeiculo)));
    }

    @ApiOperation(value = "Deleta a posição de um veículo especifico", response = ResponseEntity.class)
    @DeleteMapping(value = "/veiculo/{idVeiculo}/posicao/deletar")
    public ResponseEntity<?> deletePosicaoVeiculo(@ApiParam(name = "idVeiculo", type = "Long",
            value = "ID do veículo", example = "1", required = true)
                                     @PathVariable("idVeiculo") Long idVeiculo) {
        this.posicaoVeiculoService.deleteById(idVeiculo);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Cadastra uma posição em um veículo específico", response = PosicaoVeiculoDTO.class)
    @PostMapping(value = "/veiculo/{idVeiculo}/posicao/cadastrar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePosicaoVeiculo(@ApiParam(name = "idVeiculo", type = "Long",
            value = "ID do veículo", example = "1", required = true)
                                       @PathVariable("idVeiculo") Long idVeiculo,
                                       @RequestBody @Validated PosicaoVeiculoForm posicaoVeiculoForm,
                                       UriComponentsBuilder uriComponentsBuilder) {
        posicaoVeiculoForm.setIdVeiculo(idVeiculo);
        PosicaoVeiculo posicaoVeiculo = this.posicaoVeiculoService.save(posicaoVeiculoForm);

        URI uri = uriComponentsBuilder.path("/veiculo/{idVeiculo}/posicao").buildAndExpand(posicaoVeiculo.getId()).toUri();

        return ResponseEntity.created(uri).body(new PosicaoVeiculoDTO(posicaoVeiculo));
    }

    @ApiOperation(value = "Edita a posição de um veículo específico", response = PosicaoVeiculoDTO.class)
    @PutMapping(value = "/veiculo/{idVeiculo}/posicao/editar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editPosicaoVeiculo(@ApiParam(name = "idVeiculo", type = "Long",
            value = "ID do veículo", example = "1", required = true)
                                    @PathVariable("idVeiculo") Long idVeiculo, @RequestBody @Validated PosicaoVeiculoForm posicaoVeiculoForm) {
        PosicaoVeiculo posicaoVeiculo = this.posicaoVeiculoService.edit(idVeiculo, posicaoVeiculoForm);

        return ResponseEntity.ok(new PosicaoVeiculoDTO(posicaoVeiculo));
    }
}
