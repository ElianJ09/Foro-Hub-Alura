package com.alura.foro_hub.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class springDocConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("Foro-Hub-Alura API")
                        .description("Rest API for the Forum-Hub-Alura site where we can create discussions with Topics, and create users, as well as implemented security")
                        .contact(new Contact()
                                .name("Elian Rodriguez")
                                .email("rodriguez.elian.j09@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://foro.hub.Alura.com/api/licence")));
    }

    @Bean
    public String message(){
        String message = "Bearer is working";
        System.out.println(message);
        return message;
    }
}
