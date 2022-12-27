package com.elysantos.desafiosouth.repository.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

@NoArgsConstructor
public class LocalDateTimeTypeHandler extends BaseTypeHandler<LocalDateTime> {

  public void setNonNullParameter(PreparedStatement preparedStatement, int i, LocalDateTime localDateTime, JdbcType jdbcType) throws SQLException {
    preparedStatement.setTimestamp(i, Timestamp.valueOf(localDateTime));
  }

  public LocalDateTime getNullableResult(ResultSet rs, String collumnName) throws SQLException {
    return rs.getTimestamp(collumnName) == null ? null : rs.getTimestamp(collumnName).toLocalDateTime();
  }

  public LocalDateTime getNullableResult(ResultSet rs, int collumnIndex) throws SQLException {
    return rs.getTimestamp(collumnIndex) == null ? null : rs.getTimestamp(collumnIndex).toLocalDateTime();
  }

  public LocalDateTime getNullableResult(CallableStatement cs, int collumnIndex) throws SQLException {
    return cs.getTimestamp(collumnIndex) == null ? null : cs.getTimestamp(collumnIndex).toLocalDateTime();
  }

}
