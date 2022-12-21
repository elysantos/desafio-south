package com.elysantos.desafiosouth.model.domain;

public enum ResultadoVotacao {
  NAO_INICIADA(0, "Votação não iniciada"),
  NAO_ENCERRADA(1, "Votação encerrada"),
  APROVADA(2, "Pauta aprovada"),
  REPROVADA(3, "Pauta reprovada");
  private final int id;
  private final String descricao;

  ResultadoVotacao(int id, String descricao) {
    this.id = id;
    this.descricao = descricao;
  }

  public static ResultadoVotacao fromId(int id) {
    for (ResultadoVotacao resultadoVotacao : ResultadoVotacao.values()) {
      if (resultadoVotacao.id == id) {
        return resultadoVotacao;
      }
    }
    return null;
  }

  @Override
  public String toString(){
    return this.descricao;
  }
}
