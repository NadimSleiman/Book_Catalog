package Foo_Training.BookCatalog;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class BookCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookCatalogApplication.class, args);
	}

}
