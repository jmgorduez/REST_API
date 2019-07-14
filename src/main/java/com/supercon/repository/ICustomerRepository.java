package com.supercon.repository;

import com.supercon.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICustomerRepository extends MongoRepository<Customer, String>{

    Customer findByName(String name);
}
