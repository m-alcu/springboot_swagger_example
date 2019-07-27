package guru.springframework.config;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecConfig extends WebSecurityConfigurerAdapter {
    
    @Value("${spring.datasource.schema}")
    private String schema;
    @Value("${flyway.validateOnMigrate:true}")
    private Boolean validateOnMigrate;    

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/","/swagger-resources").permitAll();
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }

    
    /**
     * Flyway
     *
     * @param dataSource the data source
     * @return the flyway
     */
    @ConditionalOnProperty(prefix = "flyway", name = "enabled", matchIfMissing = false)
    @Bean(initMethod = "migrate")
    public Flyway flyway(DataSource dataSource) {
        Flyway flyway = Flyway.configure().dataSource(dataSource).validateOnMigrate(validateOnMigrate).schemas(schema).load();
        return flyway;
    }

}