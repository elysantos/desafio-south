package com.elysantos.desafiosouth.controller.advice;

import com.elysantos.desafiosouth.controller.SessaoController;
import com.elysantos.desafiosouth.model.exception.VotoRepetidoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice(assignableTypes = SessaoController.class)
public class VotoControllerAdvice {

  @ExceptionHandler(VotoRepetidoException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleException(VotoRepetidoException vex){
    log.warn("Tentativa de voto repetido as {} com a mensagem: {} ", "",vex.getMessage());
    return new ResponseEntity<>(vex.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
