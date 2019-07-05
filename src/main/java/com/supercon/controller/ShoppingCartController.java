package com.supercon.controller;

import com.supercon.model.Customer;
import com.supercon.model.Order;
import com.supercon.model.Product;
import com.supercon.service.abstractions.IProductService;
import com.supercon.service.builders.abstractions.IShoppingCart;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.supercon.utils.Constants.*;

@RestController
public class ShoppingCartController {

    private IShoppingCart shoppingCart;
    private IProductService productService;

    public ShoppingCartController(IShoppingCart shoppingCart, IProductService productService){

        this.shoppingCart = shoppingCart;
        this.productService = productService;
    }

    @GetMapping(V1_SHOPPING_CART_CUSTOMER_NAME)
    public ResponseEntity<Order> createShoppingCart(@PathVariable final String customerName){
        shoppingCart.setCustomer(new Customer(customerName));
        return new ResponseEntity<Order>(shoppingCart.checkout(), HttpStatus.OK);
    }

    @GetMapping(V1_SHOPPING_CART_ADD_PRODUCT)
    public ResponseEntity<Order> addProductToShoppingCart(@PathVariable final String codeProduct){
        Product product = productService.getProduct(codeProduct)
                .orElseThrow(IllegalArgumentException::new);
        shoppingCart.addProduct(product);
        return new ResponseEntity<Order>(shoppingCart.checkout(), HttpStatus.OK);
    }

    @GetMapping(V1_SHOPPING_CART_REMOVE_PRODUCT)
    public ResponseEntity<Order> removeProductToShoppingCart(@PathVariable final String codeProduct){
        Product product = productService.getProduct(codeProduct)
                .orElseThrow(IllegalArgumentException::new);
        shoppingCart.removeProduct(product);
        return new ResponseEntity<Order>(shoppingCart.checkout(), HttpStatus.OK);
    }
}
