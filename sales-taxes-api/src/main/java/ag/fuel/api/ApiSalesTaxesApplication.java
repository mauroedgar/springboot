/*
 * Copyright (c) 2021 Fuel.ag.
 * All rights reserved.
 * https://fuel.ag
 */
package ag.fuel.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * @author Mauro Sousa
 */

@Configuration
@PropertySource("classpath:application.properties")
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableSwagger2
public class ApiSalesTaxesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiSalesTaxesApplication.class, args);
    }

    @Bean
    public Docket swaggerSettings() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //Só aparecem os métodos com anotação
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .useDefaultResponseMessages(true);
    }

    private ApiInfo apiInfo() {
        final String name = "Mauro Edgar Sousa";
        final String url = "https://fuel.ag";
        final String email = "mauro.sousa@fuel.ag";

        Contact myContact = new Contact(name, url, email);

        return new ApiInfoBuilder()
                .title("Backend Software Engineer - Exam")
                .description("This is a Restful API Service to get the tax rate of products based on some premisses")
                .termsOfServiceUrl("https://fuel.ag")
                .contact(myContact)
                .version("1.0.1")
                .build();
    }
}
