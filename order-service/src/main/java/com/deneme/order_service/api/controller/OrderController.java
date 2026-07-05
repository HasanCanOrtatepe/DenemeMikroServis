package com.deneme.order_service.api.controller;


import com.deneme.order_service.api.dto.request.orderRequest.CreateOrderRequest;
import com.deneme.order_service.api.dto.request.orderRequest.UpdateOrderRequest;
import com.deneme.order_service.api.dto.response.orderResponse.CreateOrderResponse;
import com.deneme.order_service.api.dto.response.orderResponse.GetOrderResponse;
import com.deneme.order_service.api.dto.response.orderResponse.UpdateOrderResponse;
import com.deneme.order_service.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<GetOrderResponse>> getOrder() {
        return new ResponseEntity<>(orderService.getOrders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetOrderResponse> getOrder(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getOrder(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        return new ResponseEntity<>(orderService.createOrder(createOrderRequest),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateOrderResponse> updateOrder(@PathVariable Long id, @RequestBody UpdateOrderRequest updateOrderRequest) {
        return new ResponseEntity<>(orderService.updateOrder(updateOrderRequest,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
