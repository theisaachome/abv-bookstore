package com.abv.bookstore.pos;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "ABV BookStore REST API",
                description = "Spring boot ABV bookstore REST API documentation",
                version = "1.0",
                contact = @Contact(
                        name = "isaac home",
                        email = "isaachome.burma@gmail.com",
                        url = "www.highway65.com"
                ),
                license = @License(
                        url = "www.mit@llicense.com",
                        name = "Apache  2.0"
                ),
                summary = ""
        ),
        externalDocs = @ExternalDocumentation(
                description = "Spring boot ABV bookstore REST API",
                url = "www.github.com/theisaachome/abvbookstoreapi"
        )
)
public class AbvBookstoreApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AbvBookstoreApiApplication.class, args);
    }

}
