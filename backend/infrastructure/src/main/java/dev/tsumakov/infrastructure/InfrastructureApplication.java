package dev.tsumakov.infrastructure;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Web Portfolio Application",
        version = "v1.0",
        description = "Web Portfolio application API",
        contact = @Contact(url = "https://github.com/No1Evil/web-portfolio", name = "Fjodor")
    )
)
public class InfrastructureApplication {

  public static void main(String[] args) {
    SpringApplication.run(InfrastructureApplication.class, args);
  }
}
