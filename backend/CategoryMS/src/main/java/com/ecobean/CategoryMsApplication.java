package com.ecobean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = { "classpath:messages.properties" })
public class CategoryMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategoryMsApplication.class, args);
	}

}
