package com.example.demo.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI() {
        List<Server> servers = new ArrayList<>();
        servers.add(new Server().url("http://localhost:8080"));
        SecurityRequirement securityRequirement = new SecurityRequirement();
        securityRequirement.addList("Bearer");
        return new OpenAPI().servers(servers).security(List.of(securityRequirement));
    }
}
