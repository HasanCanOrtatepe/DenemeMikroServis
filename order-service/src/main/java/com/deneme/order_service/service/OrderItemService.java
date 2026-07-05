package com.deneme.order_service.service;

import com.deneme.order_service.api.dto.request.orderItemRequest.CreateOrderItemRequest;
import com.deneme.order_service.api.dto.request.orderItemRequest.UpdateOrderItemRequest;
import com.deneme.order_service.api.dto.response.orderItemResponse.CreateOrderItemResponse;
import com.deneme.order_service.api.dto.response.orderItemResponse.GetOrderItemResponse;
import com.deneme.order_service.api.dto.response.orderItemResponse.UpdateOrderItemResponse;
import com.deneme.order_service.model.OrderItem;

import java.util.List;

public interface OrderItemService {
    void deleteOrderItem(Long id);
    CreateOrderItemResponse createOrderItem(CreateOrderItemRequest createOrderItemRequest);
    UpdateOrderItemResponse updateOrderItem(UpdateOrderItemRequest updateOrderItemRequest,Long id);
    GetOrderItemResponse getOrderItem(Long id);
    List<GetOrderItemResponse> getOrderItems();
}
