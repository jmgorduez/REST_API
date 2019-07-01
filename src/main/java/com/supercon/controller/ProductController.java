package com.supercon.controller;

import com.supercon.model.Product;
import com.supercon.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/v1/products")
    public List<String> getProducts() {
        return productService.getProductCodes();
    }

    @GetMapping("/v1/products/{code}")
    public ResponseEntity<Product> getProduct(@PathVariable final String code) {
        final Product product = productService.getProduct(code);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
