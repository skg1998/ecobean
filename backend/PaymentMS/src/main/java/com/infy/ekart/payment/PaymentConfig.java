package com.infy.ekart.payment;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PaymentConfig {

	private RestTemplate template = new RestTemplate();

	@Bean
	// Make this as load-balanced rest template
	@LoadBalanced
	public RestTemplate restTemplate() {
		// return template object created above
		return this.template;
	}
}