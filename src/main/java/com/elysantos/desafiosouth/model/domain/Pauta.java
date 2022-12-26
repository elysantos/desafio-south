package com.elysantos.desafiosouth.model.domain;

import java.util.UUID;
import lombok.Data;

@Data
public class Pauta {
  private UUID id;
  private String titulo;

  public Pauta() {
  }

  public Pauta(String titulo) {
    this.id = UUID.randomUUID();
    this.titulo = titulo;
  }

  public String getTitulo() {
    return titulo;
  }
}
