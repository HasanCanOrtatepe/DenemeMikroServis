package com.deneme.product_service.api.controller;

import com.deneme.product_service.api.dto.request.CreatedProductRequest;
import com.deneme.product_service.api.dto.request.UpdateProductRequest;
import com.deneme.product_service.api.dto.response.CreatedProductResponse;
import com.deneme.product_service.api.dto.response.GetProductResponse;
import com.deneme.product_service.api.dto.response.UpdateProductResponse;
import com.deneme.product_service.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetProductResponse> getProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GetProductResponse>> getProducts(){
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreatedProductResponse> addProducts(@RequestBody CreatedProductRequest createdProductRequest){
        return new ResponseEntity<>(productService.addProduct(createdProductRequest), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateProductResponse> updateProducts(@RequestBody UpdateProductRequest updateProductRequest, @PathVariable Long id){
        return new ResponseEntity<>(productService.updateProduct(updateProductRequest,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducts(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


}
