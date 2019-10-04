package com.gestorinc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SavingsFundContributionsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SavingsFundContributionsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
