package com.elysantos.desafiosouth.model.presentation;

import com.elysantos.desafiosouth.model.domain.ResultadoVotacao;
import com.elysantos.desafiosouth.model.domain.Sessao;
import java.util.Map;
import lombok.Getter;

@Getter
public class SessaoResponse {

  private PautaResponse        pauta;
  private Map<String, Integer> votos;
  private ResultadoVotacao resultado;
  public SessaoResponse(Sessao session) {
    this.pauta = new PautaResponse(session.getPauta());

  }
}
