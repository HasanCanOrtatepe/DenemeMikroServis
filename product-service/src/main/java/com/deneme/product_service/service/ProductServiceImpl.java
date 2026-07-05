package com.deneme.product_service.service;

import com.deneme.product_service.api.dto.request.CreatedProductRequest;
import com.deneme.product_service.api.dto.request.UpdateProductRequest;
import com.deneme.product_service.api.dto.response.CreatedProductResponse;
import com.deneme.product_service.api.dto.response.GetProductResponse;
import com.deneme.product_service.api.dto.response.UpdateProductResponse;
import com.deneme.product_service.core.exceptions.ResourceNotFound;
import com.deneme.product_service.model.Product;
import com.deneme.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public GetProductResponse getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFound("Product not found")
        );
        if (!product.getActive()) {
            throw new ResourceNotFound("Product not found");
        }
        GetProductResponse getProductResponse = new GetProductResponse();
        getProductResponse.setId(product.getId());
        getProductResponse.setProductDescription(product.getProductDescription());
        getProductResponse.setProductCategory(product.getProductCategory());
        getProductResponse.setProductPrice(product.getProductPrice());
        getProductResponse.setProductName(product.getProductName());
        getProductResponse.setProductStock(product.getProductStock());


        return getProductResponse;
    }

    @Override
    public List<GetProductResponse> getProducts() {
        List<GetProductResponse> getProductResponses=productRepository.findAll().stream().
                filter(Product::getActive).map(product -> new GetProductResponse(
                product.getId(),
                product.getProductName(),
                product.getProductDescription(),
                product.getProductPrice(),
                product.getProductCategory(),
                product.getProductStock()
        )).toList();

        return getProductResponses;
    }

    @Override
    public CreatedProductResponse addProduct(CreatedProductRequest createdProductRequest) {
        Product product = new Product();
        product.setProductDescription(createdProductRequest.getProductDescription());
        product.setProductCategory(createdProductRequest.getProductCategory());
        product.setProductPrice(createdProductRequest.getProductPrice());
        product.setProductStock(createdProductRequest.getProductStock());
        product.setProductName(createdProductRequest.getProductName());
        Product product1=productRepository.save(product);

        CreatedProductResponse createdProductResponse = new CreatedProductResponse();
        createdProductResponse.setId(product1.getId());
        createdProductResponse.setProductDescription(product1.getProductDescription());
        createdProductResponse.setProductCategory(product1.getProductCategory());
        createdProductResponse.setProductPrice(product1.getProductPrice());
        createdProductResponse.setProductStock(product1.getProductStock());
        createdProductResponse.setProductName(product1.getProductName());
        return createdProductResponse;

    }

    @Override
    public UpdateProductResponse updateProduct(UpdateProductRequest updateProductRequest,Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFound("Product not found")
        );
        product.setProductDescription(updateProductRequest.getProductDescription());
        product.setProductCategory(updateProductRequest.getProductCategory());
        product.setProductPrice(updateProductRequest.getProductPrice());
        product.setProductStock(updateProductRequest.getProductStock());
        product.setProductName(updateProductRequest.getProductName());
        productRepository.save(product);
        UpdateProductResponse updateProductResponse = new UpdateProductResponse();
        updateProductResponse.setId(product.getId());
        updateProductResponse.setProductDescription(product.getProductDescription());
        updateProductResponse.setProductCategory(product.getProductCategory());
        updateProductResponse.setProductPrice(product.getProductPrice());
        updateProductResponse.setProductStock(product.getProductStock());
        updateProductResponse.setProductName(product.getProductName());
        return updateProductResponse;
    }

    @Override
    public void deleteProduct(Long id) {
        Product product=productRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFound("Product not found")
        );
        product.setActive(false);
        productRepository.save(product);
    }
}
