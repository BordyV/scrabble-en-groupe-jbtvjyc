package com.jbtvjyc.scrabble.client;

import com.jbtvjyc.scrabble.data.Identification;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplateClient(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner unClient(RestTemplate restTemplateClient) {
        return args -> {
            //connexion
            Identification ident = new Identification("Mopolo le roi des mots", "http://localhost:8081/");
            Boolean etatConnexion = restTemplateClient.postForObject("http://localhost:8080/connexion/", ident, Boolean.class);
            System.out.println("etat de la connexion -> " + etatConnexion);
        };
    }
}