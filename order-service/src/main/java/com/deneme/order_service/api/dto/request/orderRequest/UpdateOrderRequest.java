package com.deneme.order_service.api.dto.request.orderRequest;


public class UpdateOrderRequest {

    private Long customerId;
    private String status;
    private Double totalPrice;


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public UpdateOrderRequest(Long customerId, String status, Double totalPrice) {
        this.customerId = customerId;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public UpdateOrderRequest() {}
}
