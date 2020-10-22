package com.eatza.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eatza.customerservice.model.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{

}
