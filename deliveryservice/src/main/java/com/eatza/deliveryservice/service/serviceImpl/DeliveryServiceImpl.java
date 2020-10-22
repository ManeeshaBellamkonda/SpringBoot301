package com.eatza.deliveryservice.service.serviceImpl;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


import com.eatza.deliveryservice.dto.DeliveryRequestDto;
import com.eatza.deliveryservice.exception.DeliveryIdNotFounException;
import com.eatza.deliveryservice.model.Delivery;
import com.eatza.deliveryservice.repository.DeliveryRepository;
import com.eatza.deliveryservice.service.DeliveryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DeliveryServiceImpl implements DeliveryService{

	@Autowired
	private DeliveryRepository deliveryrepository;
	
	private ObjectMapper objectmapper=new ObjectMapper();
	
	private static final Logger logger = LoggerFactory.getLogger(DeliveryServiceImpl.class);
	
	@KafkaListener(topics = "kafka_delivery_json", groupId = "group_json",containerFactory = "userKafkaListenerFactory")
	@Override
	public String FoodDelivery(Delivery delivery) throws JsonMappingException, JsonProcessingException, MessagingException {
		logger.debug("In place delivery method,creating delivery object to persist");
		//DeliveryRequestDto deliverureqdto=objectmapper.readValues(delivery,DeliveryRequestDto.class);
		String deliveryPerson=assignDeliveryPerson();
		Delivery deliveryobj=new Delivery();
		deliveryobj.setCustomerId(delivery.getCustomerId());
		deliveryobj.setRestaurantId(delivery.getRestaurantId());
		deliveryobj.setDeliveryPerson(deliveryPerson);
		deliveryrepository.save(deliveryobj);
		return "Delivery Details are saved successfully";
	}
    private String assignDeliveryPerson() {
	logger.debug("Assigning delivery person randomly");
	List<String> deliveryPersons=new ArrayList<String>();
	deliveryPersons.add("Mohan");
	deliveryPersons.add("chandu");
	deliveryPersons.add("Mokshith");
	deliveryPersons.add("Reyansh");
	deliveryPersons.add("Yashu");
	return deliveryPersons.parallelStream().findAny().get();
}
    private void outForDelivery(DeliveryRequestDto deliverureqdto, String deliveryPerson) throws  MessagingException {
    	logger.debug("Email to that particular person");
    	String message="Dear Customer,"+System.lineSeparator()+"Your order has been places successfully customerId:"
    			+deliverureqdto.getCustomerId()+"with the resaturant Id:"+deliverureqdto.getRestaurantId()
    			+"will be deliverd by "+deliveryPerson+System.lineSeparator()+"Enjoy your Delicious Food";
    	Properties props=new Properties();
    	props.put("mail.smtp.auth", "true");
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.smtp.host", "smtp.gmail.com");
    	props.put("mail.smtp.port", "587");
    	Session session=Session.getInstance(props, new javax.mail.Authenticator() {
    		protected PasswordAuthentication getpasswordAuthentication() {
    			return new PasswordAuthentication("bellamkondamanisha@gmail.com","Koteswararao@9");
    		}
    	});
    	Message msg=new MimeMessage(session);
    	msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse("bellamkondamanisha@gmail.com"));
        msg.setSubject("Order Status");
        msg.setSentDate(new Date());
        MimeBodyPart messageBodyPart=new MimeBodyPart();
        messageBodyPart.setContent(message,"text/html");
        Multipart multipart=new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart attachPart=new MimeBodyPart();
        msg.setContent(multipart);
        Transport.send(msg);
    }
	public Delivery fetchDeliveryDetails(Long deliveryId) throws DeliveryIdNotFounException {
		Optional<Delivery> result = deliveryrepository.findById(deliveryId);
		if (result.isPresent()) {
			return result.get();
		} else {
			logger.debug("No delivery found for given Id");
			throw new DeliveryIdNotFounException();
		}
	}

}
