package com.jbtvjyc.scrabble.data;

import java.util.ArrayList;
import java.util.Arrays;

public class EtatDuJeu {
    ArrayList<MotPositionne> listeDeMots;
    Plateau plateau;
    Inventaire inventaire;

    public EtatDuJeu() {
        this.listeDeMots = new ArrayList<>();
        this.plateau = new Plateau();
        this.inventaire = new Inventaire();
    }

    public EtatDuJeu(Plateau plateau, Inventaire inventaire) {
        this.listeDeMots = new ArrayList<>();
        this.plateau = plateau;
        this.inventaire = inventaire;
    }

    public EtatDuJeu(Plateau plateau) {
        this.listeDeMots = new ArrayList<>();
        this.plateau = plateau;
        this.inventaire = inventaire;
    }

    public ArrayList<MotPositionne> getListeDeMots() {
        return this.listeDeMots;
    }

    public void setListeDeMots(ArrayList<MotPositionne> listeDeMots) {
        this.listeDeMots = listeDeMots;
    }

    public void ajouterLettres(Character... lettres) {
        this.inventaire.ajouterLettres(lettres);
    }

    //TODO A FAIRE PROCHAIN SPRING POUR JB
    //public void enleverLettres(Character... lettres) {
    //    this.chariot.removeAll(Arrays.asList(lettres));
    //}

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
        this.getPlateau().poserMot(motJoue);
    }
}
