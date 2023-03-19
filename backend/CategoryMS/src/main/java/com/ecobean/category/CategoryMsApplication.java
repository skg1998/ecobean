package com.ecobean.category;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
//@EnableJpaRepositories
//@PropertySource(value = { "classpath:messages.properties" })
public class CategoryMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategoryMsApplication.class, args);
	}

}
