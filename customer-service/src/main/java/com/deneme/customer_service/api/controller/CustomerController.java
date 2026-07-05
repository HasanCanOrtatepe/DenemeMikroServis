package com.deneme.customer_service.api.controller;

import com.deneme.customer_service.api.dto.request.CreatedCustomerRequest;
import com.deneme.customer_service.api.dto.request.UpdateCustomerRequest;
import com.deneme.customer_service.api.dto.response.CreatedCustomerResponse;
import com.deneme.customer_service.api.dto.response.GetCustomerResponse;
import com.deneme.customer_service.api.dto.response.UpdateCustomerResponse;
import com.deneme.customer_service.core.exceptions.ValidationErrorResponse;
import com.deneme.customer_service.model.Customer;
import com.deneme.customer_service.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<GetCustomerResponse>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCustomerResponse> getCustomerById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.getCustomer(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreatedCustomerResponse> createCustomer(@RequestBody CreatedCustomerRequest createdCustomerRequest) {
        return new ResponseEntity<>(customerService.createCustomer(createdCustomerRequest),HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateCustomerResponse> updateCustomer(@RequestBody UpdateCustomerRequest updateCustomerRequest, @PathVariable Long id) {
        return new ResponseEntity<>(customerService.updateCustomer(updateCustomerRequest,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }


}
