package com.supercon.controller;

import com.supercon.model.Product;
import com.supercon.service.abstractions.IDiscountManager;
import com.supercon.service.builders.ProductBuilder;
import com.supercon.service.builders.abstractions.IProductBuilder;
import com.supercon.service.enums.TypeDiscount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.function.Function;

import static com.supercon.utils.Constants.*;

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
        Product productWithDiscount = productBuilder.getInstance(product)
                .setDiscountStrategy(discountManager.getDiscountStrategy(product.getProductCode()))
                .build();
        return new ResponseEntity<Product>(productWithDiscount, HttpStatus.OK);
    }

    @PutMapping(V1_DISCOUNT_MANAGER_PLAIN)
    public ResponseEntity<Product> plainDiscountToProduct(@RequestBody final Product product,
                                                          @RequestAttribute final TypeDiscount typeDiscount) {
        Function<Double, Double> discountStrategy
                = discountManager.plainDiscountToProduct(product.getProductCode(), typeDiscount.getDiscountStrategy());
        Product productWithDiscount = productBuilder.getInstance(product)
                .setDiscountStrategy(discountStrategy)
                .build();
        return new ResponseEntity<Product>(productWithDiscount, HttpStatus.OK);
    }


}
