package com.citibank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.citibank" })
public class CityProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityProjectApplication.class, args);
	}

}
