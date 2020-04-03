package br.com.compasso.pautas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class PautasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PautasApplication.class, args);
	}

}
