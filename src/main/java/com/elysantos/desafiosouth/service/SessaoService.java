package com.elysantos.desafiosouth.service;

import com.elysantos.desafiosouth.model.domain.Sessao;
import com.elysantos.desafiosouth.model.domain.ValorVoto;
import com.elysantos.desafiosouth.model.exception.ItemNaoEncontradoException;
import com.elysantos.desafiosouth.model.exception.VotoRepetidoException;
import java.util.Collection;
import java.util.UUID;

public interface SessaoService {
  UUID computeVote(String cpf, Integer idSessao, ValorVoto valorVoto) throws VotoRepetidoException;

  Sessao createSession(Sessao sessao);
  Collection<Sessao> listar();
  Sessao obter(Integer id) throws ItemNaoEncontradoException;

}
