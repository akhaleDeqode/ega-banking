package com.ega.banking;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "EGA Bank",
				version = "1.0.0"
		)
)
public class EgaBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(EgaBankApplication.class, args);
	}

}
