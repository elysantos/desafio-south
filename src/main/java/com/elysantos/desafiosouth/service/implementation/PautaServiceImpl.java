package com.elysantos.desafiosouth.service.implementation;

import com.elysantos.desafiosouth.model.domain.Pauta;
import com.elysantos.desafiosouth.model.exception.ItemNaoEncontradoException;
import com.elysantos.desafiosouth.repository.PautaRepository;
import com.elysantos.desafiosouth.service.PautaService;
import java.util.Collection;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PautaServiceImpl implements PautaService {

  private final PautaRepository pautaRepository;

  @Override
  public Collection<Pauta> listar() {
    return Collections.emptyList();
  }

  @Override
  public Pauta obter(Integer id) throws ItemNaoEncontradoException {
    return new Pauta();
  }

  @Override
  public Pauta criar(Pauta pauta) {
    return null;
  }

  @Override
  public Pauta apagar(Integer id) throws ItemNaoEncontradoException {
    Pauta pauta = this.obter(id);
    pautaRepository.delete(id);
    return pauta;
  }

  @Override
  public Pauta atualizar(Pauta pauta) throws ItemNaoEncontradoException {
    return null;
  }
}
