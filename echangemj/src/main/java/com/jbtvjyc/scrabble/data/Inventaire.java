package com.jbtvjyc.scrabble.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Inventaire {
    List<Character> lettres;
    int score;

    public Inventaire(){
        this.lettres = new ArrayList<>();
        this.score = 0;
    }

    public List<Character> getLettres() {
        return this.lettres;
    }

    public void setLettres(List<Character> lettres) {
        this.lettres = lettres;
    }

    public void ajouterLettres(Character... lettres) {
        this.lettres.addAll(Arrays.asList(lettres));
    }

    public int getScore() {
        return this.score;
    }

    public void addScore(int scoresup){
        this.score = this.score +scoresup;
    }

}