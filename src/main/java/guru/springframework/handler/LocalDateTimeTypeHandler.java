package guru.springframework.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * Example with LocalDateTime java8 Handler.
 */
@MappedTypes(LocalDateTime.class)
public class LocalDateTimeTypeHandler implements TypeHandler<LocalDateTime> {

    /** The Constant LOCAL_DATE_TIME_HANDLER_LOCATION. */
    public static final String LOCAL_DATE_TIME_HANDLER_LOCATION = "com.hotelbeds.packagebackofficeservice.db.handler.LocalDateTimeTypeHandler";

    /*
     * (non-Javadoc)
     *
     * @see org.apache.ibatis.type.TypeHandler#setParameter(java.sql.PreparedStatement, int, java.lang.Object, org.apache.ibatis.type.JdbcType)
     */
    @Override
    public void setParameter(final PreparedStatement ps, final int index, final LocalDateTime parameter, final JdbcType jdbcType)
        throws SQLException {
        final LocalDateTime datetime = parameter;
        if (datetime == null) {
            ps.setTimestamp(index, null);
        } else {
            ps.setTimestamp(index, Timestamp.valueOf(parameter));
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.ResultSet, java.lang.String)
     */
    @Override
    public LocalDateTime getResult(final ResultSet rs, final String columnName) throws SQLException {
        final Timestamp timestamp = rs.getTimestamp(columnName);
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.ResultSet, int)
     */
    @Override
    public LocalDateTime getResult(final ResultSet rs, final int columnIndex) throws SQLException {
        final Timestamp timestamp = rs.getTimestamp(columnIndex);
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();

    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.CallableStatement, int)
     */
    @Override
    public LocalDateTime getResult(final CallableStatement cs, final int columnIndex) throws SQLException {
        final Timestamp timestamp = cs.getTimestamp(columnIndex);
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();

    }
}
