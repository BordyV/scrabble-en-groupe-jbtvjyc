package com.jbtvjyc.scrabble.data;

public class Plateau {
    private Case[][] lePlateau = new Case[15][15];
    int[] score = new int[]{1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};

    /**
     * Constructeur du plateau
     */
    public Plateau() {

        for(int x = 0; x < 15; x++)
        {
            for(int y = 0; y < 15; y++)
            {
                this.lePlateau[x][y] = new Case();
            }
        }

        //Mot compte triple
        this.lePlateau[0][0] = new Case(Bonus.MOTTRIPLE);
        this.lePlateau[0][7] = new Case(Bonus.MOTTRIPLE);
        this.lePlateau[0][14] = new Case(Bonus.MOTTRIPLE);
        this.lePlateau[7][0] = new Case(Bonus.MOTTRIPLE);
        this.lePlateau[7][14] = new Case(Bonus.MOTTRIPLE);
        this.lePlateau[14][0] = new Case(Bonus.MOTTRIPLE);
        this.lePlateau[14][7] = new Case(Bonus.MOTTRIPLE);
        this.lePlateau[14][14] = new Case(Bonus.MOTTRIPLE);

        //Mot compte double
        this.lePlateau[1][1] = new Case(Bonus.MOTDOUBLE);
        this.lePlateau[1][13] = new Case(Bonus.MOTDOUBLE);
        this.lePlateau[2][2] = new Case(Bonus.MOTDOUBLE);
        this.lePlateau[2][12] = new Case(Bonus.MOTDOUBLE);
        this.lePlateau[3][3] = new Case(Bonus.MOTDOUBLE);
        this.lePlateau[3][11] = new Case(Bonus.MOTDOUBLE);
        this.lePlateau[4][4] = new Case(Bonus.MOTDOUBLE);
        this.lePlateau[4][10] = new Case(Bonus.MOTDOUBLE);
        this.lePlateau[7][7] = new Case(Bonus.MOTDOUBLE);
        this.lePlateau[10][10] = new Case(Bonus.MOTDOUBLE);
        this.lePlateau[10][4] = new Case(Bonus.MOTDOUBLE);
        this.lePlateau[11][11] = new Case(Bonus.MOTDOUBLE);
        this.lePlateau[11][3] = new Case(Bonus.MOTDOUBLE);
        this.lePlateau[12][12] = new Case(Bonus.MOTDOUBLE);
        this.lePlateau[12][3] = new Case(Bonus.MOTDOUBLE);
        this.lePlateau[13][13] = new Case(Bonus.MOTDOUBLE);
        this.lePlateau[13][2] = new Case(Bonus.MOTDOUBLE);

        //Lettre compte triple
        this.lePlateau[1][5] = new Case(Bonus.LETTRETRIPLE);
        this.lePlateau[1][9] = new Case(Bonus.LETTRETRIPLE);
        this.lePlateau[5][1] = new Case(Bonus.LETTRETRIPLE);
        this.lePlateau[5][5] = new Case(Bonus.LETTRETRIPLE);
        this.lePlateau[5][9] = new Case(Bonus.LETTRETRIPLE);
        this.lePlateau[5][13] = new Case(Bonus.LETTRETRIPLE);
        this.lePlateau[9][1] = new Case(Bonus.LETTRETRIPLE);
        this.lePlateau[9][5] = new Case(Bonus.LETTRETRIPLE);
        this.lePlateau[9][9] = new Case(Bonus.LETTRETRIPLE);
        this.lePlateau[9][13] = new Case(Bonus.LETTRETRIPLE);
        this.lePlateau[13][5] = new Case(Bonus.LETTRETRIPLE);
        this.lePlateau[13][9] = new Case(Bonus.LETTRETRIPLE);

        //Lettre compte double
        this.lePlateau[0][3] = new Case(Bonus.LETTREDOUBLE);
        this.lePlateau[0][11] = new Case(Bonus.LETTREDOUBLE);
        this.lePlateau[2][6] = new Case(Bonus.LETTREDOUBLE);
        this.lePlateau[2][8] = new Case(Bonus.LETTREDOUBLE);
        this.lePlateau[3][0] = new Case(Bonus.LETTREDOUBLE);
        this.lePlateau[3][7] = new Case(Bonus.LETTREDOUBLE);
        this.lePlateau[3][14] = new Case(Bonus.LETTREDOUBLE);
        this.lePlateau[6][2] = new Case(Bonus.LETTREDOUBLE);
        this.lePlateau[6][6] = new Case(Bonus.LETTREDOUBLE);
        this.lePlateau[6][8] = new Case(Bonus.LETTREDOUBLE);
        this.lePlateau[6][12] = new Case(Bonus.LETTREDOUBLE);
        this.lePlateau[7][3] = new Case(Bonus.LETTREDOUBLE);
        this.lePlateau[7][11] = new Case(Bonus.LETTREDOUBLE);
        this.lePlateau[8][2] = new Case(Bonus.LETTREDOUBLE);
        this.lePlateau[8][6] = new Case(Bonus.LETTREDOUBLE);
        this.lePlateau[8][8] = new Case(Bonus.LETTREDOUBLE);
        this.lePlateau[8][12] = new Case(Bonus.LETTREDOUBLE);
        this.lePlateau[11][0] = new Case(Bonus.LETTREDOUBLE);
        this.lePlateau[11][7] = new Case(Bonus.LETTREDOUBLE);
        this.lePlateau[11][14] = new Case(Bonus.LETTREDOUBLE);
        this.lePlateau[12][6] = new Case(Bonus.LETTREDOUBLE);
        this.lePlateau[12][8] = new Case(Bonus.LETTREDOUBLE);
        this.lePlateau[14][3] = new Case(Bonus.LETTREDOUBLE);
        this.lePlateau[14][11] = new Case(Bonus.LETTREDOUBLE);

    }

