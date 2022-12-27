package com.elysantos.desafiosouth.service.implementation;

import com.elysantos.desafiosouth.model.domain.Associado;
import com.elysantos.desafiosouth.model.domain.AssociadoEnabled;
import com.elysantos.desafiosouth.model.domain.Sessao;
import com.elysantos.desafiosouth.model.domain.ValorVoto;
import com.elysantos.desafiosouth.model.domain.Voto;
import com.elysantos.desafiosouth.model.exception.ItemDuplicatedException;
import com.elysantos.desafiosouth.model.exception.ItemNaoEncontradoException;
import com.elysantos.desafiosouth.model.exception.VotoNaoAceitoException;
import com.elysantos.desafiosouth.repository.SessaoRepository;
import com.elysantos.desafiosouth.repository.VotoRepository;
import com.elysantos.desafiosouth.service.ExternalRequestService;
import com.elysantos.desafiosouth.service.SessaoService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SessaoServiceImpl implements SessaoService {
  private final        SessaoRepository       sessaoRepository;
  private final        ExternalRequestService externalRequestService;
  private final        VotoRepository         votoRepository;
  private static final String                 TYPE = "sessao";

  @Override
  public Sessao createSession(Sessao sessao) throws ItemDuplicatedException {
    sessao.gerarUUID();
    try {
      sessaoRepository.insert(sessao);
    } catch (DataIntegrityViolationException dve) {
      log.warn(dve.getMessage());
      throw new ItemDuplicatedException(TYPE);
    }
    return sessao;
  }

  @Override
  public List<Sessao> listar() {
    return sessaoRepository.findAll();
  }

  @Override
  public Sessao obter(String id) throws ItemNaoEncontradoException {
    try {
      UUID uuid = UUID.fromString(id);
      Sessao sessao = sessaoRepository.findById(uuid.toString());
      if (sessao == null) {
        throw new ItemNaoEncontradoException(id, TYPE);
      }
      return sessao;
    } catch (IllegalArgumentException ex) {
      throw new ItemNaoEncontradoException(id, TYPE);
    }


  }

  @Override
  public UUID computeVote(String cpf, String idSessao, ValorVoto valorVoto) throws VotoNaoAceitoException, ItemNaoEncontradoException {
    if (votoRepository.findByCpfAndSessao(cpf, idSessao)) {
      throw new VotoNaoAceitoException("Usuário cpf" + cpf + " já efetuou o voto");
    }
    Associado associado = validaCpf(cpf);
    if (associado.getAssociadoEnabled() != AssociadoEnabled.ABLE_TO_VOTE) {
      throw new VotoNaoAceitoException("Usuário cpf" + cpf + " não está habilitado para votar");
    }
    Sessao sessao = sessaoRepository.findById(idSessao);
    if (sessao == null) {
      throw new ItemNaoEncontradoException(String.valueOf(idSessao), TYPE);
    }
    Voto voto = new Voto(associado, sessao, valorVoto);
    votoRepository.insert(voto);
    return voto.getId();
  }

  private Associado validaCpf(String cpf) throws ItemNaoEncontradoException {
    return externalRequestService.validaCPF(cpf);
  }
}
