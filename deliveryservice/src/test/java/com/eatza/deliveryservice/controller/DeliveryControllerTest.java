package com.eatza.deliveryservice.controller;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.eatza.deliveryservice.dto.DeliveryRequestDto;
import com.eatza.deliveryservice.model.Delivery;
import com.eatza.deliveryservice.service.DeliveryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RunWith(SpringRunner.class)
@WebMvcTest(value = DeliveryController.class)
public class DeliveryControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;
	@MockBean
	private DeliveryService deliveryService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	DeliveryRequestDto deliveryRequestDto;

	String jwt = "";
	private static final long EXPIRATIONTIME = 900000;

	@Before
	public void setup() {
		jwt = "Bearer " + Jwts.builder().setSubject("user").claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey")
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME)).compact();
	}

	@Test
	public void FoodDelivery() throws Exception {
		Delivery delivery=new Delivery();
		delivery.setCustomerId(55L);
		delivery.setRestaurantId(1L);
		when(deliveryService.FoodDelivery(delivery));
		RequestBuilder request = MockMvcRequestBuilders.post("/DeliveryDetails").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString((delivery))).header(HttpHeaders.AUTHORIZATION, jwt);
		mockMvc.perform(request).andExpect(status().is(200)).andReturn();
	}
}

