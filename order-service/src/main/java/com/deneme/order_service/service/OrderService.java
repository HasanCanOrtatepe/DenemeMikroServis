package com.deneme.order_service.service;

import com.deneme.order_service.api.dto.request.orderRequest.CreateOrderRequest;
import com.deneme.order_service.api.dto.request.orderRequest.UpdateOrderRequest;
import com.deneme.order_service.api.dto.response.orderResponse.CreateOrderResponse;
import com.deneme.order_service.api.dto.response.orderResponse.GetOrderResponse;
import com.deneme.order_service.api.dto.response.orderResponse.UpdateOrderResponse;

import java.util.List;

public interface OrderService {
    void deleteOrder(Long orderId);
    CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest);
    UpdateOrderResponse updateOrder(UpdateOrderRequest updateOrderRequest,Long id);
    GetOrderResponse getOrder(Long orderId);
    List<GetOrderResponse> getOrders();
}
