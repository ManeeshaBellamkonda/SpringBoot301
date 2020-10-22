package com.eatza.customerservice.dto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@AllArgsConstructor@NoArgsConstructor
public class Order {

	private Long id;
	private Long customerId;
	private String status;
	private Long restaurantId;
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;
}
