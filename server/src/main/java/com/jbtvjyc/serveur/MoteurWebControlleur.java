package com.jbtvjyc.serveur;

import com.jbtvjyc.scrabble.data.EtatDuJeu;
import com.jbtvjyc.scrabble.data.Identification;
import com.jbtvjyc.scrabble.data.MotPositionne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MoteurWebControlleur {
    int value = 0;

    private Identification joueurId;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
        return builder.build();
    }
    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    Moteur moteur;

    @PostMapping("/connexion/")
    public boolean getValue(@RequestBody Identification joueurId) {
        System.out.println("Moteur > connexion acceptée de " + joueurId.getNom());
        this.joueurId = joueurId;
        this.moteur.lancerPartie();

        return true;
    }

    public MotPositionne demanderAuJoueurDeJoueur(EtatDuJeu p) {
        MotPositionne resultat = new MotPositionne();
        if (this.joueurId != null) {
            resultat = this.restTemplate.postForObject(this.joueurId.getUrl() + "/jouer", p, MotPositionne.class);
        }

        return resultat;
    }

    public String getNomJoueur() {
        String resultat = "[NULL]";
        if (this.joueurId != null) {
            resultat = this.joueurId.getNom();
        }

        return resultat;
    }

    public void envoyerFin() {
        this.restTemplate.exchange(this.joueurId.getUrl() + "/finir", HttpMethod.POST, null, Void.class);
    }
}
