package com.elysantos.desafiosouth.model.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class Sessao {
  private UUID id;
  private List<Voto> votos = new ArrayList<>();

  private Pauta pauta;
  private Integer duracao;
  private LocalDateTime dateTimeInicio;

  private ResultadoVotacao resultadoVotacao = new ResultadoVotacao();

  public static Sessao criarSessao(String inicio, Integer duracao, String idPauta) throws IllegalArgumentException {
    Sessao sessao =  new Sessao();
    sessao.gerarUUID();
    LocalDateTime dtInicio = LocalDateTime.now();
    if(inicio != null && !inicio.isEmpty()){
      dtInicio = LocalDateTime.parse(inicio, DateTimeFormatter.ISO_DATE_TIME);
    }
    sessao.dateTimeInicio = dtInicio;
    sessao.duracao = (duracao != null && duracao > 0 ) ? duracao: 1;

    sessao.pauta = new Pauta();
    sessao.pauta.setId(UUID.fromString(idPauta));
    return sessao;
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
    if(this.dateTimeInicio.plusMinutes(duracao).isAfter(LocalDateTime.now())){
      this.resultadoVotacao.setStatusVotacao(StatusVotacao.NAO_ENCERRADA);
    }
  }

  public String toMessage(){
    String sb = "Resultado votação: ";
    return sb.concat("\nPauta: ").concat(pauta.getTitulo())
        .concat("\nVotos(SIM): ").concat(String.valueOf(resultadoVotacao.getVotosSim()))
        .concat("\nVotos(NAO): ").concat(String.valueOf(resultadoVotacao.getVotosSim()))
        .concat("\nResultado: ").concat(resultadoVotacao.getStatusVotacao().toString());

  }

  public boolean isAberta(){
    LocalDateTime dataEncerramento = this.dateTimeInicio.plusMinutes(duracao);
    return dataEncerramento.isAfter(LocalDateTime.now());
  }
}
