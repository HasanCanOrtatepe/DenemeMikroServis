package com.deneme.order_service.api.controller;


import com.deneme.order_service.api.dto.request.orderItemRequest.CreateOrderItemRequest;
import com.deneme.order_service.api.dto.request.orderItemRequest.UpdateOrderItemRequest;
import com.deneme.order_service.api.dto.request.orderRequest.CreateOrderRequest;
import com.deneme.order_service.api.dto.request.orderRequest.UpdateOrderRequest;
import com.deneme.order_service.api.dto.response.orderItemResponse.CreateOrderItemResponse;
import com.deneme.order_service.api.dto.response.orderItemResponse.GetOrderItemResponse;
import com.deneme.order_service.api.dto.response.orderItemResponse.UpdateOrderItemResponse;
import com.deneme.order_service.api.dto.response.orderResponse.CreateOrderResponse;
import com.deneme.order_service.api.dto.response.orderResponse.GetOrderResponse;
import com.deneme.order_service.api.dto.response.orderResponse.UpdateOrderResponse;
import com.deneme.order_service.service.OrderItemService;
import com.deneme.order_service.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/orderItems")
public class OrderItemController {
    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public ResponseEntity<List<GetOrderItemResponse>> getOrderItems() {
        return new ResponseEntity<>(orderItemService.getOrderItems(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetOrderItemResponse> getOrderItem(@PathVariable Long id) {
        return new ResponseEntity<>(orderItemService.getOrderItem(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateOrderItemResponse> createOrderItem(@RequestBody CreateOrderItemRequest createOrderItemRequest) {
        return new ResponseEntity<>(orderItemService.createOrderItem(createOrderItemRequest),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateOrderItemResponse> updateOrderItem(@PathVariable Long id, @RequestBody UpdateOrderItemRequest updateOrderItemRequest) {
        return new ResponseEntity<>(orderItemService.updateOrderItem(updateOrderItemRequest,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
