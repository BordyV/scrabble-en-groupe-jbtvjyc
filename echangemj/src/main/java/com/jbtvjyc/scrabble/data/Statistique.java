package com.jbtvjyc.scrabble.data;

public class Statistique {

    private int tableauStat[][];
    private int Nbjoueurs;

    public Statistique(int Nbjoueurs){
        this.tableauStat = new int[Nbjoueurs][5];
    }


    /*public final void ajoutStatVictoire(int numeroJoueur){
        this.tableauStat[numeroJoueur][0] = this.tableauStat[numeroJoueur][0]+1;
    } */

    public final void ajoutMotPlacer(){
        this.tableauStat[Nbjoueurs][1] = this.tableauStat[Nbjoueurs][1]+1;
    }

    public final void tailleMotPlusLong(MotPositionne motJoue){
        if(motJoue.getMot().length() > this.tableauStat[Nbjoueurs][2]){
            this.tableauStat[Nbjoueurs][2] = motJoue.getMot().length();
        }
    }

    public final void tailleMotPlusCourt(MotPositionne motJoue){
        if(this.tableauStat[Nbjoueurs][3] == 0){
            this.tableauStat[Nbjoueurs][3] = motJoue.getMot().length();
        }
        if(motJoue.getMot().length() < this.tableauStat[Nbjoueurs][3]){
            this.tableauStat[Nbjoueurs][3] = motJoue.getMot().length();
        }
    }

    public final void tailleMoyenneMot(MotPositionne motJoue) {
        this.tableauStat[Nbjoueurs][4] = this.tableauStat[Nbjoueurs][4] + motJoue.getMot().length();
    }

    @Override
    public String toString(){
        String res = "Statistiques de la Partie: \n";
        for(int i = 0; i < this.tableauStat.length/*-1*/; i++){
            res += "Joueur numero: " + i + "\n";
            res += "\tNombre de mots placés: " + this.tableauStat[i][1] + "\n";
            res += "\tTaille du mot le plus long: " + this.tableauStat[i][2] + "\n";
            res += "\tTaille du mot le plus court: " + this.tableauStat[i][3] + "\n";
            res += "\tTaille moyenne des mots: " + this.tableauStat[i][4]/this.tableauStat[i][1] + "\n";
        }
        return res;
    }

    public int[][] getTableauStat() {
        return tableauStat;
    }
}
