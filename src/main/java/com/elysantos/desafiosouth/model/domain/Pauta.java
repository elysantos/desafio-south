package com.elysantos.desafiosouth.model.domain;

import java.util.UUID;

public class Pauta {
  private UUID id;
  private String titulo;

  public Pauta() {
  }

  public Pauta(String titulo) {
    this.id = UUID.randomUUID();
    this.titulo = titulo;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

}
