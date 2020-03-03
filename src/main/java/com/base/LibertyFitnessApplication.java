package com.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class LibertyFitnessApplication  {

	public static void main(String[] args) {
		SpringApplication.run(LibertyFitnessApplication.class, args);
	}

}
