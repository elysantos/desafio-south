package com.elysantos.desafiosouth.controller;

import com.elysantos.desafiosouth.model.domain.Pauta;
import com.elysantos.desafiosouth.model.exception.ItemDuplicatedException;
import com.elysantos.desafiosouth.model.exception.ItemNaoEncontradoException;
import com.elysantos.desafiosouth.model.presentation.PautaRequest;
import com.elysantos.desafiosouth.model.presentation.PautaResponse;
import com.elysantos.desafiosouth.service.PautaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pautas")
@RequiredArgsConstructor
public class PautaController {

  private final PautaService pautaService;

  @Operation(summary = "Criar nova pauta")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Successfully created"),
      @ApiResponse(responseCode = "400", description = "Unable to create "),
      @ApiResponse(responseCode = "500", description = "Internal server error")})
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PautaResponse> create(@RequestBody PautaRequest pautaRequest) throws ItemDuplicatedException {
    Pauta pauta = pautaService.criar(pautaRequest.toDomain());
    PautaResponse response = new PautaResponse(pauta);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @Operation(summary = "Atualizar pauta")
  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PautaResponse> update(@RequestBody PautaRequest pautaRequest) throws ItemNaoEncontradoException {
    Pauta pauta = pautaService.atualizar(pautaRequest.toDomain());
    return ResponseEntity.accepted().body(new PautaResponse(pauta));
  }

  @Operation(summary = "Obter a partir de ID")
  @GetMapping(value = "/{id}", produces = "application/json")
  public ResponseEntity<PautaResponse> getOne(@PathVariable("id") String id) throws ItemNaoEncontradoException {
    Pauta pauta = pautaService.obter(id);
    return new ResponseEntity<>(new PautaResponse(pauta), HttpStatus.OK);
  }


  @Operation(summary = "Listar todos")
  @GetMapping(produces = "application/json")
  public ResponseEntity<List<PautaResponse>> listAll() {
    List<PautaResponse> responses = pautaService.listar().stream().map(PautaResponse::new).collect(Collectors.toList());
    return new ResponseEntity<>(responses, HttpStatus.OK);
  }

  @Operation(summary = "Apagar caso encontre item com ID especificado")
  @DeleteMapping(value = "{id}", produces = "application/json")
  public ResponseEntity<PautaResponse> delete(@PathVariable("id") String id) throws ItemNaoEncontradoException {
    Pauta pauta = pautaService.apagar(id);
    return new ResponseEntity<>(new PautaResponse(pauta), HttpStatus.OK);
  }


}
