package com.elysantos.desafiosouth.model.domain;

import com.elysantos.desafiosouth.model.exception.VotoRepetidoException;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class Sessao {
  private List<Voto> votos;
  private Pauta pauta;
  private Integer duracao = 1;
  private LocalDateTime dateTimeInicio;

  public Sessao(LocalDateTime dtInicio) {
    this.dateTimeInicio = dtInicio;
  }

  public Sessao(LocalDateTime dtInicio, Integer duracao) {
    this.dateTimeInicio = dtInicio;
    this.duracao = duracao;
  }

  public LocalDateTime getDateTimeInicio() {
    return dateTimeInicio;
  }

  public List<Voto> getVotos() {
    return votos;
  }

  public Integer getDuracao() {
    return duracao;
  }

  public void addVoto(Associado associado, ValorVoto valorVoto) throws VotoRepetidoException {
    if(!podeVotar(associado)){
      throw new VotoRepetidoException(associado.getCpf());
    }
    Voto voto = new Voto(associado, valorVoto);
    this.votos.add(voto);
  }

  private boolean podeVotar(Associado associado){
    return votos.stream().anyMatch(voto -> voto.getAssociado().equals(associado));
  }
}
