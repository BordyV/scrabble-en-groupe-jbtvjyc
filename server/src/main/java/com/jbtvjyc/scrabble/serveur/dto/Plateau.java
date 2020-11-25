package com.jbtvjyc.scrabble.serveur.dto;

import com.jbtvjyc.scrabble.data.MotPositionne;

import java.lang.reflect.Array;

public class Plateau {
    private Case[][] plateau;

    public Plateau() {
        
        for(int x = 0; x < 14; x++)
        {
            for(int y = 0; y < 14; y++)
        {
            
        }
        }
    }

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
