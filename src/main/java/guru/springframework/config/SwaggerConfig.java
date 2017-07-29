package guru.springframework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
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
            .apiInfo(metadata());
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
}
