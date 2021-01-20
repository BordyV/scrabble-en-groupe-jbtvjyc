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

    public int poserMot(MotPositionne motAPoser)
    {
        //si le mot est horizontal
        if(motAPoser.getHorizontal()) {
            for(int i = 0; i < motAPoser.getMot().length(); i++)
            {
                char lettre = motAPoser.getMot().toCharArray()[i];
                this.setValeurCasePlateau(lettre, motAPoser.getOrdonnee(), motAPoser.getAbscisse()+i);
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
        return this.getScoreMot(motAPoser);
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
     * Permet de connaitre le score d'une case donnée
     */
    public int getScoreCaseXY(int x, int y) {
        this.getCase(y, x).setCaseTrouve();
        return score[Character.toLowerCase(this.getCase(y, x).getValeur()) - 'a'];
    }

    /**
     * Permet de connaitre le score d'un char donné
     */
    public int getScoreCase(Case laCase) {
        //System.out.println("valeur de la case : " + laCase.getValeur());
        int bonusLettre = 1;
        if(laCase.getBonusActif() || laCase.getCaseCommune()) {
            if(laCase.getBonus()==Bonus.LETTREDOUBLE){
                bonusLettre = 2;
                System.out.println("bonusLettre "+bonusLettre);
            }
            else if(laCase.getBonus()==Bonus.LETTRETRIPLE){
                bonusLettre = 3;
                System.out.println("bonusLettre "+bonusLettre);
            }
        }
        laCase.setCaseTrouve();
        return score[Character.toLowerCase(laCase.getValeur()) - 'a'] * bonusLettre;
    }

    /**
     * Permet de connaitre le score d'un mot donné
     */
    public int getScoreMot(MotPositionne mot) {
        int abscisse = mot.getAbscisse();
        int ordonnee = mot.getOrdonnee();
        boolean horizontal = mot.getHorizontal();
        int scoreTmp = 0;
        int bonusMot = 1;
        int scoreMotAccolesTrouves=0;
        char[] tableauDeChar = mot.getMot().toCharArray();
        int curseurMotsAccoles = 0;
        Case[] casesCommunesMotsAccoles = new Case[15];
        MotPositionne[] motAccolesTrouves = new MotPositionne[15];
        System.out.println("calcule du score du mot "+mot.getMot()+" "+mot.getAbscisse()+" "+mot.getOrdonnee());
        for(int i = 0; i<tableauDeChar.length; i++){
            Case caseTmp;
            int abscisseTmp;
            int ordonneeTmp;
            boolean motTrouve = false;
            String motTmp = "";
            //si le mot est horizontal
            if(horizontal){
                abscisseTmp = abscisse+i;
                ordonneeTmp = ordonnee;
                caseTmp = this.lePlateau[ordonneeTmp][abscisseTmp];
                if(caseTmp.getCasePasTrouve() && ordonneeTmp>=0 && ordonneeTmp<=14 && this.lePlateau[ordonneeTmp][abscisseTmp].getCasePasTrouve()){
                    casesCommunesMotsAccoles[curseurMotsAccoles]=this.lePlateau[ordonneeTmp][abscisseTmp];
                    int ordonneeDebutMot = ordonneeTmp;
                    if(ordonneeTmp>0 && this.lePlateau[ordonneeTmp-1][abscisseTmp].getValeur()!=Character.MIN_VALUE){
                        int j = 0;
                        while (ordonneeTmp-j>=0 && this.lePlateau[ordonneeTmp-j][abscisseTmp].getValeur()!=Character.MIN_VALUE) {
                            char valeurTmp = this.lePlateau[ordonneeTmp-j][abscisseTmp].getValeur();
                            motTmp = valeurTmp+motTmp;
                            ordonneeDebutMot=ordonneeTmp-j;
                            j++;
                        }
                        motTrouve = true;
                    }
                    if(ordonneeTmp<14 && this.lePlateau[ordonneeTmp+1][abscisseTmp].getValeur()!=Character.MIN_VALUE){
                        int j = 0;
                        while (ordonneeTmp+j<=14 && this.lePlateau[ordonneeTmp+j][abscisseTmp].getValeur()!=Character.MIN_VALUE) {
                            char valeurTmp = this.lePlateau[ordonneeTmp+j][abscisseTmp].getValeur();
                            motTmp = motTmp+valeurTmp;
                            //ordonneeDebutMot=ordonneeTmp+j;
                            j++;
                        }
                        motTrouve = true;
                    }
                    if (motTrouve) {
                        motAccolesTrouves[curseurMotsAccoles] = new MotPositionne(motTmp, abscisseTmp,  ordonneeDebutMot, false);
                        System.out.println("un autre mot a ete trouve : " + motAccolesTrouves[curseurMotsAccoles].getMot() + " " + motAccolesTrouves[curseurMotsAccoles].getAbscisse()  + " " + motAccolesTrouves[curseurMotsAccoles].getOrdonnee());
                        this.lePlateau[ordonneeTmp][abscisseTmp].setCaseTrouve();
                        caseTmp.setCaseCommune(true);
                        curseurMotsAccoles++;
                    }
                }
            }
            //si le mot est vertical
            else {
                abscisseTmp = abscisse;
                ordonneeTmp = ordonnee+i;
                caseTmp = this.lePlateau[ordonneeTmp][abscisseTmp];
                if(caseTmp.getCasePasTrouve() && abscisseTmp>=0 && abscisseTmp<=14 && this.lePlateau[ordonneeTmp][abscisseTmp].getCasePasTrouve()){
                    casesCommunesMotsAccoles[curseurMotsAccoles]=this.lePlateau[ordonneeTmp][abscisseTmp];
                    int abscisseDebutMot = abscisseTmp;
                    if(abscisseTmp>0 && this.lePlateau[ordonneeTmp][abscisseTmp-1].getValeur()!=Character.MIN_VALUE){
                        int j = 0;
                        while (abscisseTmp-j>=0 && this.lePlateau[ordonneeTmp][abscisseTmp-j].getValeur()!=Character.MIN_VALUE) {
                            char valeurTmp = this.lePlateau[ordonneeTmp][abscisseTmp-j].getValeur();
                            motTmp = valeurTmp+motTmp;
                            abscisseDebutMot=abscisseTmp-j;
                            j++;
                        }
                        motTrouve = true;
                    }
                    if(abscisseTmp<14 && this.lePlateau[ordonneeTmp][abscisseTmp+1].getValeur()!=Character.MIN_VALUE){
                        int j = 0;
                        while (abscisseTmp+j<=14 && this.lePlateau[ordonneeTmp][abscisseTmp+j].getValeur()!=Character.MIN_VALUE) {
                            char valeurTmp = this.lePlateau[ordonneeTmp][abscisseTmp+j].getValeur();
                            motTmp = motTmp+valeurTmp;
                            //abscisseDebutMot=abscisseTmp+j;
                            j++;
                        }
                        motTrouve = true;
                    }
                    if (motTrouve) {
                        motAccolesTrouves[curseurMotsAccoles] = new MotPositionne(motTmp, abscisseDebutMot,  ordonneeTmp, true);
                        System.out.println("un autre mot a ete trouve : " + motAccolesTrouves[curseurMotsAccoles].getMot() + " " + motAccolesTrouves[curseurMotsAccoles].getAbscisse()  + " " + motAccolesTrouves[curseurMotsAccoles].getOrdonnee());
                        this.lePlateau[ordonneeTmp][abscisseTmp].setCaseTrouve();
                        caseTmp.setCaseCommune(true);
                        curseurMotsAccoles++;
                    }
                }
            }
            for(int j = 0; j<curseurMotsAccoles; j++) {
                System.out.println(j);
                scoreMotAccolesTrouves+=getScoreMot(motAccolesTrouves[j]);
            }
            scoreTmp += getScoreCase(caseTmp);
            if(caseTmp.getBonusActif() || caseTmp.getCaseCommune()){
                if(caseTmp.getBonusActif() || caseTmp.getCaseCommune()) {
                    if(caseTmp.getBonus()==Bonus.MOTDOUBLE){
                        bonusMot *= 2;
                    }
                    if(caseTmp.getBonus()==Bonus.MOTTRIPLE){
                        bonusMot *= 3;
                    }
                }
            }
            caseTmp.setBonusPlusActif();
            curseurMotsAccoles = 0;
            casesCommunesMotsAccoles = new Case[15];
            motAccolesTrouves = new MotPositionne[15];
        }
        int scoreTotal=scoreMotAccolesTrouves+scoreTmp*bonusMot;
        System.out.println("score mots accoles : "+scoreMotAccolesTrouves);
        System.out.println(mot.getMot()+" - score total : "+scoreTotal);
        System.out.println();
        for(int j = 0; j<curseurMotsAccoles; j++) {
            casesCommunesMotsAccoles[j].setCaseCommune(false);
        }
        return scoreTotal;
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
