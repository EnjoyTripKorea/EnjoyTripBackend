package com.example.EnjoyTripBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EnjoyTripBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnjoyTripBackendApplication.class, args);
	}
}
