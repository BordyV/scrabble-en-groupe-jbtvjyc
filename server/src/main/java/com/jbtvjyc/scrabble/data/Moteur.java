package com.jbtvjyc.scrabble.data;

import com.jbtvjyc.scrabble.serveur.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
public class Moteur implements Runnable {
    @Autowired
    MoteurWebControlleur ctrl;
    Verification verification;
    Thread partie;

    EtatDuJeu etatDuJeu;
    StatistiqueGlobale statsGlobal;
    Mots lesMotsPossibles;
    int nbPartie;
    private boolean exitOnFinish = false;

    public void lancerPartie() {
        if (this.partie == null) {
            System.out.println("Moteur > la partie est démarrée");
            this.partie = new Thread(this);
            this.partie.start();
            this.nbPartie =5;
            this.statsGlobal = new StatistiqueGlobale(nbPartie);
            this.lesMotsPossibles = new Mots();
        } else {
            System.out.println("Moteur > la partie est déjà démarrée");
        }
    }

    @Override
    public void run() {
        for(int partie =0; partie<this.nbPartie;partie++) {
            this.etatDuJeu = new EtatDuJeu();
            for (int nbTour = 0; nbTour < 100; nbTour++) {
                if (nbTour == 0) {
                    this.etatDuJeu.getInventaire().setLettres(new ArrayList<>());
                    this.etatDuJeu.piocherLettre();
                }
                MotPositionne motJoue = this.ctrl.demanderAuJoueurDeJouer(this.getEtatDuJeu());
                if (motJoue != null)
                    this.verification = new Verification(this.etatDuJeu.getInventaire().getLettres(), motJoue, this.etatDuJeu.getPlateau(), this.lesMotsPossibles);

                //si la verification du mot marche
                if (motJoue != null && this.verification.verifMot()) {
                    System.out.println("Moteur > " + this.ctrl.getNomJoueur() + " a joué : " + motJoue);
                    this.etatDuJeu.addMotPlace(motJoue);
                    this.etatDuJeu.inventaire.stats.ajoutMotPlacer();
                    this.etatDuJeu.inventaire.stats.tailleMotPlusLong(motJoue);
                    this.etatDuJeu.inventaire.stats.tailleMotPlusCourt(motJoue);
                    this.etatDuJeu.inventaire.stats.tailleMoyenneMot(motJoue);
                } else if (motJoue != null) {
                    //TODO DEMANDER S'IL VEUT CHANGER SES LETTRES OU RETENTER QUELQUE CHOSE
                    System.out.println("Moteur > " + this.ctrl.getNomJoueur() + " n'a pas pu jouer car son mot n'est pas posable ou possible");
                } else {
                    System.out.println("Moteur > " + this.ctrl.getNomJoueur() + " n'a pas trouver de mot et pioche ");
                    //TODO DEMANDER AU JOUEUR QUELLES LETTRES IL DOIT CHANGER
                    this.etatDuJeu.changerLettres();
                }
            }
            System.out.println(this.etatDuJeu.getPlateau());
            this.statsGlobal.setStats(this.etatDuJeu.inventaire.stats);
        }
        System.out.println("Moteur > Les partie sont finies ");
        System.out.println(statsGlobal.toString());
        this.partie = null;

        this.ctrl.envoyerFin();
        // fin brutale (pour abréger sur travis).
        if (getExitOnFinish()) System.exit(0);
    }
    public void setExitOnFinish(boolean exitOnFinish) {
        this.exitOnFinish = exitOnFinish;
    }
    public boolean getExitOnFinish() {
        return this.exitOnFinish;
    }


    public EtatDuJeu getEtatDuJeu() {
        return this.etatDuJeu;
    }

}
