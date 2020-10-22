package com.eatza.customerservice.service;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.eatza.customerservice.dto.CustomerDto;
import com.eatza.customerservice.exception.CustomerNotFounException;
import com.eatza.customerservice.model.Customer;
import com.eatza.customerservice.repository.CustomerRepository;
import com.eatza.customerservice.service.serviceImpl.CustomerServiceImpl;
@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(value = CustomerServiceImpl.class)
public class CustomerServiceImplTest {
	
	@Mock
	private CustomerRepository customerRespository;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private CustomerServiceImpl customerService;
	
	
	
	@Test
	public void addCustomerTest() {
		CustomerDto customerDTO = new CustomerDto(1L, "manisha", 90909L, (byte) 22, "isActive", null, null);
		Customer customer = new Customer(1L, "manisha", 90909L, (byte) 22, "isActive", null, null);

		when(modelMapper.map(customerDTO, Customer.class)).thenReturn(customer);
		when(customerRespository.save(customer)).thenReturn(customer);
		when(modelMapper.map(customer, CustomerDto.class)).thenReturn(customerDTO);
		CustomerDto response = customerService.addCustomer(customerDTO);
		assertNotNull(response);
	}

    @Test
	public void getCustomerByIdTest() throws CustomerNotFounException {
	CustomerDto customerDTO = new CustomerDto(1L, "manisha", 90909L, (byte) 22, "isActive", null, null);
	Customer customer = new Customer(1L, "manisha", 90909L, (byte) 22, "isActive", null, null);

	when(customerRespository.findById(1L)).thenReturn(Optional.of(customer));
	Customer response = customerService.getCustomerById(1L);
	assertNotNull(response);
    }

    @Test
	public void deleteCustomerTest()  {
    	CustomerDto customerDTO = new CustomerDto(1L, "manisha", 90909L, (byte) 22, "isActive", null, null);
		Customer customer = new Customer(1L, "manisha", 90909L, (byte) 22, "isActive", null, null);

//		when(customerRespository.findById(1L)).thenReturn(Optional.of(customer));
//		when(customerRespository.saveAndFlush(customer)).thenReturn(customer);
//		when(modelMapper.map(customer, CustomerDto.class)).thenReturn(customerDTO);
		 customerService.deleteCustomer(1L);
		
		
	}
   
}
