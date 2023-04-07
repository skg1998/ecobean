package com.infy.ekart.payment.api;

import java.security.NoSuchAlgorithmException;
import java.util.List;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infy.ekart.payment.dto.CardDTO;
import com.infy.ekart.payment.dto.TransactionDTO;
import com.infy.ekart.payment.exception.EKartPaymentException;
import com.infy.ekart.payment.exception.PayOrderFallbackException;
import com.infy.ekart.payment.service.PaymentCircuitBreakerService;
import com.infy.ekart.payment.service.PaymentService;

@CrossOrigin
@RestController
@Validated
@RequestMapping(value = "/payment-api")

public class PaymentAPI {

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private Environment environment;

	@Autowired
	private RestTemplate template;

	private static final Log LOGGER = LogFactory.getLog(PaymentAPI.class);

	@Autowired
	private PaymentCircuitBreakerService paymentCircuitBreakerService;

	@PostMapping(value = "/customer/{customerEmailId:.+}/cards")
	public ResponseEntity<String> addNewCard(@RequestBody CardDTO cardDTO,
			@Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+", message = "{invalid.email.format}") @PathVariable("customerEmailId") String customerEmailId)
			throws EKartPaymentException, NoSuchAlgorithmException {
		LOGGER.info("Recieved request to add new  card for customer : " + cardDTO.getCustomerEmailId());

		int cardId;
		cardId = paymentService.addCustomerCard(customerEmailId, cardDTO);
		String message = environment.getProperty("PaymentAPI.NEW_CARD_ADDED_SUCCESS");
		String toReturn = message + cardId;
		toReturn = toReturn.trim();
		return new ResponseEntity<>(toReturn, HttpStatus.OK);

	}

	@PutMapping(value = "/update/card")
	public ResponseEntity<String> updateCustomerCard(@Valid @RequestBody CardDTO cardDTO)
			throws EKartPaymentException, NoSuchAlgorithmException {
		LOGGER.info("Recieved request to update  card :" + cardDTO.getCardId() + " of customer : "
				+ cardDTO.getCustomerEmailId());

		paymentService.updateCustomerCard(cardDTO);
		String modificationSuccessMsg = environment.getProperty("PaymentAPI.UPDATE_CARD_SUCCESS");
		return new ResponseEntity<>(modificationSuccessMsg, HttpStatus.OK);

	}

	@DeleteMapping(value = "/customer/{customerEmailId:.+}/card/{cardID}/delete")
	public ResponseEntity<String> deleteCustomerCard(@PathVariable("cardID") Integer cardID,
			@Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+", message = "{invalid.email.format}") @PathVariable("customerEmailId") String customerEmailId)
			throws EKartPaymentException {
		LOGGER.info("Recieved request to delete  card :" + cardID + " of customer : " + customerEmailId);

		paymentService.deleteCustomerCard(customerEmailId, cardID);
		String modificationSuccessMsg = environment.getProperty("PaymentAPI.CUSTOMER_CARD_DELETED_SUCCESS");
		return new ResponseEntity<>(modificationSuccessMsg, HttpStatus.OK);

	}

	// Get the customer cards details by calling getCardsOfCustomer()
	// method of PaymentService() and return the list of card details obtained
	@GetMapping(value = "/customer/{customerEmailId}/card-type/{cardType}")
	public ResponseEntity<List<CardDTO>> getCardsOfCustomer(@PathVariable String customerEmailId,
			@PathVariable String cardType) throws EKartPaymentException {

		// Write your logic here

		return null;
	}

	@PostMapping(value = "/customer/{customerEmailId}/pay-order")

	// Annotate this method for handling resilience
	// Get the order details from CustomerMS for the given orderId (available in
	// TransactionDTO)
	// Update the Transaction details with the obtained Order details in above step,
	// along with transaction date and total price
	// Authenticate the transaction details for the given customer by calling
	// authenticatePayment() method of PaymentService and assign the return value to transactionDTO
	// Add the transaction details to the database by calling addTransaction()
	// method of PaymentService 
	 // Update the order status by calling updateOrderAfterPayment() method of
    // PaymentCircuitBreakerService by passing transaction status as TRANSACTION_SUCCESS
    // Set the appropriate success or failure message and return the same

	public ResponseEntity<String> payForOrder(
			@Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+", message = "{invalid.email.format}") @PathVariable String customerEmailId,
			@Valid @RequestBody TransactionDTO transactionDTO)
			throws NoSuchAlgorithmException, EKartPaymentException, PayOrderFallbackException {

		// Write your logic here

		return null;

	}

	// Implement a fallback method here
	// If exception message is Payment.TRANSACTION_FAILED_CVV_NOT_MATCHING then set
	// message as Payment.TRANSACTION_FAILED_CVV_NOT_MATCHING
	// Else set the message as PaymentAPI.PAYMENT_FAILURE_FALLBACK
	// Return the above message as response

	public ResponseEntity<String> payForOrderFallback(String customerEmailId, TransactionDTO transactionDTO,
			RuntimeException exception) throws RuntimeException {
		
		LOGGER.info("*************In Fallback**************");
		LOGGER.info("Customer with customer Email Id: "+ customerEmailId + "\n and Order Id: "+ transactionDTO.getOrder().getOrderId());


		// Write your logic here

		return null;
	}
}
