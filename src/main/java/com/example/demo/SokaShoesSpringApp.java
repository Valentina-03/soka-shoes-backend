package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SokaShoesSpringApp {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(SokaShoesSpringApp.class, args);
	}

}