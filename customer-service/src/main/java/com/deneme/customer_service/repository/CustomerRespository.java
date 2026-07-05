package com.deneme.customer_service.repository;

import com.deneme.customer_service.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRespository extends JpaRepository<Customer,Long> {

}
