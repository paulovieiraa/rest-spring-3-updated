package br.com.pvprojects.restspring3.core.springdoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .info(
            new Info()
                .title("API REST - Spring Boot 3.4.3")
                .description("Conceitos atualizados com Spring Boot 3.4.3")
                .version("1.0.0")
                .contact(new Contact().name("Dev").email("EMAIL@gmail.com")));
    //                .addSecurityItem(new SecurityRequirement().addList("ApiKeyAuth", new
    // ArrayList<>()));
  }
}
