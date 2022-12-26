package com.elysantos.desafiosouth.model.domain;

import lombok.Data;

@Data
public class ResultadoVotacao {
  private StatusVotacao statusVotacao;
  private Integer votosSim = 0;
  private Integer votosNao = 0;

  public ResultadoVotacao() {
    statusVotacao = StatusVotacao.NAO_ENCERRADA;
  }
  public void computar(){
    if(votosSim > votosNao){
      statusVotacao = StatusVotacao.APROVADA;
      return;
    }
    if(votosSim < votosNao){
      statusVotacao = StatusVotacao.REPROVADA;
      return;
    }
    statusVotacao = StatusVotacao.EMPATE;
  }
}
