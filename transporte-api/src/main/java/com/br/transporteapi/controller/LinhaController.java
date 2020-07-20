package com.br.transporteapi.controller;

import com.br.transporteapi.configuration.swagger.ApiPageableDefaultFields;
import com.br.transporteapi.controller.DTO.LinhaDTO;
import com.br.transporteapi.controller.form.LinhaForm;
import com.br.transporteapi.model.Linha;
import com.br.transporteapi.service.LinhaService;
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
public class LinhaController {

    @Autowired
    private LinhaService linhaService;

    @ApiOperation(value = "Retorna todas as linhas existentes", response = LinhaDTO.class)
    @GetMapping(value = "/linhas/todas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllLinhas() {
        return ResponseEntity.ok(LinhaDTO.convert(this.linhaService.findAll()));
    }

    @ApiOperation(value = "Retorna uma lista paginada de linhas que passam por uma parada específica", response = LinhaDTO.class)
    @ApiPageableDefaultFields
    @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
            value = "Específica o campo pelo qual você deseja ordenar os registros",
            defaultValue = "nome")
    @GetMapping(value = "/linhas/parada", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllLinhasByParadas(@PageableDefault(sort = "nome", direction = Sort.Direction.ASC, page = 0, size = 10)
                                             @ApiIgnore Pageable pageable,
                                             @ApiParam(name = "parada_id", type = "Long",
                                                     value = "ID da parada", example = "1", required = true)
                                             @RequestParam(value = "parada_id") Long idParada) {
        return ResponseEntity.ok(LinhaDTO.convert(this.linhaService.findAllByParadasId(pageable, idParada)));
    }

    @ApiOperation(value = "Retorna uma linha específica", response = LinhaDTO.class)
    @GetMapping(value = "/linha/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findLinha(@ApiParam(name = "id", type = "Long",
            value = "ID da linha", example = "1", required = true)
                                   @PathVariable("id") Long id) {
        return ResponseEntity.ok(new LinhaDTO(this.linhaService.findById(id)));
    }

    @ApiOperation(value = "Retorna uma lista paginada de linhas", response = LinhaDTO.class)
    @ApiPageableDefaultFields
    @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
            value = "Específica o campo pelo qual você deseja ordenar os registros",
            defaultValue = "nome")
    @GetMapping(value = "/linhas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllLinhasPage(@PageableDefault(sort = "nome", direction = Sort.Direction.ASC, page = 0, size = 10)
             @ApiIgnore Pageable pageable) {

        return ResponseEntity.ok(LinhaDTO.convert(this.linhaService.findAll(pageable)));
    }

    @ApiOperation(value = "Deleta uma linha específica", response = ResponseEntity.class)
    @DeleteMapping("/linha/{id}/deletar")
    public ResponseEntity<?> deleteLinha(@ApiParam(name = "id", type = "Long",
            value = "ID da linha", example = "1", required = true) @PathVariable("id") Long id) {
        this.linhaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Cadastra uma linha", response = LinhaDTO.class)
    @PostMapping(value = "/linha/cadastrar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveLinha(@RequestBody @Validated LinhaForm linhaForm, UriComponentsBuilder uriComponentsBuilder) {
        Linha linha = this.linhaService.save(linhaForm);

        URI uri = uriComponentsBuilder.path("/linha/{id}").buildAndExpand(linha.getId()).toUri();

        return ResponseEntity.created(uri).body(new LinhaDTO(linha));
    }

    @ApiOperation(value = "Edita os campos de uma linha específica", response = LinhaDTO.class)
    @PutMapping(value = "/linha/{id}/editar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editLinha(@ApiParam(name = "id", type = "Long",
            value = "O ID da linha", example = "1", required = true) @PathVariable("id") Long id,
                                    @RequestBody @Validated LinhaForm linhaForm) {
        Linha linha = this.linhaService.edit(id, linhaForm);

        return ResponseEntity.ok(new LinhaDTO(linha));
    }
}
