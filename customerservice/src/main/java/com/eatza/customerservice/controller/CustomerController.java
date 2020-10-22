package com.eatza.customerservice.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.eatza.customerservice.dto.CustomerDto;
import com.eatza.customerservice.exception.CustomerNotFounException;
import com.eatza.customerservice.model.Customer;
import com.eatza.customerservice.service.CustomerService;
@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerservice;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@PostMapping("/customer")
	public ResponseEntity<String> addCustomer(@RequestHeader String authorization,
			@RequestBody CustomerDto customerDTO) {
		logger.info("customer added successfully");
		customerservice.addCustomer(customerDTO);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body("Customer added successfully");
		}
	@GetMapping("/getByCustomerId")
	public ResponseEntity<Customer> getCustomerById(@RequestHeader String authorization,@RequestParam long customerId) throws CustomerNotFounException {
		logger.info("fetching customer by using id  successfully");
		return new ResponseEntity<Customer>(customerservice.getCustomerById(customerId), HttpStatus.OK);
	}
	@DeleteMapping("/deleteBycustomerId")
	public ResponseEntity<String> deleteCustomer(@RequestHeader String authorization,
			@RequestParam long customerId)  {
		logger.info("customer deleted successfully");
		customerservice.deleteCustomer(customerId);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body("Customer deleted successfully");
		}
}
