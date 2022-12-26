package com.elysantos.desafiosouth.model.domain;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Voto {
  private UUID id;
  private Associado associado;
  private ValorVoto valorVoto;

  private Sessao sessao;

  public Voto(Associado associado, Sessao sessao, ValorVoto valorVoto) {
    this.associado = associado;
    this.valorVoto = valorVoto;
    this.sessao = sessao;
    this.id = UUID.randomUUID();
  }
}
