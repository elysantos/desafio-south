package com.elysantos.desafiosouth.model.exception;

public class ItemDuplicatedException extends Exception{
  public ItemDuplicatedException(String tipo) {
    super("Já existe " + tipo + " para os dados informados");
  }
}
