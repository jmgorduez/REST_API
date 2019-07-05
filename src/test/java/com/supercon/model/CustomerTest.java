package com.supercon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.supercon.utils.DataTestGenerator.JOHN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private Customer customerUnderTest;

    @BeforeEach
    void setUp(){
        customerUnderTest = new Customer(JOHN);
    }

    @Test
    void getName() {
        assertThat(customerUnderTest.getName())
                .isEqualTo(JOHN);
    }
}