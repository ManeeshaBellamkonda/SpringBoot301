package com.eatza.deliveryservice.service;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.mail.MessagingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


import com.eatza.deliveryservice.dto.DeliveryRequestDto;
import com.eatza.deliveryservice.model.Delivery;
import com.eatza.deliveryservice.repository.DeliveryRepository;
import com.eatza.deliveryservice.service.serviceImpl.DeliveryServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(value = DeliveryServiceImpl.class)
public class DeliveryServiceTest {
    @Mock
	DeliveryRepository deliveryRepository;

	@InjectMocks
	DeliveryServiceImpl deliveryService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void saveDelivery() throws JsonMappingException, JsonProcessingException, MessagingException {
//		DeliveryRequestDto dto = new DeliveryRequestDto();
//		dto.setCustomerId(55L);
//		dto.setRestaurantId(4L);
		Delivery entity = new Delivery();
		entity.setCustomerId(55L);
     	entity.setRestaurantId(6L);
		entity.setId(2L);
		entity.setDeliveryPerson("john");
		String response = deliveryService.FoodDelivery(entity);
		assertNotNull(response);
	}

	}
