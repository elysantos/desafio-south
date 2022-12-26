package com.elysantos.desafiosouth.service;

import com.elysantos.desafiosouth.model.domain.Sessao;
import com.elysantos.desafiosouth.model.domain.ValorVoto;
import com.elysantos.desafiosouth.model.exception.ItemDuplicatedException;
import com.elysantos.desafiosouth.model.exception.ItemNaoEncontradoException;
import com.elysantos.desafiosouth.model.exception.VotoNaoAceitoException;
import java.util.List;
import java.util.UUID;

public interface SessaoService {
  UUID computeVote(String cpf, Integer idSessao, ValorVoto valorVoto) throws VotoNaoAceitoException, ItemNaoEncontradoException;

  Sessao createSession(Sessao sessao) throws ItemDuplicatedException;
  List<Sessao> listar();
  Sessao obter(Integer id) throws ItemNaoEncontradoException;

}
