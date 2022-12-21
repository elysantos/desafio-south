package com.elysantos.desafiosouth.model.domain;

public enum ValorVoto {
  SIM(true),
  NAO(false);
  private final boolean valor;
  ValorVoto(boolean valor) {
    this.valor = valor;
  }
}
