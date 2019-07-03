package com.supercon.service;

import com.supercon.service.builders.ProductBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.NoSuchElementException;

import static com.supercon.utils.DataTestGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductServiceTest {

    private ProductService productServiceUnderTest;

    @BeforeAll
    public void setup() {
        productServiceUnderTest = new ProductService(PRODUCTS);
    }

    @Test
    public void getProductCodes_shouldReturnAllCodes() throws Exception {
        assertThat(productServiceUnderTest.getProductCodes())
                .containsExactly(PROD_01, PROD_02);
    }

    @Test
    public void getProduct_shouldReturnProductForKnownCode() throws Exception {
        assertThat(productServiceUnderTest.getProduct(PROD_01).get())
                .isEqualToComparingFieldByField(new ProductBuilder()
                        .setProductCode(PROD_01)
                        .setName(PRODUCT_01)
                        .setPrice(_1_50).build());
    }

    @Test
    public void getProduct_shouldReturnNullForUnknownCode() throws Exception {
        assertThatThrownBy(() -> productServiceUnderTest.getProduct(PROD_03).get())
                .isInstanceOf(NoSuchElementException.class);
    }

}