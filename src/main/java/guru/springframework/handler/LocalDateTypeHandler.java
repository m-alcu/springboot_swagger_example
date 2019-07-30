package guru.springframework.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;
import java.time.LocalDate;

/**
 * Example with LocalDate java8 Handler.
 */
@MappedTypes(LocalDate.class)
public class LocalDateTypeHandler implements TypeHandler<LocalDate> {

    /** The Constant LOCAL_DATE_HANDLER_LOCATION. */
    public static final String LOCAL_DATE_HANDLER_LOCATION = "com.hotelbeds.packagebackofficeservice.db.handler.LocalDateTypeHandler";

    /*
     * (non-Javadoc)
     *
     * @see org.apache.ibatis.type.TypeHandler#setParameter(java.sql.PreparedStatement, int, java.lang.Object, org.apache.ibatis.type.JdbcType)
     */
    @Override
    public void setParameter(final PreparedStatement ps, final int index, final LocalDate parameter, final JdbcType jdbcType) throws SQLException {
        final LocalDate date = parameter;
        if (date == null) {
            ps.setDate(index, null);
        } else {
            ps.setDate(index, Date.valueOf(parameter));
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.ResultSet, java.lang.String)
     */
    @Override
    public LocalDate getResult(final ResultSet rs, final String columnName) throws SQLException {
        final Date date = rs.getDate(columnName);
        if (date == null) {
            return null;
        }

        return date.toLocalDate();

    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.ResultSet, int)
     */
    @Override
    public LocalDate getResult(final ResultSet rs, final int columnIndex) throws SQLException {
        final Date date = rs.getDate(columnIndex);
        if (date == null) {
            return null;
        }
        return date.toLocalDate();

    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.CallableStatement, int)
     */
    @Override
    public LocalDate getResult(final CallableStatement cs, final int columnIndex) throws SQLException {
        final Date date = cs.getDate(columnIndex);
        if (date == null) {
            return null;
        }
        return date.toLocalDate();

    }
}
