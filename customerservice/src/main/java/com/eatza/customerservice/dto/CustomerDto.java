package com.eatza.customerservice.dto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
public class CustomerDto {

	private long customerId;

	private String customerName;

	private long customerMobile;
	
	private byte customerAge;

	private String customerActiveStatus;

	private LocalDateTime customerVisitedDate;
	
	private Long orderId;
}
