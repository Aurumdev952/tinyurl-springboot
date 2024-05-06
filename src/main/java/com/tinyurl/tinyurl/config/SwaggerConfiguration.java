package com.tinyurl.tinyurl.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Aurumdev952",
                        email = "benjamugi20072@gmail.com",
                        url = "https://www.google.com"
                ),
                description = "tinyurl clone",
                title = "Tinyclone apis",
                version = "1.0",
                license = @License(
                        name = "MIT",
                        url = "https://opensource.org/license/mit"
                ),
                termsOfService = "Feel free to clone and adapt this code for your own Spring Boot 3 project."
        ), servers = {
        @Server(
                description = "Local deployment  environment",
                url = "http://localhost:8000"
        ),
}
)
public class SwaggerConfiguration {
}