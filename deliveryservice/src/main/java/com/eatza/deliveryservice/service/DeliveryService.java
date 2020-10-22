package com.eatza.deliveryservice.service;


import javax.mail.MessagingException;

import com.eatza.deliveryservice.dto.DeliveryRequestDto;
import com.eatza.deliveryservice.exception.DeliveryIdNotFounException;
import com.eatza.deliveryservice.model.Delivery;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface DeliveryService {

//	public String saveDeliveryDetails(DeliveryRequestDto deliveryRequest);
	public String FoodDelivery(Delivery delivery) throws JsonMappingException, JsonProcessingException, MessagingException;
	
	public Delivery fetchDeliveryDetails(Long deliveryId) throws DeliveryIdNotFounException;
}
