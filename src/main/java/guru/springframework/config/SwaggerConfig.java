package guru.springframework.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.LocaleResolver;

import guru.springframework.filter.SmartLocaleResolver;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("guru.springframework.controllers"))
            //          .paths(regex("/product.*"))
            .build()
            .globalOperationParameters(getImplicitParameters())
            .apiInfo(metadata());
    }

    private List<Parameter> getImplicitParameters() {
        List<Parameter> parameters = new ArrayList<>();
        // Accept-Language Header
        parameters.add(new ParameterBuilder()
            .name(HttpHeaders.ACCEPT_LANGUAGE)
            .description("Language to use for internationalization")
            .modelRef(new ModelRef("string"))
            .parameterType("header")
            .defaultValue(Locale.ENGLISH.getLanguage())
            .required(false)
            .build());
        // Authorization Header
        parameters.add(new ParameterBuilder()
            .name(HttpHeaders.AUTHORIZATION)
            .description("Bearer authorization token")
            .modelRef(new ModelRef("string"))
            .parameterType("header")
            .required(false)
            .build());
        return parameters;
    }

    /**
     * Metadata.
     *
     * @return api information
     */
    @Bean
    public ApiInfo metadata() {
        return new ApiInfoBuilder()
            .title("mi servicio")
            .description("REST API service providing internal and external functionalities to be used by the back-end web site..\n " +
                "\tThis application will have several modules for independent management tasks.\n ")
            .version("1.00")
            .contact(new Contact("Martin Alcubierre", "http://some.url", "martin.alcubierre@gmail.com"))
            .build();
    }

    /**
     * Configuration of the application Locale Resolver to use our custom {@link SmartLocaleResolver}
     *
     * @return a new instance of {@link SmartLocaleResolver}
     */
    @Bean(name = "localeResolver")
    public LocaleResolver localeResolver() {
        return new SmartLocaleResolver();
    }

}
