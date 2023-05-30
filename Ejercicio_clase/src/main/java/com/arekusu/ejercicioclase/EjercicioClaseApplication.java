package com.arekusu.ejercicioclase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableJpaRepositories("com.example.repository") 
@EntityScan("com.example.entity") 
@ComponentScan("com.arekusu.ejercicioclase.services")
public class EjercicioClaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(EjercicioClaseApplication.class, args);
	}

}
