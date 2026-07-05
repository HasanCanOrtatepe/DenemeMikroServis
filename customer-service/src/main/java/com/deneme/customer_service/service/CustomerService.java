package com.deneme.customer_service.service;

import com.deneme.customer_service.api.dto.request.CreatedCustomerRequest;
import com.deneme.customer_service.api.dto.request.UpdateCustomerRequest;
import com.deneme.customer_service.api.dto.response.CreatedCustomerResponse;
import com.deneme.customer_service.api.dto.response.GetCustomerResponse;
import com.deneme.customer_service.api.dto.response.UpdateCustomerResponse;
import com.deneme.customer_service.model.Customer;

import java.util.List;

public interface CustomerService {

    GetCustomerResponse getCustomer(Long id);
    List<GetCustomerResponse> getAllCustomers();
    void deleteCustomer(Long id);
    CreatedCustomerResponse createCustomer(CreatedCustomerRequest createdCustomerRequest);
    UpdateCustomerResponse updateCustomer(UpdateCustomerRequest updateCustomerRequest,Long id);

}
