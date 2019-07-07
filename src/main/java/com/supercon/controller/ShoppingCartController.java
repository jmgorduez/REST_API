package com.supercon.controller;

import com.supercon.model.Customer;
import com.supercon.model.Order;
import com.supercon.model.Product;
import com.supercon.service.builders.abstractions.IShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.supercon.utils.Constants.*;

@RestController
public class ShoppingCartController {

    private IShoppingCart shoppingCart;

    @Autowired
    public ShoppingCartController(IShoppingCart shoppingCart){

        this.shoppingCart = shoppingCart;
    }

    @PostMapping(V1_SHOPPING_CART_CREATE)
    public ResponseEntity<Order> createShoppingCart(@RequestBody final Customer customer){
        shoppingCart.setCustomer(customer);
        return new ResponseEntity<Order>(shoppingCart.checkout(), HttpStatus.OK);
    }

    @PutMapping(V1_SHOPPING_CART_ADD_PRODUCT)
    public ResponseEntity<Order> addProductToShoppingCart(@RequestBody final Product product){
        shoppingCart.addProduct(product);
        return new ResponseEntity<Order>(shoppingCart.checkout(), HttpStatus.OK);
    }

    @DeleteMapping(V1_SHOPPING_CART_REMOVE_PRODUCT)
    public ResponseEntity<Order> removeProductToShoppingCart(@RequestBody final Product product){
        shoppingCart.removeProduct(product);
        return new ResponseEntity<Order>(shoppingCart.checkout(), HttpStatus.OK);
    }

    @GetMapping(V1_SHOPPING_CART)
    public ResponseEntity<Order> getShoppingCart(){
        return new ResponseEntity<Order>(shoppingCart.checkout(), HttpStatus.OK);
    }
}
