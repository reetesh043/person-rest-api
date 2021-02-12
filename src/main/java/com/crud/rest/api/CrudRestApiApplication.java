package com.crud.rest.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudRestApiApplication.class, args);
    }


	/*@Bean
	public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
		return new OpenAPI().info(new Info().title("Foobar API")
				.version(appVersion)
				.description("This is a sample Foobar server created using springdocs - a library for OpenAPI 3 with spring boot.")
				.termsOfService("http://swagger.io/terms/")
				.license(new License().name("Apache 2.0")
						.url("http://springdoc.org")));
	}*/
}
