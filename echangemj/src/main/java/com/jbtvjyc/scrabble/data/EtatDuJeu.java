package com.jbtvjyc.scrabble.data;

import java.util.ArrayList;
import java.util.Arrays;
import com.jbtvjyc.scrabble.data.Plateau;

public class EtatDuJeu {
    ArrayList<MotPositionne> listeDeMots;
    Plateau plateau;

    public EtatDuJeu() {
        this.listeDeMots = new ArrayList<>();
        this.plateau = new Plateau();
    }

    public EtatDuJeu(Plateau plateau) {
        this.listeDeMots = new ArrayList<>();
        this.plateau = plateau;
    }

    public ArrayList<MotPositionne> getListeDeMots() {
        return this.listeDeMots;
    }

    public void setListeDeMots(ArrayList<MotPositionne> listeDeMots) {
        this.listeDeMots = listeDeMots;
    }

    public void ajouterLettres(Character... lettres) {
        this.chariot.addAll(Arrays.asList(lettres));
    }

    //TODO A FAIRE PROCHAIN SPRING POUR JB
    //public void enleverLettres(Character... lettres) {
    //    this.chariot.removeAll(Arrays.asList(lettres));
    //}

    public Plateau getPlateau() {
        return this.plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public String toString() {
        return "[Plateau](contient " + this.listeDeMots.size() + " mot(s), et les lettres sont " + this.chariot + ")";
    }

    public void addMotPlace(MotPositionne motJoue) {
        this.listeDeMots.add(motJoue);
        this.getPlateau().poserMot(motJoue);
    }
}
