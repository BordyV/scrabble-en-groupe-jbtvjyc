package com.jbtvjyc.scrabble.serveur;

import com.jbtvjyc.scrabble.data.MotPositionne;
import com.jbtvjyc.scrabble.data.Mots;
import com.jbtvjyc.scrabble.data.Plateau;

import java.util.ArrayList;
import java.util.List;

public class Verification {
    String mot;
    List<Character> lettresjoueur;
    MotPositionne motPosition;
    Plateau plateau;
    Mots lesMots;


    public Verification(List<Character> joueur, MotPositionne positionne,Plateau plateau, Mots mots){
        this.lettresjoueur = joueur;
        this.motPosition = positionne;
        this.plateau = plateau;
        this.mot = positionne.getMot();
        this.lesMots = mots;

    }
    public List<Integer> positionLettres(){
        List<Integer> positions = new ArrayList<>();
        int posX =motPosition.getAbscisse();
        int posY = motPosition.getOrdonnee();
        if(motPosition.getHorizontal()) {
            for(int i = 0; i < motPosition.getMot().length(); i++)
            {
                if(plateau.getCase(posY,posX+i).getValeur() != Character.MIN_VALUE){
                    positions.add(i);
                }
            }
        }
        else{
            for(int i = 0; i < motPosition.getMot().length(); i++)
            {
                if(plateau.getCase(posY+i,posX).getValeur() != Character.MIN_VALUE){
                    positions.add(i);
                }
            }
        }
        return positions;
    }
    public boolean verifMotAdjacent(){
        int posX = motPosition.getAbscisse();
        int posY = motPosition.getOrdonnee();
        int pos=1;
        String motBase = this.mot;
        if(motPosition.getHorizontal()){
            if(posX !=0 && plateau.getCase(posY, posX-1).getValeur() != Character.MIN_VALUE || posX+mot.length() !=14 && plateau.getCase(posY, posX+mot.length()).getValeur() != Character.MIN_VALUE){
                while( posX-pos >= 0 && plateau.getCase(posY, posX-pos).getValeur() != Character.MIN_VALUE){
                    motBase= Character.toLowerCase(plateau.getCase(posY, posX-pos).getValeur()) + motBase;
                    pos +=1;
                }
                pos = this.mot.length();
                while(posX+pos <=14 && plateau.getCase(posY, posX+pos).getValeur() != Character.MIN_VALUE){
                    motBase= motBase + Character.toLowerCase(plateau.getCase(posY, posX+pos).getValeur());
                    pos +=1;
                }
                if (!this.lesMots.verificationExistanceMot(motBase)) {
                    return false;
                }
            }
        for(int i=0;i<mot.length();i++){
            if( posY != 0 && plateau.getCase(posY-1, posX+i).getValeur() != Character.MIN_VALUE || posY != 14 && plateau.getCase(posY+1, posX+i).getValeur() != Character.MIN_VALUE){
                pos = 1;
                String motAdjacent= Character.toString(mot.charAt(i));
                while(posY-pos >= 0 && plateau.getCase(posY-pos, posX+i).getValeur() != Character.MIN_VALUE){
                    motAdjacent = Character.toLowerCase(plateau.getCase(posY-pos, posX+i).getValeur()) + motAdjacent;
                    pos +=1;
                }
                pos = 1;
                while(posY+pos <= 14 && plateau.getCase(posY+pos, posX+i).getValeur() != Character.MIN_VALUE){
                    motAdjacent = motAdjacent + Character.toLowerCase(plateau.getCase(posY+pos, posX+i).getValeur());
                    pos +=1;
                }
                if(!this.lesMots.verificationExistanceMot(motAdjacent)){
                    return false;
                }
            }
        }
        }
        else{
            if(posY != 0 && plateau.getCase(posY-1, posX).getValeur() != Character.MIN_VALUE || posY+mot.length() != 14 && plateau.getCase(posY+mot.length(), posX).getValeur() != Character.MIN_VALUE){
                while(posY-pos >= 0 &&plateau.getCase(posY-pos, posX).getValeur() != Character.MIN_VALUE){
                    motBase= Character.toLowerCase(plateau.getCase(posY-pos, posX).getValeur()) + motBase;
                    pos +=1;
                }
                pos = this.mot.length();
                while(posY+pos <=14 && plateau.getCase(posY+pos, posX).getValeur() != Character.MIN_VALUE){
                    motBase= motBase + Character.toLowerCase(plateau.getCase(posY+pos, posX).getValeur());
                    pos +=1;
                }
                if (!this.lesMots.verificationExistanceMot(motBase)) {
                    return false;
                }
            }
            for(int i=0;i<mot.length();i++){
                if(posX != 0 && plateau.getCase(posY+i, posX-1).getValeur() != Character.MIN_VALUE || posX != 14 && plateau.getCase(posY+i, posX+1).getValeur() != Character.MIN_VALUE){
                    pos = 1;
                    String motAdjacent= Character.toString(mot.charAt(i));
                    while(posX-pos >= 0 && plateau.getCase(posY+i, posX-pos).getValeur() != Character.MIN_VALUE){
                        motAdjacent = Character.toLowerCase(plateau.getCase(posY+i, posX-pos).getValeur()) + motAdjacent;
                        pos +=1;
                    }
                    pos = 1;
                    while(posX+pos <= 14 && plateau.getCase(posY+i, posX+pos).getValeur() != Character.MIN_VALUE){
                        motAdjacent = motAdjacent + Character.toLowerCase(plateau.getCase(posY+i, posX+pos).getValeur());
                        pos +=1;
                    }
                    if(!this.lesMots.verificationExistanceMot(motAdjacent)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public boolean verifMot () {
        if (!this.lesMots.verificationExistanceMot(this.mot)) {
            return false;
        }
        int posX = motPosition.getAbscisse();
        int posY = motPosition.getOrdonnee();
        if (posX > 14 || posX <0 || posY > 14 || posY < 0) {
            return false;
        }
        if ((motPosition.getHorizontal() && posX + this.mot.length() > 14) || (!motPosition.getHorizontal() && posY + this.mot.length() > 14)) {
            return false;
        }
        List<Integer> positions = positionLettres();
        if(this.mot.length() == positions.size()){
            return false;
        }
        if (this.lettresjoueur.size()+positions.size() < this.mot.length()) {
            return false;
        }
        if(!this.verifMotAdjacent()){
            return false;
        }
        if (motPosition.getHorizontal()) {
            for (Integer position : positions) {
                if (Character.toLowerCase(this.mot.charAt(position)) != Character.toLowerCase(plateau.getCase(posY, posX + position).getValeur())) {
                    return false;
                }
                this.lettresjoueur.add(plateau.getCase(posY, posX + position).getValeur());
            }
        } else {
            for (Integer position : positions) {
                if (Character.toLowerCase(this.mot.charAt(position)) != Character.toLowerCase(plateau.getCase(posY + position, posX).getValeur())) {
                    return false;
                }
                this.lettresjoueur.add(plateau.getCase(posY + position, posX).getValeur());
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
