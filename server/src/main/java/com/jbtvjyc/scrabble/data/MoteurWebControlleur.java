package com.jbtvjyc.scrabble.data;

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
    private Identification[] joueurIds = new Identification[4];
    private int nbJoueurs = 0;

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
        if (nbJoueurs < 4) {
            System.out.println("Moteur > connexion acceptée de " + joueurId.getNom());
            joueurIds[nbJoueurs] = joueurId;
            nbJoueurs++;

            if (nbJoueurs == 4) {
                this.moteur.lancerPartie();
            }

            return true;
        } else {
            System.out.println("Moteur > connexion refusée de " + joueurId.getNom());

            return false;
        }


    }

    public MotPositionne demanderAuJoueurDeJouer(EtatDuJeu p, int joueur) {
        MotPositionne resultat = new MotPositionne();
        if (this.joueurIds.length > joueur && this.joueurIds[joueur] != null) {
            resultat = this.restTemplate.postForObject(this.joueurIds[joueur].getUrl() + "/jouer", p, MotPositionne.class);
        }

        return resultat;
    }

    public String getNomJoueur(int joueur) {
        String resultat = "[NULL]";
        if (this.joueurIds.length > joueur && this.joueurIds[joueur] != null) {
            resultat = this.joueurIds[joueur].getNom();
        }

        return resultat;
    }

    public void envoyerFin() {
        for (Identification joueurId : this.joueurIds) {
            if (joueurId != null) {
                this.restTemplate.exchange(joueurId.getUrl() + "/finir", HttpMethod.POST, null, Void.class);
            }
        }
    }
}
