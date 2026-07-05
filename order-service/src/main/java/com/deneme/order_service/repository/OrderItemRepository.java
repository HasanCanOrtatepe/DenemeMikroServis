package com.deneme.order_service.repository;

import com.deneme.order_service.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
