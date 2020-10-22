package com.eatza.customerservice.service.serviceImpl;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eatza.customerservice.dto.CustomerDto;
import com.eatza.customerservice.exception.CustomerNotFounException;
import com.eatza.customerservice.model.Customer;
import com.eatza.customerservice.repository.CustomerRepository;
import com.eatza.customerservice.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService{

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	@Autowired
	private CustomerRepository cutomerrepository;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Override
	public CustomerDto addCustomer(CustomerDto customerDTO) {
		Customer customer = modelmapper.map(customerDTO, Customer.class);
		customer.setCustomerActiveStatus("isActive");
		customerDTO=modelmapper.map(cutomerrepository.save(customer),CustomerDto.class);
		return customerDTO;

	}
	@Override
	public Customer getCustomerById(long customerId) throws CustomerNotFounException {
	Optional<Customer> customer=cutomerrepository.findById(customerId);
		if(customer.isPresent()) {
			return customer.get();
		}
		else {
			logger.debug("No customer found for given Id");
			throw new CustomerNotFounException();
	}
	}
    @Override
	public void deleteCustomer(long customerId) {
		cutomerrepository.deleteById(customerId);
	}
	}
