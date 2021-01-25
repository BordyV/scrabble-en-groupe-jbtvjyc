package com.jbtvjyc.scrabble.data;

public class StatistiqueGlobale {

        private int nbPartie;
        private int nbJoueurs = 0;
        private int i = 0;
        private Statistique[] stats;

        public StatistiqueGlobale(int nbPartie){
            this.nbPartie = nbPartie;
            stats = new Statistique[this.nbPartie];
        }

        public void trouverLeNombreDeJoueurs(){
            this.nbJoueurs = stats[0].getTableauStat().length;
        }

        public int calculePourcentageVictoire(int numeroJoueur){
            int res = 0;
            for(int i = 0; i < this.nbPartie; i++){
                res += this.stats[i].getTableauStat()[numeroJoueur][0];
            }
            return res;
        }

        public int calculeMotPlacerTotal(int numeroJoueur){
            int res = 0;
            for(int i = 0; i < this.nbPartie; i++){
                res += this.stats[i].getTableauStat()[numeroJoueur][1];
            }
            return res;
        }

        public int calculeTailleMotPlusLong(int numeroJoueur){
            int res = 0;
            for(int i = 0; i < this.nbPartie; i++){
                if(res < this.stats[i].getTableauStat()[numeroJoueur][2])
                res = this.stats[i].getTableauStat()[numeroJoueur][2];
            }
            return res;
        }

        public int calculeTailleMotPlusCourt(int numeroJoueur){
            int res = 30;
            for(int i = 0; i < this.nbPartie; i++){
                if(res > this.stats[i].getTableauStat()[numeroJoueur][3])
                    res = this.stats[i].getTableauStat()[numeroJoueur][3];
            }
            return res;
        }

        public int calculeTailleMoyenneMot(int numeroJoueur){
            int res = 0;
            for(int i = 0; i < this.nbPartie; i++){
                res += this.stats[i].getTableauStat()[numeroJoueur][4];
            }
            return res;
        }

        @Override
        public String toString(){
            trouverLeNombreDeJoueurs();
            String res = "Statistiques des "+ this.nbPartie +" Parties : \n";
            for(int i = 0; i < this.nbJoueurs/*-1*/; i++){
                res += "Joueur numero: " + i + "\n";
                /*res += "\tTaux de victoire: " + calculePourcentageVictoire(i) + " sur " + this.nbPartie + "\n";*/
                res += "\tNombre de mots placés: " + calculeMotPlacerTotal(i) + "\n";
                res += "\tTaille du mot le plus long: " + calculeTailleMotPlusLong(i) + "\n";
                res += "\tTaille du mot le plus court: " + calculeTailleMotPlusCourt(i) + "\n";
                res += "\tTaille moyenne des mots: " + calculeTailleMoyenneMot(i)/calculeMotPlacerTotal(i) + "\n";
                
            }
            return res;
        }

        public void setStats(Statistique stats) {
            this.stats[i] = stats;
            i++;
        }
    }
