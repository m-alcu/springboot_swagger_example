package guru.springframework.config;

import guru.springframework.handler.LocalDateTimeTypeHandler;
import guru.springframework.handler.LocalDateTypeHandler;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * MyBatis SQL session factory configuration.
 */
@Configuration
@MapperScan({
    "guru.springframework.dao"
})
public class MyBatisConfiguration {

    /** The data source. */
    @Resource(name = "dataSource")
    private DataSource dataSource;

    /**
     * Get bean data source transaction manager.
     *
     * @return data source transaction manager
     */
    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * Get the SQL session factory for MyBatis configuration.
     *
     * @return SQL session factory bean of MyBatis
     * @throws Exception exception configuring SQL Session Factory
     */
    @Bean
    @Primary
    @SuppressWarnings("squid:S00112")
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setTypeHandlersPackage(LocalDateTimeTypeHandler.class.getPackage().getName());
        sqlSessionFactoryBean.setTypeHandlersPackage(LocalDateTypeHandler.class.getPackage().getName());
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        sqlSessionFactoryBean.setDatabaseIdProvider(databaseIdProvider());
        final SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        sqlSessionFactory.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
        return sqlSessionFactoryBean;
    }

    /**
     * Get the SQL session factory for MyBatis configuration.
     *
     * @return SQL session factory bean of MyBatis
     * @throws Exception exception configuring SQL Session Factory
     */
    @Bean
    @SuppressWarnings("squid:S00112")
    public SqlSessionFactoryBean sqlBatchSessionFactory() throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setTypeHandlersPackage(LocalDateTimeTypeHandler.class.getPackage().getName());
        sqlSessionFactoryBean.setTypeHandlersPackage(LocalDateTypeHandler.class.getPackage().getName());
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        sqlSessionFactoryBean.setDatabaseIdProvider(databaseIdProvider());
        final SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        sqlSessionFactory.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
        return sqlSessionFactoryBean;
    }

    /**
     * Supported DB vendors used in the application (dev: hsql, prod: postgresql)
     *
     * @return the DB vendor provider
     */
    @Bean
    public VendorDatabaseIdProvider databaseIdProvider() {
        Properties properties = new Properties();
        properties.put("PostgreSQL", "postgresql");
        properties.put("HSQL Database Engine", "hsql");

        VendorDatabaseIdProvider vendorDatabaseIdProvider = new VendorDatabaseIdProvider();
        vendorDatabaseIdProvider.setProperties(properties);
        return vendorDatabaseIdProvider;
    }

}
