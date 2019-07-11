package com.supercon.service;

import com.supercon.model.Product;
import com.supercon.service.abstractions.IDiscountManager;
import com.supercon.service.builders.ProductBuilder;
import com.supercon.service.builders.abstractions.IProductBuilder;
import com.supercon.utils.Constants;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static com.supercon.utils.Constants.*;
import static com.supercon.utils.DataTestGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductServiceTest {

    private ProductService productServiceUnderTest;
    private IDiscountManager discountManager;
    private IProductBuilder productBuilder;

    @BeforeAll
    public void setup() {
        discountManager = mock(DiscountManager.class);
        when(discountManager.getDiscountStrategy(PROD_01))
                .thenReturn(Constants::discount10Percent);
        when(discountManager.getDiscountStrategy(any()))
                .thenReturn(Double::doubleValue);

        productBuilder = mock(ProductBuilder.class);
        when(productBuilder.instance(any()))
                .thenReturn(new ProductBuilder()
                        .instance(PRODUCT_01_OBJECT));
        when(productBuilder.setDiscountStrategy(any()))
                .thenReturn(new ProductBuilder()
                        .instance(PRODUCT_01_OBJECT)
                        .setDiscountStrategy(Constants::discount10Percent));
        when(productBuilder.build())
                .thenReturn(new ProductBuilder()
                        .instance(PRODUCT_01_OBJECT)
                        .setDiscountStrategy(Constants::discount10Percent)
                        .build());

        productServiceUnderTest = new ProductService(discountManager, productBuilder, Arrays.asList(PRODUCT_01_OBJECT));
    }

    @Test
    public void getProducts_shouldReturnAllProducts() throws Exception {
        Product product01Expected = new Product(
                discount10Percent(_1_67)
                , PROD_01, PRODUCT_01, _1);
        assertThat(productServiceUnderTest.getProducts().get(_0))
                .isEqualToComparingFieldByFieldRecursively(product01Expected);
    }

    @Test
    public void getProduct_shouldReturnProductForKnownCode() throws Exception {
        assertThat(productServiceUnderTest.getProduct(PROD_01).get())
                .isEqualToComparingFieldByField(new Product(_1_50, PROD_01, PRODUCT_01, _1));
    }

    @Test
    public void getProduct_shouldReturnNullForUnknownCode() throws Exception {
        assertThatThrownBy(() -> productServiceUnderTest.getProduct(PROD_03).get())
                .isInstanceOf(NoSuchElementException.class);
    }

}