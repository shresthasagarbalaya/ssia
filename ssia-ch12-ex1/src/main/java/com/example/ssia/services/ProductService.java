package com.example.ssia.services;

import com.example.ssia.models.Product;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @PreFilter("filterObject.owner == authentication.name")
    public List<Product> sellProducts(List<Product> products) {
        //sell products and return the sold products list
        return products;
    }
}
