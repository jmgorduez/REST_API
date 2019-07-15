package com.supercon.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {

    @Id
    private final String name;

    public Customer(@JsonProperty("name") String name) {
        this.name = name;
    }

    public Customer(){
        name = "";
    }

    public String getName() {
        return name;
    }
}
