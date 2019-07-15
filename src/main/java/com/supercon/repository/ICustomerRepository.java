package com.supercon.repository;

import com.supercon.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerRepository extends CrudRepository<Customer, String> {

    Customer findByName(String name);
}
