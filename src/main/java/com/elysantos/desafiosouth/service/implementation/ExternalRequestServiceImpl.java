package com.elysantos.desafiosouth.service.implementation;

import com.elysantos.desafiosouth.model.domain.Associado;
import com.elysantos.desafiosouth.model.domain.AssociadoEnabled;
import com.elysantos.desafiosouth.repository.AssociadoRepository;
import com.elysantos.desafiosouth.service.ExternalRequestService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
@AllArgsConstructor
public class ExternalRequestServiceImpl implements ExternalRequestService {

  private final AssociadoRepository associadoRepository;
  private final String externalValidator;

  private AssociadoEnabled getEnabledStatus(String cpf){

    return WebClient.create()
        .get()
        .uri(externalValidator + cpf)
        .retrieve()
        .bodyToMono(String.class)
        .mapNotNull(AssociadoEnabled::fromStatus)
        .block();

  }

  @Override
  public Associado validaCPF(String cpf) {
    Associado associado = associadoRepository.findByCPF(cpf);
    if(associado != null){
      return associado;
    }

    AssociadoEnabled resultStatus = getEnabledStatus(cpf);

    associado = new Associado(UUID.randomUUID(), cpf, resultStatus);
    associadoRepository.insert(associado);

    return associado;
  }
}
