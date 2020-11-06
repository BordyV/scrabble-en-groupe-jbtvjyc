package com.jbtvjyc.scrabble.data;

import java.util.ArrayList;
import java.util.Arrays;

public class EtatDuJeu {
    ArrayList<MotPositionne> listeDeMots;
    ArrayList<Character> chariot;

    public EtatDuJeu() {
        this.listeDeMots = new ArrayList<>();
        this.chariot = new ArrayList<>();
    }

    public ArrayList<MotPositionne> getListeDeMots() {
        return this.listeDeMots;
    }

    public void setListeDeMots(ArrayList<MotPositionne> listeDeMots) {
        this.listeDeMots = listeDeMots;
    }

    public ArrayList<Character> getChariot() {
        return this.chariot;
    }

    public void setChariot(ArrayList<Character> chariot) {
        this.chariot = chariot;
    }

    public void ajouterLettres(Character... lettres) {
        this.chariot.addAll(Arrays.asList(lettres));
    }

    public String toString() {
        return "[Plateau](contient " + this.listeDeMots.size() + " mot(s), et les lettres sont " + this.chariot + ")";
    }

    public void addMotPlace(MotPositionne motJoue) {
        this.listeDeMots.add(motJoue);
    }
}
