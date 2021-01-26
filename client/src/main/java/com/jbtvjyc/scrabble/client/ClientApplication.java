package com.jbtvjyc.scrabble.client;

import com.jbtvjyc.scrabble.data.Identification;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ClientApplication implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    static int port = 8081;
    static String nom = "Mopolo le Roi des Mots";
    static String serverip = "localhost";
    static String ownip = "localhost";
    static String autoconnect = "FALSE";

    public static void main(String[] args) throws InterruptedException {
        /*if (args.length > 1 && !args[0].equals("autoconnect")) {
            ClientApplication.port = Integer.parseInt(args[0]);
            ClientApplication.nom = args[1];
        }*/

        TimeUnit.SECONDS.sleep(2);

        if (System.getenv("PORT") != null) {
            ClientApplication.port = Integer.parseInt(System.getenv("PORT"));
        }

        if (System.getenv("NOM") != null) {
            ClientApplication.nom = System.getenv("NOM");
        }

        if (System.getenv("SERVERIP") != null) {
            ClientApplication.serverip = System.getenv("SERVERIP");
        }

        if (System.getenv("AUTOCONNECT") != null) {
            ClientApplication.autoconnect = System.getenv("AUTOCONNECT");
        }

        if (System.getenv("OWNIP") != null) {
            ClientApplication.ownip = System.getenv("OWNIP");
        }

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
            System.out.println("C'est le moment");
            System.out.println(ClientApplication.autoconnect);
            System.out.println(ClientApplication.autoconnect.equals("autoconnect"));
            if(((args.length > 0) && args[0].equals("autoconnect")) || (ClientApplication.autoconnect.equals("autoconnect"))) {
                Identification ident = new Identification(ClientApplication.nom, "http://" + ClientApplication.ownip + ":"+ClientApplication.port+"/");
                Boolean etatConnexion = restTemplateClient.postForObject("http://" + ClientApplication.serverip + ":8080/connexion/", ident, Boolean.class);
                System.out.println("etat de la connexion -> " + etatConnexion);
            }
        };
    }

    @Override
    public void customize(ConfigurableServletWebServerFactory server) {
        server.setPort(ClientApplication.port);
        System.out.println("PORT DU CLIENT: " + ClientApplication.port);
    }
}