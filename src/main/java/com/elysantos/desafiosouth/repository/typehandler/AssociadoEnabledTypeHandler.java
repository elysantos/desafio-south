package com.elysantos.desafiosouth.repository.typehandler;

import com.elysantos.desafiosouth.model.domain.AssociadoEnabled;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class AssociadoEnabledTypeHandler extends BaseTypeHandler<AssociadoEnabled> {
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, AssociadoEnabled parameter, JdbcType jdbcType) throws SQLException {
    ps.setBoolean(i, parameter.getBoolStatus());
  }

  @Override
  public AssociadoEnabled getNullableResult(ResultSet rs, String columnName) throws SQLException {

    return rs.wasNull() ? null : AssociadoEnabled.valueFromBool(rs.getBoolean(columnName));
  }

  @Override
  public AssociadoEnabled getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return rs.wasNull() ? null : AssociadoEnabled.valueFromBool(rs.getBoolean(columnIndex));
  }

  @Override
  public AssociadoEnabled getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return cs.wasNull() ? null : AssociadoEnabled.valueFromBool(cs.getBoolean(columnIndex));
  }
}
