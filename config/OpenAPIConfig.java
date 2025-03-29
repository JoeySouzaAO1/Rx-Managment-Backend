package com.surecostproject.takehome.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {
    
    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server()
            .url("http://localhost:8080")
            .description("Development server");

        Contact contact = new Contact()
            .name("Drug Management API Team")
            .email("team@example.com");

        Info info = new Info()
            .title("Drug Management API")
            .version("1.0")
            .contact(contact)
            .description("This API exposes endpoints to manage drugs inventory.")
            .termsOfService("https://www.example.com/terms")
            .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"));

        return new OpenAPI()
            .info(info)
            .servers(List.of(devServer));
    }
}
