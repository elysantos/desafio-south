package com.elysantos.desafiosouth.model.exception;

public class ItemNaoEncontradoException extends Exception {
  public ItemNaoEncontradoException(String item, String tipo) {
    super("Não encontrado Item " + item + " do Tipo " + tipo);
  }
}
