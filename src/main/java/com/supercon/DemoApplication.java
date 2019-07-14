package com.supercon;

import com.supercon.model.Customer;
import com.supercon.repository.ICustomerRepository;
import io.spring.gradle.dependencymanagement.org.codehaus.plexus.util.cli.Commandline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	ICustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
