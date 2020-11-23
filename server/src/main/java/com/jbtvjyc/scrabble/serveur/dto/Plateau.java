package com.jbtvjyc.scrabble.serveur.dto;

import com.jbtvjyc.scrabble.data.MotPositionne;

import java.lang.reflect.Array;

public class Plateau {
    private Case[][] plateau;

    public Plateau(Case[][] plateau) {
        this.plateau = plateau;
    }

    public void poserMot(MotPositionne motAPoser)
    {
        if(motAPoser.getHorizontal() == true) {
        }
        else{

        }
    }


    public Case[][] getPlateau() {
        return plateau;
    }

    public void setPlateau(Case[][] plateau) {
        this.plateau = plateau;
    }
}
