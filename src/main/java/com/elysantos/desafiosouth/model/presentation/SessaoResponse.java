package com.elysantos.desafiosouth.model.presentation;

import com.elysantos.desafiosouth.model.domain.ResultadoVotacao;
import com.elysantos.desafiosouth.model.domain.Sessao;
import lombok.Getter;

@Getter
public class SessaoResponse {

  private final PautaResponse        pauta;
  private final ResultadoVotacao resultado;
  public SessaoResponse(Sessao session) {
    this.pauta = new PautaResponse(session.getPauta());
    this.resultado = session.getResultadoVotacao();

  }
}
