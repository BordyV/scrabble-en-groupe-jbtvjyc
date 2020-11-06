package com.jbtvjyc.serveur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.jbtvjyc.scrabble.data.MotPositionne;
import com.jbtvjyc.scrabble.data.EtatDuJeu;

@Component
@Scope("singleton")
public class Moteur implements Runnable {
    @Autowired
    MoteurWebControlleur ctrl;

    Thread partie ;

    EtatDuJeu etatDuJeu;

    public void lancerPartie() {
        if (this.partie == null) {
            System.out.println("Moteur > la partie est démarrée");
            this.etatDuJeu = new EtatDuJeu();
            this.partie = new Thread(this);
            this.partie.start();
        } else {
            System.out.println("Moteur > la partie est déjà démarrée");
        }
    }

    @Override
    public void run() {
        for(int nbTour = 0; nbTour < 2; nbTour++) {
            this.etatDuJeu.ajouterLettres('a','b','c','d','m','o','t');
            MotPositionne motJoue = this.ctrl.demanderAuJoueurDeJoueur(getPlateau()) ;
            System.out.println("Moteur > " + this.ctrl.getNomJoueur() + " a joué : " + motJoue + " (il n'y a pas de vérification)");
            this.etatDuJeu.addMotPlace(motJoue);
        }
        System.out.println("Moteur > la partie est finie "+ this.etatDuJeu);
        this.partie = null;

        this.ctrl.envoyerFin();
        // fin brutale (pour abréger sur travis).
        System.exit(0);
    }


    public EtatDuJeu getPlateau() {
        return this.etatDuJeu;
    }

}
