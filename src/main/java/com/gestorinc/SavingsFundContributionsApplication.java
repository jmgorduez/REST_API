package com.gestorinc;

import com.gestorinc.repository.IContributionPetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AportesAPVApplication implements CommandLineRunner {

	@Autowired
    IContributionPetitionRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(AportesAPVApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
