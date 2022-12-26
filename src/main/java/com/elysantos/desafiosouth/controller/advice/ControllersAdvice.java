package com.elysantos.desafiosouth.controller.advice;

import com.elysantos.desafiosouth.controller.PautaController;
import com.elysantos.desafiosouth.controller.SessaoController;
import com.elysantos.desafiosouth.model.exception.ItemNaoEncontradoException;
import com.elysantos.desafiosouth.model.exception.VotoNaoAceitoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice(assignableTypes = {PautaController.class, SessaoController.class})
public class ControllersAdvice {

  @ExceptionHandler(VotoNaoAceitoException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleException(VotoNaoAceitoException vex){
    log.warn("Tentativa de voto com a mensagem: {} ", vex.getMessage());
    return new ResponseEntity<>(vex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ItemNaoEncontradoException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> handleException(ItemNaoEncontradoException iex){
    log.error("Item não encontrado {} ", iex.getMessage());
    return new ResponseEntity<>(iex.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
