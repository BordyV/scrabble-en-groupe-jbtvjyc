package com.jbtvjyc.scrabble.serveur;

import com.jbtvjyc.scrabble.data.MotPositionne;
import com.jbtvjyc.scrabble.data.Mots;

import java.util.ArrayList;

public class Verification {
    String mot;
    ArrayList<Character> lettresjoueur;
    MotPositionne motPosition;
    Plateau plateau;


    Verification(ArrayList<Character> joueur, MotPositionne positionne,Plateau plateau){
        this.lettresjoueur = joueur;
        this.motPosition = positionne;
        this.plateau = plateau;
        this.mot = positionne.getMot();
    }
    public ArrayList<Integer> positionLettres(){
        ArrayList<Integer> positions = new ArrayList<>();
        int posX =motPosition.getAbscisse();
        int posY = motPosition.getOrdonnee();
        if(motPosition.getHorizontal()) {
            for(int i = 0; i < motPosition.getMot().length(); i++)
            {
                if(plateau.getCasePlateau(posY,posX+i).getValeur() != Character.MIN_VALUE){
                    positions.add(i);
                }
            }
        }
        else{
            for(int i = 0; i < motPosition.getMot().length(); i++)
            {
                if(plateau.getCasePlateau(posY+i,posX).getValeur() != Character.MIN_VALUE){
                    positions.add(i);
                }
            }
        }
        return positions;
    }
    public boolean verifMot () {
        Mots mots = new Mots();
        if (!mots.verificationExistanceMot(this.mot)) {
            return false;
        }
        int posX =motPosition.getAbscisse();
        int posY = motPosition.getOrdonnee();
        if (posX > 14 || posX <0 || posY > 14 || posY < 0) {
            return false;
        }
        ArrayList<Integer> positions = positionLettres();
        if (this.lettresjoueur.size()+positions.size() < this.mot.length()) {
            return false;
        }
        if (motPosition.getHorizontal()) {
            if (posX + this.mot.length() > 14) {
                return false;
            }
            for (Integer position : positions) {
                if (Character.toLowerCase(this.mot.charAt(position)) != Character.toLowerCase(plateau.getCasePlateau(posY, posX + position).getValeur())) {
                    return false;
                }
                this.lettresjoueur.add(plateau.getCasePlateau(posY, posX + position).getValeur());
            }
        } else {
            if(posY + this.mot.length() > 14){
                return false;
            }
            for (Integer position : positions) {
                if (Character.toLowerCase(this.mot.charAt(position)) != Character.toLowerCase(plateau.getCasePlateau(posY + position, posX).getValeur())) {
                    return false;
                }
                this.lettresjoueur.add(plateau.getCasePlateau(posY + position, posX).getValeur());
            }
        }
        for (int i = 0; i < this.mot.length(); i++) {
            boolean found = false;
            char charMot = Character.toLowerCase(this.mot.charAt(i));
            for (int j = 0;j < this.lettresjoueur.size() ; j++) {
                if (charMot == Character.toLowerCase(this.lettresjoueur.get(j))) {
                    this.lettresjoueur.remove(j);
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }

        return true;
    }
}