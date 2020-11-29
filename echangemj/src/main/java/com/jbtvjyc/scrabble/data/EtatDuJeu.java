package com.jbtvjyc.scrabble.data;

import java.util.ArrayList;
import java.util.Arrays;
import com.jbtvjyc.scrabble.data.Plateau;

public class EtatDuJeu {
    ArrayList<MotPositionne> listeDeMots;
    ArrayList<Character> chariot;
    Plateau plateau;

    public EtatDuJeu() {
        this.listeDeMots = new ArrayList<>();
        this.chariot = new ArrayList<>();
    }

    public EtatDuJeu(Plateau plateau) {
        this.listeDeMots = new ArrayList<>();
        this.chariot = new ArrayList<>();
        this.plateau = plateau;
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
    }
}
