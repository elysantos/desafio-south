package com.elysantos.desafiosouth.model.domain;

public enum StatusVotacao {
  NAO_ENCERRADA(0, "Votação encerrada"),
  APROVADA(1, "Pauta aprovada"),
  REPROVADA(2, "Pauta reprovada"),
  EMPATE(3, "Pauta com empate");
  private final int id;
  private final String descricao;

  StatusVotacao(int id, String descricao) {
    this.id = id;
    this.descricao = descricao;
  }

  public static StatusVotacao fromId(int id) {
    for (StatusVotacao status : StatusVotacao.values()) {
      if (status.id == id) {
        return status;
      }
    }
    return null;
  }

  @Override
  public String toString(){
    return this.descricao;
  }
}
