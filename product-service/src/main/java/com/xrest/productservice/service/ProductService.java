package com.xrest.productservice.service;

import com.xrest.productservice.Model.Product;
import com.xrest.productservice.Repository.ProductRepository;
import com.xrest.productservice.dto.ProductRequest;
import com.xrest.productservice.dto.ProductResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {


    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .name(productRequest.getName()).build();
        productRepository.save(product);
        log.info("Product {} is saved", product.getName());
    }

    public List<ProductResponseDto> getAllProd() {
        List<Product> products = productRepository.findAll();
        return          products.stream().map(this::mapToProductRespone).collect(Collectors.toList());
    }

    private ProductResponseDto mapToProductRespone(Product p) {
        return ProductResponseDto.builder().id(p.getId()).description(p.getDescription()).price(p.getPrice()).name(p.getName()).build();
    }


}
