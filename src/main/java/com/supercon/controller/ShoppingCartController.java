package com.supercon.controller;

import com.supercon.model.Customer;
import com.supercon.model.Order;
import com.supercon.service.builders.ShoppingCart;
import com.supercon.service.builders.abstractions.IShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.supercon.utils.Constants.V1_SHOPPING_CART_CUSTOMER_NAME;

@RestController
public class ShoppingCartController {

    private IShoppingCart shoppingCart;

    public ShoppingCartController(IShoppingCart shoppingCart){
        this.shoppingCart = shoppingCart;
    }

    @GetMapping(V1_SHOPPING_CART_CUSTOMER_NAME)
    public ResponseEntity<Order> createShoppingCart(@PathVariable final String customerName){
        shoppingCart.setCustomer(new Customer(customerName));
        return new ResponseEntity<Order>(shoppingCart.checkout(), HttpStatus.OK);
    }
}
