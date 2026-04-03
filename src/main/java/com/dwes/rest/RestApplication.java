package com.dwes.rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				description = "Una APE REST sobre productos",
				version ="1.0",
				contact = @Contact(
						email = "hsb68012@educastur.es",
						name = "Laura"
				),
				license = @License(name = "CC BY"),
				title ="Una API sobre productos"

		)
)
@SpringBootApplication
public class RestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

}
