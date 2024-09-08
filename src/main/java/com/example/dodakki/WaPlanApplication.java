package com.example.dodakki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WaPlanApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaPlanApplication.class, args);
	}

}
