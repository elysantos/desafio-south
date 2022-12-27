package com.elysantos.desafiosouth.model.presentation;

import com.elysantos.desafiosouth.model.domain.Sessao;
import com.elysantos.desafiosouth.model.exception.FormatoIncorretoException;
import lombok.Data;

@Data
public class SessaoRequest {
  private String pautaID;
  private Integer duracao;
  private String inicio;

  public Sessao toDomain() throws FormatoIncorretoException {
    Sessao sessao;
    try{
      sessao = Sessao.criarSessao(this.inicio, this.duracao, pautaID);
    }catch (IllegalArgumentException exception){
      throw new FormatoIncorretoException(exception.getMessage());
    }
    return sessao;
  }
}
