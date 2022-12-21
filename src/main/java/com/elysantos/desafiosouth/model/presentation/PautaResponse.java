package com.elysantos.desafiosouth.model.presentation;

import com.elysantos.desafiosouth.model.domain.Pauta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PautaResponse {
  private Integer id;
  private String title;

  public PautaResponse(Pauta pauta){
    this.title = pauta.getTitulo();
  }
}
