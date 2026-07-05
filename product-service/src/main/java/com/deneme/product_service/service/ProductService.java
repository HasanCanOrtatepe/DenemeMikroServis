package com.deneme.product_service.service;


import com.deneme.product_service.api.dto.request.CreatedProductRequest;
import com.deneme.product_service.api.dto.request.UpdateProductRequest;
import com.deneme.product_service.api.dto.response.CreatedProductResponse;
import com.deneme.product_service.api.dto.response.GetProductResponse;
import com.deneme.product_service.api.dto.response.UpdateProductResponse;
import com.deneme.product_service.model.Product;

import java.util.List;

public interface ProductService {

    GetProductResponse getProduct(Long id);
    List<GetProductResponse> getProducts();
    CreatedProductResponse addProduct(CreatedProductRequest createdProductRequest);
    UpdateProductResponse updateProduct(UpdateProductRequest updateProductRequest,Long id);
    void deleteProduct(Long id);




}
