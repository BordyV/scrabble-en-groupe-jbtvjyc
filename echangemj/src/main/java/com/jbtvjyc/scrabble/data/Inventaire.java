package com.jbtvjyc.scrabble.data;

import java.util.*;

public class Inventaire {
    Statistique stats;
    List<Character> lettres;
    int score;

    public Inventaire(){
        this.lettres = new ArrayList<>();
        this.score = 0;
        this.stats = new Statistique(1);
    }

    public Inventaire(Statistique stats){
        this.lettres = new ArrayList<>();
        this.score = 0;
        this.stats = stats;
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

    public void enleverLettres(List<Character> lettresSupp) {

        List<Character> copyLettresSupp = new ArrayList<>();
        copyLettresSupp.addAll(lettresSupp);

        for (Character lettreASupp: copyLettresSupp) {
            for (Character lettreInv : this.lettres) {
                if(lettreASupp.charValue() == lettreInv.charValue()){
                    this.lettres.remove(lettreInv);
                    break;
                }
            }
        }
    }


    public int getScore() {
        return this.score;
    }

    public void addScore(int scoresup){
        this.score = this.score +scoresup;
    }

}