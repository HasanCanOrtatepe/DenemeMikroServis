package com.deneme.order_service.service;

import com.deneme.order_service.api.dto.request.orderItemRequest.CreateOrderItemRequest;
import com.deneme.order_service.api.dto.request.orderItemRequest.UpdateOrderItemRequest;
import com.deneme.order_service.api.dto.response.orderItemResponse.CreateOrderItemResponse;
import com.deneme.order_service.api.dto.response.orderItemResponse.GetOrderItemResponse;
import com.deneme.order_service.api.dto.response.orderItemResponse.UpdateOrderItemResponse;
import com.deneme.order_service.core.exceptions.ResourceNotFound;
import com.deneme.order_service.model.Order;
import com.deneme.order_service.model.OrderItem;
import com.deneme.order_service.repository.OrderItemRepository;
import com.deneme.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    OrderItemRepository orderItemRepository;
    OrderRepository orderRepository;


    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, OrderRepository orderRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
    }


    @Override
    public void deleteOrderItem(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(
                ()->new ResourceNotFound("No OrderItem found with id: "+id)
        );
        orderItem.setActive(false);
        orderItemRepository.save(orderItem);

    }

    @Override
    public CreateOrderItemResponse createOrderItem(CreateOrderItemRequest createOrderItemRequest) {
        Order order= orderRepository.findById(createOrderItemRequest.getOrderId()).orElseThrow(
                (()->new ResourceNotFound("No OrderItem found with id: "+createOrderItemRequest.getOrderId()))
        );


        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(createOrderItemRequest.getProductId());
        orderItem.setQuantity(createOrderItemRequest.getQuantity());
        orderItem.setUnitPrice(createOrderItemRequest.getUnitPrice());
        orderItem.setActive(true);
        orderItem.setOrderId(order);
        orderItemRepository.save(orderItem);
        CreateOrderItemResponse createOrderItemResponse = new CreateOrderItemResponse();
        createOrderItemResponse.setOrderId(order.getId());
        createOrderItemResponse.setProductId(orderItem.getProductId());
        createOrderItemResponse.setQuantity(orderItem.getQuantity());
        createOrderItemResponse.setPrice(orderItem.getUnitPrice());

        return createOrderItemResponse;
    }

    @Override
    public UpdateOrderItemResponse updateOrderItem(UpdateOrderItemRequest updateOrderItemRequest,Long id) {
        Order order= orderRepository.findById(updateOrderItemRequest.getOrderId()).orElseThrow(
                (()->new ResourceNotFound("No OrderItem found with id: "+updateOrderItemRequest.getOrderId()))
        );
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(
                (()->new ResourceNotFound("No OrderItem found with id: "+id))
        );
        orderItem.setProductId(updateOrderItemRequest.getProductId());
        orderItem.setQuantity(updateOrderItemRequest.getQuantity());
        orderItem.setActive(true);
        orderItem.setOrderId(order);
        orderItem.setUnitPrice(updateOrderItemRequest.getUnitPrice());
        orderItemRepository.save(orderItem);
        UpdateOrderItemResponse updateOrderItemResponse = new UpdateOrderItemResponse();
        updateOrderItemResponse.setOrderId(order.getId());
        updateOrderItemResponse.setProductId(updateOrderItemRequest.getProductId());
        updateOrderItemResponse.setQuantity(updateOrderItemRequest.getQuantity());
        updateOrderItemResponse.setPrice(updateOrderItemRequest.getUnitPrice());

        return updateOrderItemResponse;
    }

    @Override
    public GetOrderItemResponse getOrderItem(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(
                ()->new ResourceNotFound("No OrderItem found with id: "+id)
        );
        if (!orderItem.isActive()) {
            throw new ResourceNotFound("No OrderItem found with id: "+id);
        }
        GetOrderItemResponse getOrderItemResponse = new GetOrderItemResponse();
        getOrderItemResponse.setId(orderItem.getId());
        getOrderItemResponse.setOrderId(orderItem.getOrderId().getId());
        getOrderItemResponse.setProductId(orderItem.getProductId());
        getOrderItemResponse.setPrice(orderItem.getUnitPrice());
        getOrderItemResponse.setQuantity(orderItem.getQuantity());

        return getOrderItemResponse;
    }

    @Override
    public List<GetOrderItemResponse> getOrderItems() {

        return orderItemRepository.findAll().stream().filter(OrderItem::isActive).map( orderItem ->
                new GetOrderItemResponse(
                        orderItem.getId(),
                        orderItem.getProductId(),
                        orderItem.getQuantity(),
                        orderItem.getUnitPrice(),
                        orderItem.getOrderId().getId()
                )

        ).toList();
    }
}
