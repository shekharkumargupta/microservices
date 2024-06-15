package com.trantor.learning.cartservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info (
                contact = @Contact(
                        name = "Shekhar",
                        email = "shekhar@example.com",
                        url = "https://shekhar.com"
                ),
                description = "Learning Open API documentation",
                title = "Microservices Learning Specification",
                version = "1.0",
                license = @License(
                        name = "OpenSource License",
                        url = "https://opensource.com"
                ),
                termsOfService = "You can use it for your own practice but not for production"
        )
)
public class OpenApiConfig {
}
