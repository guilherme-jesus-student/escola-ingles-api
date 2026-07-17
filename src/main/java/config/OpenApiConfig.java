package com.escola.escolainglesapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Escola de Inglês")
                        .version("v1")
                        .description("Documentação dos endpoints da API da Escola de Inglês (CRUD de Professores, etc.)"));
    }
}