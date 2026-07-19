package com.deneme.customer_service.service;

import com.deneme.customer_service.api.dto.request.CreatedCustomerRequest;
import com.deneme.customer_service.api.dto.request.UpdateCustomerRequest;
import com.deneme.customer_service.api.dto.response.CreatedCustomerResponse;
import com.deneme.customer_service.api.dto.response.GetCustomerResponse;
import com.deneme.customer_service.api.dto.response.UpdateCustomerResponse;
import com.deneme.customer_service.core.exceptions.ResourceNotFound;
import com.deneme.customer_service.model.Customer;
import com.deneme.customer_service.repository.CustomerRespository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRespository customerRespository;

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    public CustomerServiceImpl(CustomerRespository customerRespository) {
        this.customerRespository = customerRespository;
    }


    @Override
    public GetCustomerResponse getCustomer(Long id) {
        Customer customer= customerRespository.findById(id).orElseThrow(()->new ResourceNotFound("Customer not found"));
        if (!customer.isActive()) {
            throw new ResourceNotFound("Customer not found with id " + id);
        }
        GetCustomerResponse response = new GetCustomerResponse();
        response.setId(customer.getId());
        response.setEmail(customer.getEmail());
        response.setFirstName(customer.getFirstName());
        response.setLastName(customer.getLastName());
        return response;
    }

    @Override
    public List<GetCustomerResponse> getAllCustomers() {
        List<GetCustomerResponse> responses= customerRespository.findAll().stream().filter(Customer::isActive).map(customer -> new GetCustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail()
        ) ).toList();

        return responses;

    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer=customerRespository.findById(id).orElseThrow(
                ()->new ResourceNotFound("Customer not found with id " + id)
        );
        customer.setActive(false);
        customerRespository.save(customer);
    }

    @Override
    public CreatedCustomerResponse createCustomer(CreatedCustomerRequest createdCustomerRequest) {
        Customer customer = new Customer();
        customer.setActive(true);
        customer.setEmail(createdCustomerRequest.getEmail());
        customer.setFirstName(createdCustomerRequest.getFirstName());
        customer.setLastName(createdCustomerRequest.getLastName());
        customerRespository.save(customer);
        CreatedCustomerResponse response = new CreatedCustomerResponse();
        response.setId(customer.getId());
        response.setEmail(createdCustomerRequest.getEmail());
        response.setFirstName(createdCustomerRequest.getFirstName());
        response.setLastName(createdCustomerRequest.getLastName());
        return response;
    }

    @Override
    public UpdateCustomerResponse updateCustomer(UpdateCustomerRequest updateCustomerRequest,Long id) {
        Customer customer = customerRespository.findById(id).orElseThrow(
                ()-> new ResourceNotFound("Customer not found with id " + id)
        );

        customer.setFirstName(updateCustomerRequest.getFirstName());
        customer.setLastName(updateCustomerRequest.getLastName());
        customer.setEmail(updateCustomerRequest.getEmail());
        customerRespository.save(customer);

        UpdateCustomerResponse response = new UpdateCustomerResponse();
        response.setId(customer.getId());
        response.setFirstName(customer.getFirstName());
        response.setLastName(customer.getLastName());
        response.setEmail(customer.getEmail());
        return response;
    }
}
