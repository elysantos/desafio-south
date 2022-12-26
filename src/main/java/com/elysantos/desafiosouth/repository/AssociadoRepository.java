package com.elysantos.desafiosouth.repository;

import com.elysantos.desafiosouth.model.domain.Associado;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AssociadoRepository {
  Associado findByCPF(String cpf);
  void insert(Associado associado);
}
