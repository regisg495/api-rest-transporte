package com.br.transporteapi.controller;

import com.br.transporteapi.configuration.swagger.ApiPageableDefaultFields;
import com.br.transporteapi.controller.DTO.VeiculoDTO;
import com.br.transporteapi.controller.form.VeiculoForm;
import com.br.transporteapi.model.Veiculo;
import com.br.transporteapi.service.VeiculoService;
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
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @ApiOperation(value = "Retorna todos os veículos existentes", response = VeiculoDTO.class)
    @GetMapping(value = "/veiculos/todos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllVeiculos() {
        return ResponseEntity.ok(VeiculoDTO.convert(this.veiculoService.findAll()));
    }

    @ApiOperation(value = "Retorna uma lista paginada de veículos que fazem parte de uma linha específica", response = VeiculoDTO.class)
    @ApiPageableDefaultFields
    @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
            value = "Específica o campo pelo qual você deseja ordenar os registros",
            defaultValue = "nome")
    @GetMapping(value = "/veiculos/linha", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findVeiculosByLinhaPage(@PageableDefault(sort = "nome", direction = Sort.Direction.ASC, page = 0, size = 10) @ApiIgnore Pageable pageable,
                                              @ApiParam(name = "linha_id", type = "Long",
                                                      value = "ID da linha", example = "1", required = true)
                                              @RequestParam(value = "linha_id") Long idLinha) {
        return ResponseEntity.ok(VeiculoDTO.convert(this.veiculoService.findAllByLinha_Id(pageable, idLinha)));
    }

    @ApiOperation(value = "Retorna um veículo específico", response = VeiculoDTO.class)
    @GetMapping(value = "/veiculo/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findVeiculo(@ApiParam(name = "id", type = "Long",
            value = "ID do veículo", example = "1", required = true) @PathVariable("id") Long id) {
        return ResponseEntity.ok(new VeiculoDTO(this.veiculoService.findById(id)));
    }

    @ApiOperation(value = "Retorna uma lista paginada de veículos")
    @ApiPageableDefaultFields
    @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
            value = "Específica o campo pelo qual você deseja ordenar os registros",
            defaultValue = "nome")
    @GetMapping(value = "/veiculos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllVeiculosPage(@PageableDefault(sort = "nome", direction = Sort.Direction.ASC, page = 0, size = 10)
                                      @ApiIgnore Pageable pageable) {
        return ResponseEntity.ok(VeiculoDTO.convert(this.veiculoService.findAll(pageable)));
    }

    @ApiOperation(value = "Deleta um veículo específico", response = ResponseEntity.class)
    @DeleteMapping(value = "/veiculo/{id}/deletar")
    public ResponseEntity<?> deleteVeiculo(@ApiParam(name = "id", type = "Long",
            value = "ID do veículo", example = "1", required = true) @PathVariable("id") Long id) {
        this.veiculoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Cadastra um veículo", response = VeiculoDTO.class)
    @PostMapping(value = "/veiculo/cadastrar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveVeiculo(@RequestBody @Validated VeiculoForm veiculoForm, UriComponentsBuilder uriComponentsBuilder) {
        Veiculo veiculo = this.veiculoService.save(veiculoForm);

        URI uri = uriComponentsBuilder.path("/veiculo/{id}").buildAndExpand(veiculo.getId()).toUri();

        return ResponseEntity.created(uri).body(new VeiculoDTO(veiculo));
    }

    @ApiOperation(value = "Edita um veículo específico", response = VeiculoDTO.class)
    @PutMapping(value = "/veiculo/{id}/editar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editVeiculo(@ApiParam(name = "id", type = "Long", value = "ID do veículo", example = "1", required = true)
                                    @PathVariable("id") Long id, @RequestBody @Validated VeiculoForm veiculoForm) {
        Veiculo veiculo = this.veiculoService.edit(id, veiculoForm);

        return ResponseEntity.ok(new VeiculoDTO(veiculo));
    }
}
