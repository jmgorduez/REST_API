package com.supercon.model;

import com.supercon.service.builders.ProductBuilder;
import com.supercon.service.builders.abstractions.IProductBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static com.supercon.utils.DataTestGenerator.*;
import static com.supercon.utils.DataTestGenerator._13;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductPackageTest {

    private ProductPackage productPackageUnderTest;

    @BeforeEach
    void setUp() {
        productPackageUnderTest = new ProductPackage(_45_80,
                PROD_01, PRODUCT_01, _9,
                Arrays.asList(PRODUCT_02_OBJECT));
    }

    @Test
    void getElements() {
        assertThat(productPackageUnderTest.getElements())
                .contains(PRODUCT_02_OBJECT);
    }
}