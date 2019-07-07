package com.supercon.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Customer implements Serializable {

    private final String name;

    public Customer(@JsonProperty("name") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
