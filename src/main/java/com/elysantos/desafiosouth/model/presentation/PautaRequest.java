package com.elysantos.desafiosouth.model.presentation;

import com.elysantos.desafiosouth.model.domain.Pauta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PautaRequest {
  private String titulo;

  public Pauta toDomain(){
    return new Pauta(this.titulo);
  }
}
