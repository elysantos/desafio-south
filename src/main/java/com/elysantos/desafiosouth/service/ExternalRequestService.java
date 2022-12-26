package com.elysantos.desafiosouth.service;

import com.elysantos.desafiosouth.model.domain.Associado;
import com.elysantos.desafiosouth.model.exception.ItemNaoEncontradoException;

public interface ExternalRequestService {
  Associado validaCPF(String cpf) throws ItemNaoEncontradoException;
}
