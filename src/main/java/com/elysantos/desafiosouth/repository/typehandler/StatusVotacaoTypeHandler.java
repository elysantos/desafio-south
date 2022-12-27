package com.elysantos.desafiosouth.repository.typehandler;

import com.elysantos.desafiosouth.model.domain.StatusVotacao;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

@NoArgsConstructor
public class StatusVotacaoTypeHandler extends BaseTypeHandler<StatusVotacao> {
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, StatusVotacao parameter, JdbcType jdbcType) throws SQLException {
    ps.setInt(i, parameter.getId());
  }

  @Override
  public StatusVotacao getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return rs.wasNull() ? null : StatusVotacao.fromId(rs.getInt(columnName));
  }

  @Override
  public StatusVotacao getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return rs.wasNull() ? null : StatusVotacao.fromId(rs.getInt(columnIndex));
  }

  @Override
  public StatusVotacao getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return cs.wasNull() ? null : StatusVotacao.fromId(cs.getInt(columnIndex));
  }
}
