package com.elysantos.desafiosouth.controller;

import com.elysantos.desafiosouth.model.domain.Sessao;
import com.elysantos.desafiosouth.model.domain.ValorVoto;
import com.elysantos.desafiosouth.model.exception.ItemDuplicatedException;
import com.elysantos.desafiosouth.model.exception.ItemNaoEncontradoException;
import com.elysantos.desafiosouth.model.exception.VotoNaoAceitoException;
import com.elysantos.desafiosouth.model.presentation.SessaoRequest;
import com.elysantos.desafiosouth.model.presentation.SessaoResponse;
import com.elysantos.desafiosouth.service.SessaoService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sessoes")
@RequiredArgsConstructor
public class SessaoController {

  private final SessaoService sessaoService;

  @Operation(summary = "Criar nova sessao")
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<SessaoResponse> create(@RequestBody SessaoRequest request) throws ItemDuplicatedException {
    SessaoResponse response = new SessaoResponse(sessaoService.createSession(request.toDomain()));
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @Operation(summary = "Obter a partir de ID")
  @GetMapping(value = "/{id}", produces = "application/json")
  public ResponseEntity<SessaoResponse> getOne(@PathVariable("id") Integer id) throws ItemNaoEncontradoException {
    Sessao sessao = sessaoService.obter(id);
    return new ResponseEntity<>(new SessaoResponse(sessao), HttpStatus.OK);
  }

  @Operation(summary = "Listar todos")
  @GetMapping(produces = "application/json")
  public ResponseEntity<List<SessaoResponse>> listAll() {
    List<SessaoResponse> responses =
        sessaoService.listar().stream()
            .map(SessaoResponse::new).collect(Collectors.toList());
    return new ResponseEntity<>(responses, HttpStatus.OK);
  }

  @Operation(summary = "Computar voto a sessao")
  @PostMapping(value = "/{id}/", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> votar(@PathVariable("id") Integer id,
                                      @RequestParam("associado") String cpfAssociado,
                                      @RequestParam("voto") String voto ) throws VotoNaoAceitoException, ItemNaoEncontradoException {
    UUID uuidVote = sessaoService.computeVote(cpfAssociado, id, ValorVoto.valueOf(voto));
    return ResponseEntity.ok(uuidVote.toString());
  }

}
