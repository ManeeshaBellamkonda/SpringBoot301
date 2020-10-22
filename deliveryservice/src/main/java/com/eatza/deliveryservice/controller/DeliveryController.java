package com.eatza.deliveryservice.controller;
import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.eatza.deliveryservice.dto.DeliveryRequestDto;
import com.eatza.deliveryservice.exception.DeliveryIdNotFounException;
import com.eatza.deliveryservice.model.Delivery;
import com.eatza.deliveryservice.service.DeliveryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class DeliveryController {

	private static final Logger logger = LoggerFactory.getLogger(DeliveryController.class);
	@Autowired
	private DeliveryService deliveryservice;
	
	@PostMapping("/DeliveryDetails")
	public ResponseEntity<String> FoodDelivery(@RequestHeader String authorization,@RequestBody Delivery delivery) throws JsonMappingException, JsonProcessingException, MessagingException {
		logger.info("Delivery details saved successfully");
		deliveryservice.FoodDelivery(delivery);
		return ResponseEntity.status(HttpStatus.OK)
				.body("Delivery details saved successfully");
	}
	@GetMapping("/getDeliveryDetails/id/{deliveryId}")
	public Delivery fetchDeliveryDetails(@PathVariable Long deliveryId)
			throws DeliveryIdNotFounException {
    return deliveryservice.fetchDeliveryDetails(deliveryId);

	}
}
