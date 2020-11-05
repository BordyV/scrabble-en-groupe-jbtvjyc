package com.jbtvjyc.scrabble.client;

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
    public CommandLineRunner client(RestTemplate restTemplateClient) {
        return args -> {
            Integer valueClient = restTemplateClient.getForObject("http://localhost:8080",Integer.class);
            System.out.println("client a la valeur lue -> " + valueClient);
        };
    }
}