package com.spring.test.apirest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Rest API",
                version = "1.0",
                description = "This is a demo project for a Rest Api using Spring Boot, H2 database and Swagger documentation."
        )
)
@SpringBootApplication
public class ApiRestApplication {

    public static final String HTTP_200 = "200";

    public static void main(String[] args) {
        SpringApplication.run(ApiRestApplication.class, args);
    }

}
