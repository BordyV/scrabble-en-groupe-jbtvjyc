package com.jbtvjyc.scrabble.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ScrabbleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScrabbleApplication.class, args);
	}

	@Bean
	public CommandLineRunner aGame(@Autowired Moteur moteur) {
		return args -> {
			if (args.length > 0) {
				moteur.setExitOnFinish(true);
			}
		};
	}

}
