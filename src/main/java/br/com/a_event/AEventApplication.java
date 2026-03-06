package br.com.a_event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AEventApplication {

	public static void main(String[] args) {
		SpringApplication.run(AEventApplication.class, args);
	}

}
