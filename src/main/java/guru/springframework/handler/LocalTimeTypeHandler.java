package guru.springframework.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;
import java.time.LocalTime;

/**
 * Example with LocalTime java8 Handler
 */
@MappedTypes(LocalTime.class)
public class LocalTimeTypeHandler implements TypeHandler<LocalTime> {

    @Override
    public void setParameter(PreparedStatement ps, int index, LocalTime parameter, JdbcType jdbcType) throws SQLException {
        final LocalTime time = parameter;
        if (time == null) {
            ps.setTimestamp(index, null);
        } else {
            ps.setTime(index, Time.valueOf(parameter));
        }
    }

    @Override
    public LocalTime getResult(ResultSet rs, String columnName) throws SQLException {
        final Time time = rs.getTime(columnName);
        if (time == null) {
            return null;
        }
        return time.toLocalTime();
    }

    @Override
    public LocalTime getResult(ResultSet rs, int columnIndex) throws SQLException {
        final Time time = rs.getTime(columnIndex);
        if (time == null) {
            return null;
        }
        return time.toLocalTime();
    }

    @Override
    public LocalTime getResult(CallableStatement cs, int columnIndex) throws SQLException {
        final Time time = cs.getTime(columnIndex);
        if (time == null) {
            return null;
        }
        return time.toLocalTime();
    }
}
