package com.elysantos.desafiosouth.service.implementation;

import com.elysantos.desafiosouth.model.domain.Pauta;
import com.elysantos.desafiosouth.model.exception.ItemDuplicatedException;
import com.elysantos.desafiosouth.model.exception.ItemNaoEncontradoException;
import com.elysantos.desafiosouth.repository.PautaRepository;
import com.elysantos.desafiosouth.service.PautaService;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PautaServiceImpl implements PautaService {

  private final        PautaRepository pautaRepository;
  private static final String          TYPE = "Pauta";

  @Override
  public Collection<Pauta> listar() {
    return pautaRepository.findAll();
  }

  @Override
  public Pauta obter(String id) throws ItemNaoEncontradoException {
    Pauta pauta = pautaRepository.findById(id);
    if (pauta == null) {
      throw new ItemNaoEncontradoException(TYPE, String.valueOf(id));
    }
    return pauta;
  }

  @Override
  public Pauta criar(Pauta pauta) throws ItemDuplicatedException {
    try {
      pautaRepository.insert(pauta);
    }catch (DataIntegrityViolationException dve){
      log.error(dve.getMessage());
      throw new ItemDuplicatedException(TYPE);
    }
    return pauta;
  }

  @Override
  public Pauta apagar(String id) throws ItemNaoEncontradoException {
    Pauta pauta = this.obter(id);
    pautaRepository.delete(id);
    return pauta;
  }

  @Override
  public Pauta atualizar(Pauta pauta) throws ItemNaoEncontradoException {
    Pauta pautaAtual = this.obter(pauta.getId().toString());
    pautaRepository.update(pautaAtual.getId().toString(), pauta);
    return pauta;
  }
}
