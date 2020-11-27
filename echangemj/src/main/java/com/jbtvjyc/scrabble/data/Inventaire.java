package com.jbtvjyc.scrabble.data;

import java.util.ArrayList;
import java.util.Arrays;

public class Inventaire {
    ArrayList<Character> lettres;

    public Inventaire(){
        this.lettres = new ArrayList<Character>();
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

}