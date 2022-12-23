package com.elysantos.desafiosouth.service.implementation;

import com.elysantos.desafiosouth.model.domain.Sessao;
import com.elysantos.desafiosouth.model.domain.ValorVoto;
import com.elysantos.desafiosouth.model.exception.ItemNaoEncontradoException;
import com.elysantos.desafiosouth.model.exception.VotoRepetidoException;
import com.elysantos.desafiosouth.repository.SessaoRepository;
import com.elysantos.desafiosouth.service.ExternalRequestService;
import com.elysantos.desafiosouth.service.SessaoService;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SessaoServiceImpl implements SessaoService {
  private final SessaoRepository sessaoRepository;
  private final ExternalRequestService externalRequestService;
  @Override
  public Sessao createSession(Sessao sessao) {
    return null;
  }

  @Override
  public Collection<Sessao> listar() {
    return Collections.emptyList();
  }

  @Override
  public Sessao obter(Integer id) throws ItemNaoEncontradoException {
    Sessao sessao = sessaoRepository.findById(id);
    if(sessao == null){
      throw new ItemNaoEncontradoException();
    }
    return sessao;
  }

  @Override
  public UUID computeVote(String cpf, Integer idSessao, ValorVoto valorVoto) throws VotoRepetidoException {
    if(sessaoRepository.findVoto(cpf, idSessao)){
      throw new VotoRepetidoException(cpf);
    }
    return UUID.randomUUID();
  }
}
