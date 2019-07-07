package com.supercon.service.builders;

import com.supercon.model.Customer;
import com.supercon.model.Order;
import com.supercon.service.builders.abstractions.IShoppingCart;
import com.supercon.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.supercon.utils.DataTestGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;

class ShoppingCartTest {

    private IShoppingCart shoppingCartUnderTest;

    @BeforeEach
    void setUp() {
        shoppingCartUnderTest = new ShoppingCart()
                .setCustomer(new Customer(JOHN));
    }

    @Test
    void addProduct() {
        IShoppingCart shoppingCartManagerExpected
                = new ShoppingCart()
                .setCustomer(new Customer(JOHN))
                .addProduct(PRODUCT_01_OBJECT);
        assertThat(shoppingCartUnderTest.addProduct(PRODUCT_01_OBJECT))
                .isEqualToComparingFieldByFieldRecursively(shoppingCartManagerExpected);
    }

    @Test
    void removeProduct() {
        IShoppingCart shoppingCartManagerExpected
                = new ShoppingCart()
                .setCustomer(new Customer(JOHN));
        shoppingCartUnderTest.addProduct(PRODUCT_01_OBJECT);
        assertThat(shoppingCartUnderTest.removeProduct(PRODUCT_01_OBJECT))
                .isEqualToComparingFieldByFieldRecursively(shoppingCartManagerExpected);
    }

    @Test
    void getProducts() {
        assertThat(shoppingCartUnderTest.getProducts())
                .isEmpty();
        shoppingCartUnderTest.addProduct(PRODUCT_01_OBJECT);
        shoppingCartUnderTest.addProduct(PRODUCT_02_OBJECT);
        assertThat(shoppingCartUnderTest.getProducts())
                .containsExactly(PRODUCT_01_OBJECT, PRODUCT_02_OBJECT);
    }

    @Test
    void getCustomer() {
        assertThat(shoppingCartUnderTest.getCustomer())
                .isEqualToComparingFieldByField(new Customer(JOHN));
    }

    @Test
    void checkout() {
        Order orderExpected = new ShoppingCart()
                .setCustomer(new Customer(JOHN))
                .addProduct(PRODUCT_01_OBJECT)
                .addProduct(PRODUCT_02_OBJECT).checkout();
        shoppingCartUnderTest
                .addProduct(PRODUCT_01_OBJECT)
                .addProduct(PRODUCT_02_OBJECT);
        assertThat(shoppingCartUnderTest.checkout())
                .isEqualToComparingFieldByFieldRecursively(orderExpected);
    }

    @Test
    void totalPrice() {
        shoppingCartUnderTest
                .addProduct(PRODUCT_01_OBJECT)
                .addProduct(PRODUCT_02_OBJECT);
        assertThat(shoppingCartUnderTest.totalPrice())
                .isEqualTo(_4_95);
    }

    @Test
    void loyaltyPointsEarned() {
        shoppingCartUnderTest
                .addProduct(PRODUCT_03_OBJECT)
                .addProduct(PRODUCT_04_OBJECT);
        assertThat(shoppingCartUnderTest.loyaltyPointsEarned())
                .isEqualTo(_13);
    }

    @Test
    void setDiscountStrategy() {
        assertThat(shoppingCartUnderTest.setDiscountStrategy(Constants::discount10Percent))
                .isEqualToComparingFieldByFieldRecursively(new ShoppingCart()
                        .setCustomer(new Customer(JOHN))
                        .setDiscountStrategy(Constants::discount10Percent));
    }
}