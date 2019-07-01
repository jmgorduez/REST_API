package com.supercon.service;

import com.supercon.model.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ProductServiceTest {

    private ProductService productService;

    @Before
    public void setup() throws Exception {
        productService = new ProductService(Arrays.asList(
                new Product(1.50, "PROD_01", "Product 01"),
                new Product(3.45, "PROD_02", "Product 02")
        ));
    }

    @Test
    public void getProductCodes_shouldReturnAllCodes() throws Exception {
        List<String> codes = productService.getProductCodes();
        assertEquals(2, codes.size());
        assertEquals("PROD_01", codes.get(0));
        assertEquals("PROD_02", codes.get(1));
    }

    @Test
    public void getProduct_shouldReturnProductForKnownCode() throws Exception {
        Product product = productService.getProduct("PROD_01");
        assertEquals("PROD_01", product.getProductCode());
        assertEquals("Product 01", product.getName());
        assertEquals(1.50, product.getPrice(), 0.00);
    }

    @Test
    public void getProduct_shouldReturnNullForUnknownCode() throws Exception {
        Product product = productService.getProduct("PROD_03");
        assertNull(product);
    }

}