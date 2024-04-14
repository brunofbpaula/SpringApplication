package com.blackout.university.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("University Course Management API")
                        .version("1.0")
                        .description("RESTFUL API for courses and its enrollments management. ")
                        .termsOfService("http://swagger.io/terms/")
                        .contact(new Contact()
                                .name("Development Team")
                                .url("http://www.fiap.com.br/support")
                                .email("suporte@fiap.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
