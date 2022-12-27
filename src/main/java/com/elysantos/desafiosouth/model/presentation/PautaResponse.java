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
  private String id;
  private String titulo;

  public PautaResponse(Pauta pauta){
    this.id =  pauta.getId().toString();
    this.titulo = pauta.getTitulo();
  }
}
