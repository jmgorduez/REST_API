package com.supercon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.supercon.service.DiscountManager;
import com.supercon.service.abstractions.IDiscountManager;
import com.supercon.service.builders.ProductBuilder;
import com.supercon.service.builders.abstractions.IProductBuilder;
import com.supercon.service.enums.TypeDiscount;
import com.supercon.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static com.supercon.service.enums.TypeDiscount.DISCOUNT_10_PERCENT;
import static com.supercon.utils.Constants.*;
import static com.supercon.utils.DataTestGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class DiscountControllerTest {

    private MockMvc mockMvc;

    private IDiscountManager discountManager;
    private IProductBuilder productBuilder;


    @BeforeEach
    protected void setUp() {
        discountManager = mock(DiscountManager.class);
        when(discountManager.getDiscountStrategy(PROD_01))
                .thenReturn(Constants::discount10Percent);
        when(discountManager.plainDiscountToProduct(any(), any()))
                .thenReturn(_2);

        productBuilder = mock(ProductBuilder.class);
        when(productBuilder.getInstance(any()))
                .thenReturn(new ProductBuilder()
                        .getInstance(PRODUCT_01_OBJECT));
        when(productBuilder.setDiscountStrategy(any()))
                .thenReturn(new ProductBuilder()
                        .getInstance(PRODUCT_01_OBJECT)
                        .setDiscountStrategy(Constants::discount10Percent));
        when(productBuilder.build())
                .thenReturn(new ProductBuilder()
                        .getInstance(PRODUCT_01_OBJECT)
                        .setDiscountStrategy(Constants::discount10Percent)
                        .build());

        mockMvc = standaloneSetup(new DiscountController(discountManager, productBuilder)).build();
    }

    @Test
    void applyDiscountToProduct() throws Exception {

        RequestBuilder requestBuilder = put(V1_DISCOUNT_MANAGER_APPLY)
                .content(new ObjectMapper().writeValueAsString(PRODUCT_01_OBJECT))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        verify(discountManager, times(_1))
                .getDiscountStrategy(PRODUCT_01_OBJECT.getProductCode());

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(PRODUCT_WITH_DISCOUNT_JSON);
    }

    @Test
    void plainDiscountToProduct() throws Exception {
        RequestBuilder requestBuilder = post(V1_DISCOUNT_MANAGER_PLAIN)
                .content(new ObjectMapper()
                        .writeValueAsString(PRODUCT_01_OBJECT))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .requestAttr(TYPE_DISCOUNT, DISCOUNT_10_PERCENT)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        verify(discountManager, times(_1))
                .plainDiscountToProduct(PRODUCT_01_OBJECT.getProductCode(),
                        DISCOUNT_10_PERCENT.getDiscountStrategy());

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(_2.toString());
    }
}