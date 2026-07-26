package com.libracore.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI libraryApi() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("LibraCore API")
                                .version("1.0.0")
                                .description("Library Management System REST API")
                                .contact(
                                        new Contact()
                                                .name("Meghana")
                                                .email("your-email@example.com")));
    }
}