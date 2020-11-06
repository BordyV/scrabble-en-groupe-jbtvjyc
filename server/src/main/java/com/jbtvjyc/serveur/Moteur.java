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
    Serveur ctrl;

    Thread partie ;

    EtatDuJeu etatDuJeu;

    public void lancerPartie() {
        if (partie == null) {
            System.out.println("Moteur > la partie est démarrée");
            etatDuJeu = new EtatDuJeu();
            partie = new Thread(this);
            partie.start();
        } else {
            System.out.println("Moteur > la partie est déjà démarrée");
        }
    }

    @Override
    public void run() {
        for(int nbTour = 0; nbTour < 2; nbTour++) {
            etatDuJeu.ajouterLettres('a','b','c','d','m','o','t');
            MotPositionne motJoué = ctrl.demanderAuJoueurDeJoueur(getPlateau()) ;
            System.out.println("Moteur > "+ctrl.getNomJoueur()+" a joué : "+motJoué+ " (il n'y a pas de vérification)");
            etatDuJeu.addMotPlacé(motJoué);
        }
        System.out.println("Moteur > la partie est finie "+ etatDuJeu);
        partie = null;

        ctrl.envoyerFin();
        // fin brutale (pour abréger sur travis).
        System.exit(0);
    }


    public EtatDuJeu getPlateau() {
        return etatDuJeu;
    }

}
