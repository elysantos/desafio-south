package com.elysantos.desafiosouth.model.exception;

public class VotoRepetidoException extends Exception{
  public VotoRepetidoException(String cpfAssociado) {
    super("Usuário cpf "+ cpfAssociado + " não está habilitado para votar");
  }
}
