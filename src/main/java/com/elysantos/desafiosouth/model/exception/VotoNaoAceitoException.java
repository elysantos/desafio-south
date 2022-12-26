package com.elysantos.desafiosouth.model.exception;

public class VotoNaoAceitoException extends Exception{
  public VotoNaoAceitoException(String motivo) {
    super(motivo);
  }
}
