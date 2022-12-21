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
  private String name;

  public static PautaRequest fromDomain(Pauta pauta){
    PautaRequest pautaRequest = new PautaRequest();
    pautaRequest.setName(pauta.getTitulo());

    return pautaRequest;
  }

  public Pauta toDomain(){
    return new Pauta(this.name);
  }
}
