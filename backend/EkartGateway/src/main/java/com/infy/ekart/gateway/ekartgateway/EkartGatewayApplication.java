package com.infy.ekart.gateway.ekartgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class EkartGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EkartGatewayApplication.class, args);
	}

	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
	    return factory -> factory.configureDefault(
	    		id -> new Resilience4JConfigBuilder(id)
	            .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults()).build());
	            
	}
	
	@Bean
	public KeyResolver useKeyResolver() {
		return exchange->Mono.just("userKey");
	}

}
