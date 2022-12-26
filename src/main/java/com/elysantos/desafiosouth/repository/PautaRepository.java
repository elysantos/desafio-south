package com.elysantos.desafiosouth.repository;

import com.elysantos.desafiosouth.model.domain.Pauta;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface PautaRepository {
  void delete(@Param("id") String id);

  void insert(@Param("pauta")Pauta pauta);

  void update(@Param("id") String id, @Param("pauta") Pauta pauta);

  List<Pauta> findAll();

  Pauta findById(@Param("id") String id);
}
