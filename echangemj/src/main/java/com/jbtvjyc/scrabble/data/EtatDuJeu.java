package com.jbtvjyc.scrabble.data;

import java.util.ArrayList;
import java.util.List;

public class EtatDuJeu {
    List<MotPositionne> listeDeMots;
    Plateau plateau;
    Inventaire[] inventaires;
    int currJoueur;
    Pioche pioche;

    public EtatDuJeu() {
        this.listeDeMots = new ArrayList<>();
        this.plateau = new Plateau();
        this.inventaires = new Inventaire[4];
        for (int i=0; i < inventaires.length; i++) {
            inventaires[i] = new Inventaire();
        }
        this.pioche = new Pioche();
        this.currJoueur = 0;
    }

    public List<MotPositionne> getListeDeMots() {
        return this.listeDeMots;
    }

    public void setListeDeMots(List<MotPositionne> listeDeMots) {
        this.listeDeMots = listeDeMots;
    }

    public void piocherLettre() {
        int nbLettreAPiocher = (7 - this.inventaires[this.currJoueur].getLettres().toArray().length);
        this.pioche.piocherPlusieursLettres(this, nbLettreAPiocher);
    }

    //TODO PEUTETRE AJOUTER EN PARAM LES LETTRES A CHANGER EN FOCNTION DU CHOIX DU JOUEUR
    public void changerLettres() {
        //TODO AJOUTER UN CONTROLE SUPPLEMENTAIRE POUR VIDER TOTALEMENT LE SAC
        if(!this.pioche.getSacVide() && this.pioche.getTailleDuSac() > 7) {
            this.pioche.rendrePlusieursLettres(this.inventaires[this.currJoueur].getLettres());
            this.inventaires[this.currJoueur].enleverLettres(this.inventaires[this.currJoueur].getLettres());
            this.piocherLettre();
        }
        System.out.println("Le joueur change ses lettres, voici ses lettres " + this.getInventaire().getLettres());
    }

    public void enleverLettres(MotPositionne motPositionne) {
        List<Character> removeLetters = new ArrayList<>();

        if(motPositionne.getHorizontal()) {
            //pour chaque lettre dans le mot a positionner
            for (int i = 0; i < motPositionne.getMot().length(); i++) {
                //si la valeur du plateau est differente
                if (this.plateau.getCase(motPositionne.getOrdonnee(), motPositionne.getAbscisse()+i).getValeur() == Character.MIN_VALUE) {
                    removeLetters.add(motPositionne.getMot().charAt(i));
                }
            }
        }
        else{
            //pour chaque lettre dans le mot a positionner
            for (int i = 0; i < motPositionne.getMot().length(); i++) {
                //si la valeur du plateau est differente
                if (this.plateau.getCase(motPositionne.getOrdonnee()+i, motPositionne.getAbscisse()).getValeur() == Character.MIN_VALUE) {
                    removeLetters.add(motPositionne.getMot().charAt(i));
                }
            }
        }

        this.inventaires[this.currJoueur].enleverLettres(removeLetters);
    }

    public Plateau getPlateau() {
        return this.plateau;
    }

    public Inventaire getInventaire() {
        return inventaires[this.currJoueur];
    }

    public String toString() {
        return "[Plateau](contient " + this.listeDeMots.size() + " mot(s), et les lettres sont " + this.getInventaire().getLettres() + ")";
    }

    public void addMotPlace(MotPositionne motJoue) {
        this.listeDeMots.add(motJoue);
        this.enleverLettres(motJoue);
        this.piocherLettre();
        System.out.println("Le joueur pioche, voici ses lettres " + this.getInventaire().getLettres());
        this.getPlateau().poserMot(motJoue);
    }

    public Inventaire[] getInventaires() {
        return this.inventaires;
    }
}
