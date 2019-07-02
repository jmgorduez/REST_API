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

@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

    /*
    * TODO It should return a ResponseEntity<List<String>>
    * */
    @GetMapping(V1_PRODUCTS)
    public List<String> getProducts() {
        return productService.getProductCodes();
    }

    @GetMapping("/v1/products/{code}")
    public ResponseEntity<Optional<Product>> getProduct(@PathVariable final String code) {
        final Optional<Product> product = productService.getProduct(code);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
