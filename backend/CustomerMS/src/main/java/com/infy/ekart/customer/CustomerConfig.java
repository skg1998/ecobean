package com.infy.ekart.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomerConfig {

	private RestTemplate template = new RestTemplate();

	@Bean
	// Make this as load-balanced rest template
	public RestTemplate restTemplate() {
		// return template object created above
		return this.template;
	}

}
