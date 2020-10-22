package com.eatza.customerservice.service;
import com.eatza.customerservice.dto.CustomerDto;
import com.eatza.customerservice.exception.CustomerNotFounException;
import com.eatza.customerservice.model.Customer;

public interface CustomerService {

	public CustomerDto addCustomer(CustomerDto customerDTO);
	
	public Customer getCustomerById(long customerId)throws CustomerNotFounException; 
	
	public void deleteCustomer(long customerId); 
}
