package com.cts.fsd.userTask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Swagger 2 configuration for the Spring REST web services.
 * It describes and documents RESTful APIs.
 * Swagger UI is used for user interactions with the Swagger-generated API resources.
 *
 * REST API docs - http://<server>:<port>/v2/api-docs
 * Swagger UI - http://<server>:<port>/swagger-ui.html
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cts.fsd.userTask.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(new ApiInfo(
                        "PAS DOC REST Services API",
                        "",
                        "v1",
                        "",
                        null,
                        "",
                        "",
                        Collections.emptyList()
                ))
                .tags(new Tag("documents", ""));
    }

}
