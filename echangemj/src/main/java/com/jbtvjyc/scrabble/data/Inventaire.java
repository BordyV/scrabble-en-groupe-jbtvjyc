package com.jbtvjyc.scrabble.data;

import java.util.ArrayList;
import java.util.Arrays;

public class Inventaire {
    ArrayList<Character> lettres;
    int score;

    public Inventaire(){
        this.lettres = new ArrayList<Character>();
        this.score = 0;
    }

    public ArrayList<Character> getLettres() {
        return this.lettres;
    }

    public void setLettres(ArrayList<Character> lettres) {
        this.lettres = lettres;
    }

    public void ajouterLettres(Character... lettres) {
        this.lettres.addAll(Arrays.asList(lettres));
    }

    public int getScore() {
        return this.score;
    }

    public int addScore(int scoresup){
        return this.score += scoresup;
    }

}