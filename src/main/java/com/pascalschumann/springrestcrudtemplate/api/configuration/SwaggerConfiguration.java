package com.pascalschumann.springrestcrudtemplate.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Enables and configures the swagger documentation
 */
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    private final BuildProperties buildProperties;

    @Autowired
    public SwaggerConfiguration(final BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                        .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                        .paths(PathSelectors.any()).build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        final String serviceName = buildProperties.getName();

        return new ApiInfoBuilder().title(serviceName).description(
                        "Description: https://github.com/pascalschumann/spring-crud-template/blob/master/README.md")
                        .license("License: Apache License 2.0")
                        .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                        .termsOfServiceUrl("").version("1.0.0")
                        .contact(new Contact("Bug report",
                                        "https://github.com/pascalschumann/spring-crud-template/issues/new",
                                        ""))
                        .build();
    }
}
