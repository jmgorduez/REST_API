package com.supercon.controller;

import com.supercon.model.Product;
import com.supercon.service.abstractions.IProductService;
import com.supercon.service.builders.abstractions.IProductBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.supercon.utils.Constants.*;

@RestController
public class ProductController {

    private IProductService productService;
    private IProductBuilder productBuilder;

    @Autowired
    public ProductController(IProductService productService,
                             IProductBuilder productBuilder) {
        this.productService = productService;
        this.productBuilder = productBuilder;
    }

    @GetMapping(V1_PRODUCTS)
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @GetMapping(V1_PRODUCTS_CODE)
    public ResponseEntity<Product> getProduct(@PathVariable final String code) {
        try {

            final Product product = productService.getProduct(code)
                    .orElseThrow(IllegalArgumentException::new);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (IllegalArgumentException error) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(V1_PACKAGE_PRODUCTS_CREATE)
    public ResponseEntity<Product> createProductPackage(@RequestAttribute String codeProduct,
                                                        @RequestAttribute String name,
                                                        @RequestBody List<Product> products) {
        productBuilder
                .setProductCode(codeProduct)
                .setName(name);
        products.stream()
                .forEach(productBuilder::addElement);
        return new ResponseEntity<>(productBuilder.build(), HttpStatus.OK);
    }
}
