package com.elysantos.desafiosouth.repository;

import com.elysantos.desafiosouth.model.domain.Voto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface VotoRepository {
  void insert(Voto voto);
  boolean findByCpfAndSessao(String cpf, Integer sessao);
}
