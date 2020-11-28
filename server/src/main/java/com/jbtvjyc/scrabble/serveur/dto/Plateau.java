package com.jbtvjyc.scrabble.serveur.dto;

import com.jbtvjyc.scrabble.data.MotPositionne;

import java.lang.reflect.Array;

public class Plateau {
    private Case[][] lePlateau = new Case[15][15];

    public Plateau() {

        for(int x = 0; x < 14; x++)
        {
            for(int y = 0; y < 14; y++)
            {
                this.lePlateau[x][y] = new Case();
            }
        }

        //Mot compte triple
        this.lePlateau[0][0] = new Case(Bonus.MotTriple);
        this.lePlateau[0][7] = new Case(Bonus.MotTriple);
        this.lePlateau[0][14] = new Case(Bonus.MotTriple);
        this.lePlateau[7][0] = new Case(Bonus.MotTriple);
        this.lePlateau[7][14] = new Case(Bonus.MotTriple);
        this.lePlateau[14][0] = new Case(Bonus.MotTriple);
        this.lePlateau[14][7] = new Case(Bonus.MotTriple);
        this.lePlateau[14][14] = new Case(Bonus.MotTriple);

        //Mot compte double
        this.lePlateau[1][1] = new Case(Bonus.MotDouble);
        this.lePlateau[1][13] = new Case(Bonus.MotDouble);
        this.lePlateau[2][2] = new Case(Bonus.MotDouble);
        this.lePlateau[2][12] = new Case(Bonus.MotDouble);
        this.lePlateau[3][3] = new Case(Bonus.MotDouble);
        this.lePlateau[3][11] = new Case(Bonus.MotDouble);
        this.lePlateau[4][4] = new Case(Bonus.MotDouble);
        this.lePlateau[4][10] = new Case(Bonus.MotDouble);
        this.lePlateau[7][7] = new Case(Bonus.MotDouble);
        this.lePlateau[10][10] = new Case(Bonus.MotDouble);
        this.lePlateau[10][4] = new Case(Bonus.MotDouble);
        this.lePlateau[11][11] = new Case(Bonus.MotDouble);
        this.lePlateau[11][3] = new Case(Bonus.MotDouble);
        this.lePlateau[12][12] = new Case(Bonus.MotDouble);
        this.lePlateau[12][3] = new Case(Bonus.MotDouble);
        this.lePlateau[13][13] = new Case(Bonus.MotDouble);
        this.lePlateau[13][2] = new Case(Bonus.MotDouble);

        //Lettre compte triple
        this.lePlateau[1][5] = new Case(Bonus.LettreTriple);
        this.lePlateau[1][9] = new Case(Bonus.LettreTriple);
        this.lePlateau[5][1] = new Case(Bonus.LettreTriple);
        this.lePlateau[5][5] = new Case(Bonus.LettreTriple);
        this.lePlateau[5][9] = new Case(Bonus.LettreTriple);
        this.lePlateau[5][13] = new Case(Bonus.LettreTriple);
        this.lePlateau[9][1] = new Case(Bonus.LettreTriple);
        this.lePlateau[9][5] = new Case(Bonus.LettreTriple);
        this.lePlateau[9][9] = new Case(Bonus.LettreTriple);
        this.lePlateau[9][13] = new Case(Bonus.LettreTriple);
        this.lePlateau[13][5] = new Case(Bonus.LettreTriple);
        this.lePlateau[13][9] = new Case(Bonus.LettreTriple);

        //Lettre compte double
        this.lePlateau[0][3] = new Case(Bonus.LettreDouble);
        this.lePlateau[0][11] = new Case(Bonus.LettreDouble);
        this.lePlateau[2][6] = new Case(Bonus.LettreDouble);
        this.lePlateau[2][8] = new Case(Bonus.LettreDouble);
        this.lePlateau[3][0] = new Case(Bonus.LettreDouble);
        this.lePlateau[3][7] = new Case(Bonus.LettreDouble);
        this.lePlateau[3][14] = new Case(Bonus.LettreDouble);
        this.lePlateau[6][2] = new Case(Bonus.LettreDouble);
        this.lePlateau[6][6] = new Case(Bonus.LettreDouble);
        this.lePlateau[6][8] = new Case(Bonus.LettreDouble);
        this.lePlateau[6][12] = new Case(Bonus.LettreDouble);
        this.lePlateau[7][3] = new Case(Bonus.LettreDouble);
        this.lePlateau[7][11] = new Case(Bonus.LettreDouble);
        this.lePlateau[8][2] = new Case(Bonus.LettreDouble);
        this.lePlateau[8][6] = new Case(Bonus.LettreDouble);
        this.lePlateau[8][8] = new Case(Bonus.LettreDouble);
        this.lePlateau[8][12] = new Case(Bonus.LettreDouble);
        this.lePlateau[11][0] = new Case(Bonus.LettreDouble);
        this.lePlateau[11][7] = new Case(Bonus.LettreDouble);
        this.lePlateau[11][14] = new Case(Bonus.LettreDouble);
        this.lePlateau[12][6] = new Case(Bonus.LettreDouble);
        this.lePlateau[12][8] = new Case(Bonus.LettreDouble);
        this.lePlateau[14][3] = new Case(Bonus.LettreDouble);
        this.lePlateau[14][11] = new Case(Bonus.LettreDouble);

    }

    public Plateau(Case[][] plateau) {
        this.lePlateau = plateau;
    }

    public void poserMot(MotPositionne motAPoser)
    {
        if(motAPoser.getHorizontal() == true) {
            for(int i = 0; i < motAPoser.getMot().length(); i++)
            {
                char lettre = motAPoser.getMot().toCharArray()[i];
                this.setValeurCasePlateau(lettre,motAPoser.getOrdonnee(),motAPoser.getAbscisse()+i);
            }
        }
        else{
            for(int i = 0; i < motAPoser.getMot().length(); i++)
            {
                char lettre = motAPoser.getMot().toCharArray()[i];
                this.setValeurCasePlateau(lettre,motAPoser.getOrdonnee()+i, motAPoser.getAbscisse());
            }
        }
    }

    public Case getCasePlateau(int y, int x) {
        return lePlateau[y][x];
    }

    /**
     *
     * @param valeur
     * @param x
     * @param y
     */
    public void setValeurCasePlateau(char valeur, int y, int x) {
        lePlateau[y][x].setValeur(valeur);
    }

    public Case[][] getLePlateau() {
        return lePlateau;
    }

    public void setLePlateau(Case[][] lePlateau) {
        this.lePlateau= lePlateau;
    }

    public String toString() {

        String plateauString = "";

        for(int x = 0; x < 14; x++)
        {
            plateauString += "[";
            for(int y = 0; y < 14; y++)
            {
                plateauString += this.lePlateau[x][y].getValeur() != Character.MIN_VALUE
                        ? "|" + this.lePlateau[x][y].getValeur()
                        :"|" + " ";

            }
            plateauString += "]\n";

        }
        return plateauString;
    }
}
