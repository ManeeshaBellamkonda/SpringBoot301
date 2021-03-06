package com.eatza.deliveryservice.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.eatza.deliveryservice.dto.DeliveryRequestDto;
import com.eatza.deliveryservice.model.Delivery;

@Configuration
@EnableKafka 
public class KafkaConfiguration {

	@Bean 
	public ConsumerFactory<String, Delivery> orderConsumerFactory() { 
	JsonDeserializer<Delivery> deserializer = new JsonDeserializer<>(Delivery.class);
	 // deserializer.setRemoveTypeHeaders(false); 
	// deserializer.addTrustedPackages("*"); 
	deserializer.setUseTypeMapperForKey(true); 
	Map<String, Object> config = new HashMap<>();

	config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
	 config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json"); 
	config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class); 
	config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer); 
	config.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, deserializer); 
	config.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS,deserializer.getClass()); 
	config.put(JsonDeserializer.TRUSTED_PACKAGES,"*"); 
	return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new ErrorHandlingDeserializer(deserializer)); 
	}

	 

	    @Bean
	    public ConcurrentKafkaListenerContainerFactory<String, Delivery> userKafkaListenerFactory() {
	        ConcurrentKafkaListenerContainerFactory<String, Delivery> factory = new ConcurrentKafkaListenerContainerFactory<>();
	        factory.setConsumerFactory(orderConsumerFactory());
	        return factory;
	    }

	 


}
