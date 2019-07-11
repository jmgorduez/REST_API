package com.supercon.controller;

import com.supercon.model.Product;
import com.supercon.service.abstractions.IDiscountManager;
import com.supercon.service.builders.abstractions.IProductBuilder;
import com.supercon.service.enums.TypeDiscount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.supercon.utils.Constants.V1_DISCOUNT_MANAGER_APPLY;
import static com.supercon.utils.Constants.V1_DISCOUNT_MANAGER_PLAIN;

@RestController
public class DiscountController {

    private IDiscountManager discountManager;
    private IProductBuilder productBuilder;

    @Autowired
    public DiscountController(IDiscountManager discountManager, IProductBuilder productBuilder){
        this.discountManager = discountManager;
        this.productBuilder = productBuilder;
    }

    @PutMapping(V1_DISCOUNT_MANAGER_APPLY)
    public ResponseEntity<Product> applyDiscountToProduct(@RequestBody final Product product) {
        Product productWithDiscount = productBuilder.instance(product)
                .setDiscountStrategy(discountManager.getDiscountStrategy(product.getProductCode()))
                .build();
        return new ResponseEntity<>(productWithDiscount, HttpStatus.OK);
    }

    @PostMapping(V1_DISCOUNT_MANAGER_PLAIN)
    public ResponseEntity<Integer> plainDiscountToProduct(@RequestBody final Product product,
                                                          @RequestAttribute final TypeDiscount typeDiscount) {
        Integer index = discountManager.plainDiscountToProduct(product.getProductCode(), typeDiscount.getDiscountStrategy());
        return new ResponseEntity<>(index, HttpStatus.OK);
    }


}
