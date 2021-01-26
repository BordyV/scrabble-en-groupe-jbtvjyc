package com.jbtvjyc.scrabble.data;

import com.jbtvjyc.scrabble.serveur.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Scope("singleton")
public class Moteur implements Runnable {
    @Autowired
    MoteurWebControlleur ctrl;
    Verification verification;
    Thread partie;

    EtatDuJeu etatDuJeu;
    Mots lesMotsPossibles;
    private int currentPlayer;
    private boolean exitOnFinish = false;

    public void lancerPartie() {
        if (this.partie == null) {
            System.out.println("Moteur > la partie est démarrée");
            this.etatDuJeu = new EtatDuJeu();
            this.partie = new Thread(this);
            this.lesMotsPossibles = new Mots();
            this.currentPlayer = 0;
            this.partie.start();
        } else {
            System.out.println("Moteur > la partie est déjà démarrée");
        }
    }

    @Override
    public void run() {
        for(Inventaire inv: this.etatDuJeu.getInventaires()) {
            inv.setLettres(new ArrayList<>());
            this.etatDuJeu.piocherLettre();
            this.etatDuJeu.currJoueur++;
        }

        for(int nbTour = 0; nbTour < 500; nbTour++) {
            this.currentPlayer = (this.currentPlayer + 1) % 4;
            this.etatDuJeu.currJoueur = this.currentPlayer;
            MotPositionne motJoue = this.ctrl.demanderAuJoueurDeJouer(this.getEtatDuJeu(), this.currentPlayer);
            if(motJoue != null)
                this.verification = new Verification(this.etatDuJeu.getInventaire().getLettres(), motJoue,this.etatDuJeu.getPlateau(), this.lesMotsPossibles);

            //si la verification du mot marche
            if(motJoue != null && this.verification.verifMot()) {
                System.out.println("Moteur > " + this.ctrl.getNomJoueur(this.currentPlayer) + " a joué : " + motJoue);
                this.etatDuJeu.addMotPlace(motJoue);
            } else if (motJoue != null) {
                //TODO DEMANDER S'IL VEUT CHANGER SES LETTRES OU RETENTER QUELQUE CHOSE
                System.out.println("Moteur > " + this.ctrl.getNomJoueur(this.currentPlayer) + " n'a pas pu jouer car son mot n'est pas posable ou possible");
            } else {
                System.out.println("Moteur > " + this.ctrl.getNomJoueur(this.currentPlayer) + " n'a pas trouver de mot et pioche ");
                //TODO DEMANDER AU JOUEUR QUELLES LETTRES IL DOIT CHANGER
                this.etatDuJeu.changerLettres();
            }
        }
        System.out.println(this.etatDuJeu.getPlateau());
        System.out.println("Moteur > la partie est finie "+ this.etatDuJeu);

        int gagnant = 0;
        int bestScore = 0;
        for (int i=0; i < this.etatDuJeu.getInventaires().length; i++) {
            System.out.println("Score de " + this.ctrl.getNomJoueur(i) + ": " + this.etatDuJeu.getInventaires()[i].getScore() + " points.");
            if (bestScore < this.etatDuJeu.getInventaires()[i].getScore()) {
                gagnant = i;
                bestScore = this.etatDuJeu.getInventaires()[i].getScore();
            }
        }

        System.out.println(this.ctrl.getNomJoueur(gagnant) + " gagne la partie avec un score de " + bestScore + " points.");

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
