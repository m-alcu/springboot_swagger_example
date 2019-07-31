package guru.springframework.configuration;

import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"guru.springframework.entity"})
@EnableJpaRepositories(basePackages = {"guru.springframework.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {
       
    /**
     * Data source.
     *
     * @return the data source
     */
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
            .addScript("/sql/setSyntax.sql")
            .addScript("/sql/db-schema.sql")
            .addScript("/sql/dml.sql")
            .build();
    }

    @Bean
    @Primary
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {

        //Session && datasource
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        //Configuration
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        sqlSessionFactoryBean.setDatabaseIdProvider(databaseIdProvider());

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        sqlSessionFactory.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);

        return sqlSessionFactoryBean;
    }

    @Bean
    public VendorDatabaseIdProvider databaseIdProvider() {
        Properties properties = new Properties();
        properties.put("PostgreSQL", "postgresql");
        properties.put("HSQL Database Engine", "hsql");

        VendorDatabaseIdProvider vendorDatabaseIdProvider = new VendorDatabaseIdProvider();
        vendorDatabaseIdProvider.setProperties(properties);
        return vendorDatabaseIdProvider;
    }


    @Bean
    public PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("application.yml"));
        propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
        return propertySourcesPlaceholderConfigurer;
    }
}
