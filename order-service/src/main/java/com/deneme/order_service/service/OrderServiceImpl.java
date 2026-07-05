package com.deneme.order_service.service;

import com.deneme.order_service.api.dto.request.orderRequest.CreateOrderRequest;
import com.deneme.order_service.api.dto.request.orderRequest.UpdateOrderRequest;
import com.deneme.order_service.api.dto.response.orderResponse.CreateOrderResponse;
import com.deneme.order_service.api.dto.response.orderResponse.GetOrderResponse;
import com.deneme.order_service.api.dto.response.orderResponse.UpdateOrderResponse;
import com.deneme.order_service.core.exceptions.ResourceNotFound;
import com.deneme.order_service.model.Order;
import com.deneme.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public void deleteOrder(Long orderId) {
        Order order= orderRepository.findById(orderId).orElseThrow(
                ()->new ResourceNotFound("No Order found with id: "+orderId)
        );
        order.setActive(false);
        orderRepository.save(order);
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        Order order = new Order();
        order.setActive(true);
        order.setCustomerid(createOrderRequest.getCustomerId());
        order.setStatus(createOrderRequest.getStatus());
        order.setTotalPrice(createOrderRequest.getTotalPrice());
        orderRepository.save(order);
        CreateOrderResponse createOrderResponse = new CreateOrderResponse();
        createOrderResponse.setStatus(order.getStatus());
        createOrderResponse.setCustomerId(order.getCustomerid());
        createOrderResponse.setTotalPrice(order.getTotalPrice());
        return createOrderResponse;
    }

    @Override
    public UpdateOrderResponse updateOrder(UpdateOrderRequest updateOrderRequest,Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFound("No Order found with id: "+id)
        );
        order.setCustomerid(updateOrderRequest.getCustomerId());
        order.setStatus(updateOrderRequest.getStatus());
        order.setTotalPrice(updateOrderRequest.getTotalPrice());
        orderRepository.save(order);
        UpdateOrderResponse updateOrderResponse = new UpdateOrderResponse();
        updateOrderResponse.setStatus(order.getStatus());
        updateOrderResponse.setCustomerId(order.getCustomerid());
        updateOrderResponse.setTotalPrice(order.getTotalPrice());
        return updateOrderResponse;
    }

    @Override
    public GetOrderResponse getOrder(Long orderId) {
        Order order= orderRepository.findById(orderId).orElseThrow(
                ()->new ResourceNotFound("No Order found with id: "+orderId)
        );
        if (!order.isActive()) {
            throw new ResourceNotFound("No Order found with id: "+orderId);
        }
        GetOrderResponse getOrderResponse = new GetOrderResponse();
        getOrderResponse.setId(order.getId());
        getOrderResponse.setStatus(order.getStatus());
        getOrderResponse.setCustomerId(order.getCustomerid());
        getOrderResponse.setTotalPrice(order.getTotalPrice());

        return getOrderResponse;
    }

    @Override
    public List<GetOrderResponse> getOrders() {

        return orderRepository.findAll().stream().filter(Order::isActive).map(order ->
                new GetOrderResponse(order.getId(),order.getCustomerid(),order.getStatus(),order.getTotalPrice())).toList();

    }


}
