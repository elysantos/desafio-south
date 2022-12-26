package com.elysantos.desafiosouth.model.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class Sessao {
  private UUID id;
  private List<Voto> votos;

  private Pauta pauta;
  private Integer duracao = 1;
  private LocalDateTime dateTimeInicio;

  private ResultadoVotacao resultadoVotacao = new ResultadoVotacao();

  public Sessao(LocalDateTime dtInicio) {
    this.dateTimeInicio = dtInicio;
  }

  public Sessao(LocalDateTime dtInicio, Integer duracao) {
    this.dateTimeInicio = dtInicio;
    this.duracao = duracao;
  }

  public void gerarUUID() {
    this.id = UUID.randomUUID();
  }

  public void encerrarVotacao(){
    Map<Voto, Long> resultado = votos.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    resultado.forEach((k,v) -> {
      if(k.getValorVoto() == ValorVoto.SIM){
        this.resultadoVotacao.setVotosSim(v.intValue());
      }
      if(k.getValorVoto() == ValorVoto.NAO){
        this.resultadoVotacao.setVotosNao(v.intValue());
      }
    });
    this.resultadoVotacao.computar();
  }

  public String toMessage(){
    String sb = "Resultado votação: ";
    return sb.concat("\nPauta: ").concat(pauta.getTitulo())
        .concat("\nVotos(SIM): ").concat(String.valueOf(resultadoVotacao.getVotosSim()))
        .concat("\nVotos(NAO): ").concat(String.valueOf(resultadoVotacao.getVotosSim()))
        .concat("\nResultado: ").concat(resultadoVotacao.getStatusVotacao().toString());

  }
}
