package com.project.welltrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WelltripApplication {

	public static void main(String[] args) {
		SpringApplication.run(WelltripApplication.class, args);
	}

}
