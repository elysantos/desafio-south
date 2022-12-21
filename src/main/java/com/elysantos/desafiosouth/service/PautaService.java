package com.elysantos.desafiosouth.service;

import com.elysantos.desafiosouth.model.domain.Pauta;
import com.elysantos.desafiosouth.model.exception.ItemNaoEncontradoException;
import java.util.Collection;

public interface PautaService {
  Collection<Pauta> listar();
  Pauta obter(Integer id) throws ItemNaoEncontradoException;
  Pauta criar(Pauta pauta);
  Pauta apagar(Integer id) throws ItemNaoEncontradoException;
  Pauta atualizar(Pauta pauta) throws ItemNaoEncontradoException;
}
