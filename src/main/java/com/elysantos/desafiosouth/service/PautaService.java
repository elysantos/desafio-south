package com.elysantos.desafiosouth.service;

import com.elysantos.desafiosouth.model.domain.Pauta;
import com.elysantos.desafiosouth.model.exception.ItemDuplicatedException;
import com.elysantos.desafiosouth.model.exception.ItemNaoEncontradoException;
import java.util.Collection;

public interface PautaService {
  Collection<Pauta> listar();
  Pauta obter(String id) throws ItemNaoEncontradoException;
  Pauta criar(Pauta pauta) throws ItemDuplicatedException;
  Pauta apagar(String id) throws ItemNaoEncontradoException;
  Pauta atualizar(String id, Pauta pauta) throws ItemNaoEncontradoException;
}
