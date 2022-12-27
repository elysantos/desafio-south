package com.elysantos.desafiosouth.repository;

import com.elysantos.desafiosouth.model.domain.Sessao;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SessaoRepository {
  void insert(@Param("sessao") Sessao sessao);
  void update(@Param("id") String id, @Param("sessao") Sessao sessao);
  void delete(@Param("id") String id);
  List<Sessao> findAll();
  Sessao findById(@Param("id") String id);


  List<Sessao> findByStatusNotClosedAndVotingClosed();
}
