package com.eatza.customerservice.model;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter@Getter
@NoArgsConstructor@AllArgsConstructor
@Entity
public class Customer {
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long customerId;
	
	private String customerName;
	
	private long customerMobile;
	
	private byte customerAge;
	
	private String customerActiveStatus;
	
	@CreationTimestamp
	private LocalDateTime customerVisitedDate;
	
	private Long  orderId;
}
