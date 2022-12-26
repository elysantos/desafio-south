package com.elysantos.desafiosouth.model.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class Sessao {
  private UUID id;
  private List<Voto> votos;

  private Pauta pauta;
  private Integer duracao;
  private LocalDateTime dateTimeInicio;

  private ResultadoVotacao resultadoVotacao = new ResultadoVotacao();

  public Sessao(String inicio, Integer duracao, String idPauta) throws IllegalArgumentException {
    LocalDateTime dtInicio = LocalDateTime.now();
    if(!inicio.isEmpty()){
      dtInicio = LocalDateTime.parse(inicio, DateTimeFormatter.ISO_DATE_TIME);
    }
    this.dateTimeInicio = dtInicio;
    this.duracao = (duracao != null && duracao > 0 ) ? duracao: 1;

    this.pauta = new Pauta();
    pauta.setId(UUID.fromString(idPauta));

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
