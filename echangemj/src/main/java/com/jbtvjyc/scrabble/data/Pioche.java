package com.jbtvjyc.scrabble.data;

import java.util.ArrayList;
import java.util.Random;

public class Pioche {
    char[] alphabet = "abcdefghijklmnopqrstuvwxyz-".toCharArray();
    ArrayList<Integer> sac;
    int tailleDuSac;
    boolean sacVide = false;

    public Pioche() {
        this.tailleDuSac = 102;
        this.sac = new ArrayList<>();
        for (int i=0; i<25; i++){
            this.sac.add(1);
        }
        this.setQuantiteDeLettreInitial();
    }

    public void setQuantiteDeLettreInitial(){
        //a
        this.sac.set(0,9);
        //b
        this.sac.set(1,2);
        //c
        this.sac.set(2,2);
        //d
        this.sac.set(3,3);
        //e
        this.sac.set(4,15);
        //f
        this.sac.set(5,2);
        //g
        this.sac.set(6,2);
        //h
        this.sac.set(7,2);
        //i
        this.sac.set(8,8);
        //j
        this.sac.set(9,1);
        //k
        this.sac.set(10,1);
        //l
        this.sac.set(11,5);
        //m
        this.sac.set(12,3);
        //n
        this.sac.set(13,6);
        //o
        this.sac.set(14,6);
        //p
        this.sac.set(15,2);
        //q
        this.sac.set(16,1);
        //r
        this.sac.set(17,6);
        //s
        this.sac.set(18,6);
        //t
        this.sac.set(19,6);
        //u
        this.sac.set(20,6);
        //v
        this.sac.set(21,2);
        //w
        this.sac.set(22,1);
        //x
        this.sac.set(23,1);
        //y
        this.sac.set(24,1);
        //z
        this.sac.set(25,1);
    }

    public void piocherPlusieursLettres(EtatDuJeu etatDuJeu, int ndDeLettres) {
        for (int i = etatDuJeu.getChariot().size()-1; i < etatDuJeu.getChariot().size()-1+ndDeLettres; i++){
            etatDuJeu.getChariot().add(i, piocherUneLettre());
        }
    }

    public char piocherUneLettre() {
        int index;
        char lettreTrouvee;
        do {
            Random random = new Random();
            index = random.nextInt(25);
            lettreTrouvee = alphabet[index];

        } while (this.testLettreVide(index));
        int newVal = this.sac.get(index)-1;
        this.sac.set(index, newVal);
        this.tailleDuSac--;
        if (this.tailleDuSac == 0) {
            this.sacVide = true;
        }
        return lettreTrouvee;
    }

    public boolean testLettreVide(int index) {
        return this.sac.get(index) == 0;
    }
}
