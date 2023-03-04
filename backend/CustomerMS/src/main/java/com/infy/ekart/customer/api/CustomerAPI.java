package com.infy.ekart.customer.api;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infy.ekart.customer.dto.CartProductDTO;
import com.infy.ekart.customer.dto.CustomerCartDTO;
import com.infy.ekart.customer.dto.CustomerDTO;
import com.infy.ekart.customer.dto.ProductDTO;
import com.infy.ekart.customer.exception.EKartCustomerException;
import com.infy.ekart.customer.service.CustomerService;


//Add the missing annotations


@CrossOrigin
@RequestMapping(value = "/customer-api")
@RestController
@Validated
public class CustomerAPI {

    @Autowired
	private CustomerService customerService;
	
	
    @Autowired
	private RestTemplate template;

	
    @Autowired
	private Environment environment;

	static Log logger = LogFactory.getLog(CustomerAPI.class);

	

	@PostMapping(value = "/login")
	public ResponseEntity<CustomerDTO> authenticateCustomer(@Valid @RequestBody CustomerDTO customerDTO)
			throws EKartCustomerException {

		logger.info("CUSTOMER TRYING TO LOGIN, VALIDATING CREDENTIALS. CUSTOMER EMAIL ID: " + customerDTO.getEmailId());
		CustomerDTO customerDTOFromDB = customerService.authenticateCustomer(customerDTO.getEmailId(),
				customerDTO.getPassword());
		logger.info("CUSTOMER LOGIN SUCCESS, CUSTOMER EMAIL : " + customerDTOFromDB.getEmailId());
		return new ResponseEntity<>(customerDTOFromDB, HttpStatus.OK);
	}

	@PostMapping(value = "/register")
	public ResponseEntity<String> registerCustomer(@Valid @RequestBody CustomerDTO customerDTO)
			throws EKartCustomerException {

		logger.info("CUSTOMER TRYING TO REGISTER. CUSTOMER EMAIL ID: " + customerDTO.getEmailId());
		String registeredWithEmailID = customerService.registerNewCustomer(customerDTO);
		registeredWithEmailID = environment.getProperty("CustomerAPI.CUSTOMER_REGISTRATION_SUCCESS")
				+ registeredWithEmailID;
		return new ResponseEntity<>(registeredWithEmailID, HttpStatus.OK);
	}
	
	@PutMapping(value = "/customer/{customerEmailId:.+}/updateProfile/")
	public ResponseEntity<String> updateProfile(@PathVariable("customerEmailId") String customerEmailId, @RequestBody CustomerDTO customerDTO)
			throws EKartCustomerException { 
		customerService.updateProfile(customerDTO);
		String message = environment.getProperty("CustomerAPI.CUSTOMER_DETAILS_UPDATION_SUCCESS");
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@PutMapping(value = "/customer/{customerEmailId:.+}/updatePassword/")
	public ResponseEntity<String> changePassword(@PathVariable("customerEmailId") String customerEmailId, @RequestBody String currentPassword, @RequestBody String newPassword)
			throws EKartCustomerException { 
		customerService.changePassword(customerEmailId, currentPassword, newPassword);
		String message = environment.getProperty("CustomerAPI.CUSTOMER_PASSWORD_CHANGE_SUCCESS");
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@PutMapping(value = "/customer/{customerEmailId:.+}/address/")
	public ResponseEntity<String> updateShippingAddress(
			@Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+", message = "{invalid.email.format}")  String customerEmailId,
			@RequestBody String address) throws EKartCustomerException {

		customerService.updateShippingAddress(customerEmailId, address);
		String modificationSuccessMsg = environment.getProperty("CustomerAPI.UPDATE_ADDRESS_SUCCESS");
		return new ResponseEntity<>(modificationSuccessMsg, HttpStatus.OK);

	}

	@DeleteMapping(value = "/customer/{customerEmailId:.+}")
	public ResponseEntity<String> deleteShippingAddress(
			@Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+", message = "{invalid.email.format}") @PathVariable("customerEmailId") String customerEmailId)
			throws EKartCustomerException {

		customerService.deleteShippingAddress(customerEmailId);
		String modificationSuccessMsg = environment.getProperty("CustomerAPI.CUSTOMER_ADDRESS_DELETED_SUCCESS");
		return new ResponseEntity<>(modificationSuccessMsg, HttpStatus.OK);

	}

	@PostMapping(value = "/customercarts/add-product")
	public ResponseEntity<String> addProductToCart(@Valid @RequestBody CustomerCartDTO customerCartDTO)
			throws EKartCustomerException {

		customerService.getCustomerByEmailId(customerCartDTO.getCustomerEmailId());

		for (CartProductDTO cartProductDTO : customerCartDTO.getCartProducts()) {
			template.getForEntity("https:ProductMS/product/"+cartProductDTO.getProduct().getProductId(), ProductDTO.class);
			// We are calling the product API using hard-coded URI
			// Replace this call with the appropriate MS name
			// Product API is upscaled (available in 2 numbers). Hence, use load balanced
			// template to make call to the Product API
		}
		
		ResponseEntity<String> productAddedToCartMessage = template.postForEntity("https:CustomerCartMS/products", customerCartDTO, String.class);
		
		// We are calling the Cart API using hard-coded URI
		// Replace this call with the appropriate MS name
		// CartMS is not an upscaled one (available in 1 number) , still load-balanced
		// rest template should be used here to make the call
		// Don't create and autowire a normal rest template because load balanced
		// template is already in config file
		
		return productAddedToCartMessage;
	}

}
