package com.elysantos.desafiosouth.service.implementation;

import com.elysantos.desafiosouth.service.ExternalRequestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@AllArgsConstructor
public class ExternalRequestServiceImpl implements ExternalRequestService {
  private final String url;

  public Flux<String> getRequest(String cpf){

    Flux<String> cpfFlux = WebClient.create()
        .get()
        .uri(url + cpf)
        .retrieve()
        .bodyToFlux(String.class);

    cpfFlux.subscribe(log::info);
    return cpfFlux;
  }
}
