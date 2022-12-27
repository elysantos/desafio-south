package com.elysantos.desafiosouth.service.implementation;

import com.elysantos.desafiosouth.model.domain.Pauta;
import com.elysantos.desafiosouth.model.exception.ItemDuplicatedException;
import com.elysantos.desafiosouth.model.exception.ItemNaoEncontradoException;
import com.elysantos.desafiosouth.repository.PautaRepository;
import com.elysantos.desafiosouth.service.PautaService;
import java.util.Collection;
import java.util.UUID;
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
    try{
      UUID uuid = UUID.fromString(id);
      Pauta pauta = pautaRepository.findById(uuid.toString());
      if (pauta == null) {
        throw new ItemNaoEncontradoException(TYPE, uuid.toString());
      }
      return pauta;
    }catch (IllegalArgumentException ex){
      throw new ItemNaoEncontradoException(id, TYPE);
    }

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
  public Pauta atualizar(String id, Pauta pauta) throws ItemNaoEncontradoException {
    try{
      UUID uuid = UUID.fromString(id);
      Pauta pautaAtual = this.obter(uuid.toString());
      pautaRepository.update(pautaAtual.getId().toString(), pauta);
      return pauta;
    }catch (IllegalArgumentException ex){
      throw new ItemNaoEncontradoException(id, TYPE);
    }

  }
}
