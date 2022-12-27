package com.elysantos.desafiosouth.repository.typehandler;

import com.elysantos.desafiosouth.model.domain.ValorVoto;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

@NoArgsConstructor
public class ValorVotoTypeHandler extends BaseTypeHandler<ValorVoto> {
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, ValorVoto parameter, JdbcType jdbcType) throws SQLException {
    ps.setBoolean(i, parameter.getValor());
  }

  @Override
  public ValorVoto getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return rs.wasNull() ? null : ValorVoto.valueFromBool(rs.getBoolean(columnName));
  }

  @Override
  public ValorVoto getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return rs.wasNull() ? null : ValorVoto.valueFromBool(rs.getBoolean(columnIndex));
  }

  @Override
  public ValorVoto getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return cs.wasNull() ? null : ValorVoto.valueFromBool(cs.getBoolean(columnIndex));
  }
}
