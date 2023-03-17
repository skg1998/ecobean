package com.infy.ekart.gateway.ekartgateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class FallbackController {

    @GetMapping("/fallbackCustomerCartHandler")
	public ResponseEntity<String> fallbackCustomerCartHandler(){
		return new ResponseEntity<String>("Cart Service is down",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/fallbackCustomerHandler")
	public ResponseEntity<String> fallbackCustomerHandler(){
		return new ResponseEntity<String>("Customer Service is down",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/fallbackPaymentHandler")
	public ResponseEntity<String> fallbackPaymentHandler(){
		return new ResponseEntity<String>("Payment Service is down",HttpStatus.INTERNAL_SERVER_ERROR);
	}

    @GetMapping("/fallbackProductHandler")
	public ResponseEntity<String> fallbackProductHandler(){
		return new ResponseEntity<String>("Product Service is down",HttpStatus.INTERNAL_SERVER_ERROR);
	}

    @GetMapping("/fallbackNotificationHandler")
	public ResponseEntity<String> fallbackNotificationHandler(){
		return new ResponseEntity<String>("Notification Service is down",HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
