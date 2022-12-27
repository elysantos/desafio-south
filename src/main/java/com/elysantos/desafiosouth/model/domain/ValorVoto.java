package com.elysantos.desafiosouth.model.domain;

public enum ValorVoto {
  SIM(true),
  NAO(false);
  private final boolean valor;
  ValorVoto(boolean valor) {
    this.valor = valor;
  }

  public static ValorVoto valueFromBool(boolean value){
    for (ValorVoto valor : ValorVoto.values()) {
      if (valor.valor == value) {
        return valor;
      }
    }
    return null;
  }

  public boolean getValor(){
    return this.valor;
  }
}
