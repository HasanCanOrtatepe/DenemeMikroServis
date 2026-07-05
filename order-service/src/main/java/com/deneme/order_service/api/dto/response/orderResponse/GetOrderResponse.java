package com.deneme.order_service.api.dto.response.orderResponse;

public class GetOrderResponse {
    private Long id;
    private Long customerId;
    private String status;
    private Double totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public GetOrderResponse(Long id, Long customerId, String status, Double totalPrice) {
        this.id = id;
        this.customerId = customerId;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public GetOrderResponse() {}
}