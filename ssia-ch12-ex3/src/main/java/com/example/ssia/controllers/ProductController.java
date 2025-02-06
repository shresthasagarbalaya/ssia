package com.example.ssia.controllers;

import com.example.ssia.models.Product;
import com.example.ssia.services.ProductServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductServices productServices;

    public ProductController(ProductServices productServices) {
        this.productServices = productServices;
    }

    @GetMapping("/find")
    public List<Product> findProducts() {
        return productServices.findProducts();
    }
}
