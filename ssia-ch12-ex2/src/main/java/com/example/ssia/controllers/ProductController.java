package com.example.ssia.controllers;

import com.example.ssia.models.Product;
import com.example.ssia.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/sell")
    public List<Product> sellProducts() {
        List<Product> products = List.of(
                new Product("beer", "nikolai"),
                new Product("candy", "nikolai"),
                new Product("chocolate", "julien"));
        return productService.sellProducts(products);

    }
}
