package com.deneme.order_service.api.dto.response.orderItemResponse;

public class GetOrderItemResponse {
    private Long id;
    private Long productId;
    private Long quantity;
    private Double price;
    private Long orderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public GetOrderItemResponse(Long id, Long productId, Long quantity, Double price, Long orderId) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.orderId = orderId;
    }
    public GetOrderItemResponse() {}
}
