package com.elysantos.desafiosouth.service.implementation;

import com.elysantos.desafiosouth.model.domain.Associado;
import com.elysantos.desafiosouth.model.domain.AssociadoEnabled;
import com.elysantos.desafiosouth.model.exception.ItemNaoEncontradoException;
import com.elysantos.desafiosouth.repository.AssociadoRepository;
import com.elysantos.desafiosouth.service.ExternalRequestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor
public class ExternalRequestServiceImpl implements ExternalRequestService {

  private final AssociadoRepository associadoRepository;
  private final String externalValidator;
  private Mono<String> getRequest(String cpf){

    Mono<String> cpfFlux = WebClient.create()
        .get()
        .uri(externalValidator + cpf)
        .retrieve()
        .bodyToMono(String.class);

    cpfFlux.subscribe(log::info);
    return cpfFlux;
  }

  @Override
  public Associado validaCPF(String cpf) throws ItemNaoEncontradoException {
    Associado associado = associadoRepository.findByCPF(cpf);
    if(associado == null){
      throw new ItemNaoEncontradoException(cpf, "Associado");
    }
    AssociadoEnabled resultStatus = getRequest(cpf).mapNotNull(AssociadoEnabled::fromStatus).block();

    associado.setAssociadoEnabled(resultStatus);

    return associado;
  }
}
