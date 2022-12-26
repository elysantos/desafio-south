package com.elysantos.desafiosouth.model.presentation;

import com.elysantos.desafiosouth.model.domain.ResultadoVotacao;
import com.elysantos.desafiosouth.model.domain.Sessao;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SessaoResponse {

  private PautaResponse    pauta;
  private String           dtInicio;
  private ResultadoVotacao resultado;

  public SessaoResponse(Sessao session) {
    this.pauta = new PautaResponse(session.getPauta());
    this.resultado = session.getResultadoVotacao();
    this.dtInicio = session.getDateTimeInicio().format(DateTimeFormatter.ISO_DATE_TIME);

  }
}
