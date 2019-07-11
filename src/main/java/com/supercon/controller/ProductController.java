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
        }catch (IllegalArgumentException error){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(V1_PRODUCTS_SET_CODE)
    public ResponseEntity<Product> setCodeProduct(@RequestBody String codeProduct){
        productBuilder.setProductCode(codeProduct);
        return new ResponseEntity<>(productBuilder.build(), HttpStatus.OK);
    }

    @PostMapping(V1_PRODUCTS_SET_NAME)
    public ResponseEntity<Product> setNameProduct(@RequestBody String nameProduct){
        productBuilder.setName(nameProduct);
        return new ResponseEntity<>(productBuilder.build(), HttpStatus.OK);
    }

    @PostMapping(V1_PRODUCTS_ADD_ELEMENT)
    public ResponseEntity<Product> addElement(@RequestBody String codeProduct){
        Product product = productService.getProduct(codeProduct).get();
        productBuilder.addElement(product);
        return new ResponseEntity<>(productBuilder.build(), HttpStatus.OK);
    }

}
