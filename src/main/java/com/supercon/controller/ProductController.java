package com.supercon.controller;

import com.supercon.model.Product;
import com.supercon.service.abstractions.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static com.supercon.utils.Constants.V1_PRODUCTS;
import static com.supercon.utils.Constants.V1_PRODUCTS_CODE;

@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping(V1_PRODUCTS)
    public ResponseEntity<List<String>> getProducts() {
        return new ResponseEntity<>(productService.getProductCodes(), HttpStatus.OK);
    }

    @GetMapping(V1_PRODUCTS_CODE)
    public ResponseEntity<Optional<Product>> getProduct(@PathVariable final String code) {
        final Optional<Product> product = productService.getProduct(code);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