    public Plateau(Case[][] plateau) {
        this.lePlateau = plateau;
    }

    public void poserMot(MotPositionne motAPoser)
    {
        //si le mot est horizontal
        if(motAPoser.getHorizontal()) {
            for(int i = 0; i < motAPoser.getMot().length(); i++)
            {
                char lettre = motAPoser.getMot().toCharArray()[i];
                this.setValeurCasePlateau(lettre,motAPoser.getOrdonnee(),motAPoser.getAbscisse()+i);
            }
        }
        //si le mot est vertical
        else{
            for(int i = 0; i < motAPoser.getMot().length(); i++)
            {
                char lettre = motAPoser.getMot().toCharArray()[i];
                this.setValeurCasePlateau(lettre,motAPoser.getOrdonnee()+i, motAPoser.getAbscisse());
            }
        }
    }

    public Case getCase(int y, int x) {
        return lePlateau[y][x];
    }

    /**
     * Set la valeur d'une case du plateau
     * @param valeur
     * @param y
     * @param x
     */
    public void setValeurCasePlateau(char valeur, int y, int x) {
        lePlateau[y][x].setValeur(valeur);
    }

    /**
     * Permet de connaitre le score d'un char donné
     */
    public int getScore(char lettre) {
        return score[Character.toLowerCase(lettre) - 'a'];
    }

    /**
     * Permet de connaitre le score d'un char donné
     */
    public int getScoreCaseXY(int x ,int y) {
        return score[Character.toLowerCase(this.getCase(y, x).getValeur()) - 'a'];
    }

    /**
     * Permet de connaitre le score d'un char donné
     */
    public int getScoreCase(Case laCase) {
        return score[Character.toLowerCase(laCase.getValeur()) - 'a'];
    }

    public Case[][] getLePlateau() {
        return lePlateau;
    }

    public void setLePlateau(Case[][] lePlateau) {
        this.lePlateau= lePlateau;
    }

    public String toString() {

        String plateauString = "";

        for(int x = 0; x < 16; x++)
        {
            plateauString += "[" + (x-1 > 9 ? x-1 : x == 0 ? "00" :"0"+(x-1));
            for(int y = 0; y < 15; y++)
            {
                if (x == 0) {
                    plateauString += "|" + (y > 9 ? y : "0"+y);
                } else {
                    plateauString += this.lePlateau[x-1][y].getValeur() != Character.MIN_VALUE
                            ? "|" + this.lePlateau[x-1][y].getValeur() + " "
                            : "|" + "  ";
                }
            }
            plateauString += "]\n";

        }
        return plateauString;
    }
}
