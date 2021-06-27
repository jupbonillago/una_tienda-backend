package com.ingesoft2;

import com.ingesoft2.repositories.PersonRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = PersonRepository.class)
public class BackendApplication {
	
	 
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
