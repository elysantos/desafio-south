package com.elysantos.desafiosouth.repository;

import com.elysantos.desafiosouth.model.domain.Voto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface VotoRepository {
  void insert(@Param("voto") Voto voto);
  boolean findByCpfAndSessao(@Param("cpf") String cpf, @Param("sessao") String sessao);
}
