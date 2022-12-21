package com.elysantos.desafiosouth.model.domain;

public class Voto {
  private Associado associado;
  private ValorVoto valorVoto;

  public Voto() {
  }

  public Voto(Associado associado, ValorVoto valorVoto) {
    this.associado = associado;
    this.valorVoto = valorVoto;
  }

  public Associado getAssociado() {
    return associado;
  }

  public void setAssociado(Associado associado) {
    this.associado = associado;
  }

  public ValorVoto getValorVoto() {
    return valorVoto;
  }

  public void setValorVoto(ValorVoto valorVoto) {
    this.valorVoto = valorVoto;
  }
}
