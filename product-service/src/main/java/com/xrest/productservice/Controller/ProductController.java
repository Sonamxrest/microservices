package com.xrest.productservice.Controller;

import com.xrest.productservice.dto.ProductRequest;
import com.xrest.productservice.dto.ProductResponseDto;
import com.xrest.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<ProductResponseDto> getAllProd() {
        return productService.getAllProd();
    }
}
