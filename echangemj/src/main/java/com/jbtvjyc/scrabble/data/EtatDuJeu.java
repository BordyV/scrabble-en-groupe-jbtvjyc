package com.jbtvjyc.scrabble.data;

import java.util.ArrayList;
import java.util.List;

public class EtatDuJeu {
    List<MotPositionne> listeDeMots;
    Plateau plateau;
    Inventaire inventaire;
    Pioche pioche;
    Statistique stats;

    public EtatDuJeu() {
        this.listeDeMots = new ArrayList<>();
        this.plateau = new Plateau();
        this.stats = new Statistique(1);
        this.inventaire = new Inventaire(stats);
        this.pioche = new Pioche();
    }

    public EtatDuJeu(Plateau plateau, Inventaire inventaire, Pioche pioche) {
        this.listeDeMots = new ArrayList<>();
        this.plateau = plateau;
        this.inventaire = inventaire;
        this.pioche = pioche;
    }

    public EtatDuJeu(Plateau plateau) {
        this.listeDeMots = new ArrayList<>();
        this.plateau = plateau;
    }

    public List<MotPositionne> getListeDeMots() {
        return this.listeDeMots;
    }

    public void setListeDeMots(List<MotPositionne> listeDeMots) {
        this.listeDeMots = listeDeMots;
    }

    public void piocherLettre() {
        int nbLettreAPiocher = (7 - this.inventaire.getLettres().toArray().length);
        this.pioche.piocherPlusieursLettres(this, nbLettreAPiocher);
    }

    //TODO PEUTETRE AJOUTER EN PARAM LES LETTRES A CHANGER EN FOCNTION DU CHOIX DU JOUEUR
    public void changerLettres() {
        //TODO AJOUTER UN CONTROLE SUPPLEMENTAIRE POUR VIDER TOTALEMENT LE SAC
        if(!this.pioche.getSacVide() && this.pioche.getTailleDuSac() > 7) {
            this.pioche.rendrePlusieursLettres(this.inventaire.getLettres());
            this.inventaire.enleverLettres(this.inventaire.getLettres());
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

        this.inventaire.enleverLettres(removeLetters);
    }

    public Plateau getPlateau() {
        return this.plateau;
    }

    public Inventaire getInventaire() {
        return inventaire;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public void setInventaire(Inventaire inventaire) {
        this.inventaire = inventaire;
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
}
