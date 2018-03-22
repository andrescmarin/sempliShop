package com.sempli.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.sempli.backend"})
@EntityScan(basePackages = {"com.sempli.logic"})
@ComponentScan(basePackages = {"com.sempli.logic","com.sempli.backend","com.sempli.frontend"})
public class shopApp {

	public static void main(String[] args) {
		SpringApplication.run(shopApp.class);
	}

}
