package com.jbtvjyc.scrabble.client;

import com.jbtvjyc.scrabble.data.Mots;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.jbtvjyc.scrabble.data.MotPositionne;
import com.jbtvjyc.scrabble.data.EtatDuJeu;

import java.util.List;

@Component
@Scope("singleton")
public class Client {
    Mots lesMots;

    public Client() {
        this.lesMots = new Mots();
    }

    public MotPositionne jouer(EtatDuJeu etatDuJeu) {
        MotPositionne motTrouver = this.trouverMotAvecPlateau(etatDuJeu, this.lesMots.getListeDeMots());
        if(motTrouver != null) System.out.println("Joueur > je joue en " + motTrouver.getAbscisse() + " , " + motTrouver.getOrdonnee() +
                " le mot " + motTrouver.getMot() + " de facon " + (motTrouver.getHorizontal() ? "horizontal" : "vertical"));

        return motTrouver;
    }

    public MotPositionne trouverMot(EtatDuJeu etatDuJeu, List<String> listeDeMots) {
        List<Character> currInventaire = etatDuJeu.getInventaire().getLettres();

        // We first count the number of each letter in the word and put the result in an array
        char[] nbLettresInv = new char[26];

        for (char c : currInventaire) {
            nbLettresInv[Character.toLowerCase(c) - 'a']++; // c - 'a' is a number between 0 and 25 for lowercase letters.
        }

        String bestMot = null;
        int bestScore = 0;

        for (String mot : listeDeMots) {
            char[] nbLettresMots = new char[26];
            int score = 0;
            for (char c : mot.toCharArray()) {
                int lettre = Character.toLowerCase(c) - 'a';
                nbLettresMots[lettre]++; // We count the letters for the word we try to do

                // If there is not enough letters to form that word, we stop counting
                if (nbLettresInv[lettre] < nbLettresMots[lettre]) {
                    score = -1; // To make sure the word does not become the bestMot
                    break;
                }
                // We add the score of that letter
                score += etatDuJeu.getPlateau().getScore(c);
            }

            if (score > bestScore) {
                bestScore = score;
                bestMot = mot;
            }
        }

        // If there is no word found, we return null
        if (bestMot == null) {
            return null;
        }

        // We try to find a position for the word
        int x;
        int y;
        boolean posable = false;
        boolean horizontal = true;
        int nbTries = 0;

        do {
            x = 7;
            y = 7;

            // Is the first case taken already ?
            if (etatDuJeu.getPlateau().getCase(y, x).getValeur() != Character.MIN_VALUE && etatDuJeu.getPlateau().getCase(y, x).getValeur() != bestMot.charAt(0)) {
                continue;
            }
            horizontal = true;
            boolean vertical = true;
            // We try to place the words horizontally / vertically and see if it's possible
            for (int i=1; i < bestMot.length(); i++) {
                if (etatDuJeu.getPlateau().getCase(y, x + i).getValeur() != Character.MIN_VALUE && etatDuJeu.getPlateau().getCase(y, x + i).getValeur() != bestMot.charAt(i)) {
                    horizontal = false;
                }
                if (etatDuJeu.getPlateau().getCase(y + i, x).getValeur() != Character.MIN_VALUE && etatDuJeu.getPlateau().getCase(y, x + i).getValeur() != bestMot.charAt(i)) {
                    vertical = false;
                }

                if (!horizontal && !vertical) {
                    break;
                }
            }
            posable = (horizontal || vertical);
            nbTries++;
        } while (!posable && nbTries < 50);

        // TODO Il faudrait pouvoir reboucler avec des mots plus petits pour pouvoir peut être les placer
        // If we can't place the word, we return null
        if (nbTries >= 50) {
            return null;
        }

        return new MotPositionne(bestMot, x, y, horizontal);
    }

    public MotPositionne trouverMotAvecPlateau (EtatDuJeu etatDuJeu, List<String> listeDeMots) {

        int x = 7, y = 7;
        String bestMot = null;
        int bestScore = 0;
        boolean horizontal = true;
        boolean posable = false;

        MotPositionne resultat = null;
        if (etatDuJeu.getPlateau().getCase(y, x).getValeur() == Character.MIN_VALUE) {
                return trouverMot(etatDuJeu,listeDeMots);
        }
        int lettre_plateau_x = 0, lettre_plateau_y = 0;
        int x_final = 0, y_final = 0;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {

                List<Character> currInventaire = null;
                char[] nbLettresInv = null;

                //System.out.println("Caractere a la position (" + j + ","+ i+") : " + etatDuJeu.getPlateau().getCase(j,i).getValeur()+ ".");

                if(etatDuJeu.getPlateau().getCase(j,i).getValeur() == Character.MIN_VALUE) {
                    continue;
                }
                currInventaire = etatDuJeu.getInventaire().getLettres();
                currInventaire.add(etatDuJeu.getPlateau().getCase(j,i).getValeur());

                //System.out.println("Inventaire : " + etatDuJeu.getChariot());

                nbLettresInv = new char[26];

                for (char c : currInventaire) {
                    nbLettresInv[Character.toLowerCase(c) - 'a']++; // c - 'a' is a number between 0 and 25 for lowercase letters.
                }

                for (String mot : listeDeMots) {
                    char[] nbLettresMots = new char[26];
                    int score = 0;
                    for (char c : mot.toCharArray()) {
                        int lettre = Character.toLowerCase(c) - 'a';
                        nbLettresMots[lettre]++;

                        if (nbLettresInv[lettre] < nbLettresMots[lettre]) {
                            score = -1;
                            break;
                        }

                        score += etatDuJeu.getPlateau().getScore(c);
                    }

                    if (score > bestScore) {
                        lettre_plateau_x = i;
                        lettre_plateau_y = j;
                        //bestScore = score;
                        bestMot = mot;

                        //On regarde si le mot doit etre positionné de maniere vertical ou horizontal
                        if (lettre_plateau_x > 0 && lettre_plateau_x < 14) {
                            if (etatDuJeu.getPlateau().getCase(j,i-1).getValeur() != Character.MIN_VALUE || etatDuJeu.getPlateau().getCase(j,i+1).getValeur() != Character.MIN_VALUE) {
                                horizontal = false;
                                //System.out.println("Position j et i de "+etatDuJeu.getPlateau().getCase(j,i).getValeur()+" : "+ j +" et "+ i);
                            }
                        } else if (lettre_plateau_x == 0) {
                            if (etatDuJeu.getPlateau().getCase(j,i+1).getValeur() != Character.MIN_VALUE) {
                                horizontal = false;
                            }
                        } else {
                            if (etatDuJeu.getPlateau().getCase(j,i+1).getValeur() != Character.MIN_VALUE) {
                                horizontal = false;
                            }
                        }

                        //On veut avoir la position x et y du mots a poser
                        int positionLettrePlateauDansMot = 0;
                        String leMotAPlacer = bestMot;
                        for (char laLettre : leMotAPlacer.toCharArray()) {
                            if(etatDuJeu.getPlateau().getCase(lettre_plateau_y,lettre_plateau_x).getValeur() == laLettre){
                                break;
                            }
                            positionLettrePlateauDansMot++;
                        }

                        if (horizontal) {
                            //resultat = new MotPositionne(bestMot, lettre_plateau_x-positionLettrePlateauDansMot, lettre_plateau_y, horizontal);
                            x_final = lettre_plateau_x-positionLettrePlateauDansMot;
                            y_final = lettre_plateau_y;
                            if ((x_final + bestMot.length() <= 14 && x_final >= 0)) {
                                posable = true;
                            }
                        } else {
                            //resultat = new MotPositionne(bestMot, lettre_plateau_x, lettre_plateau_y-positionLettrePlateauDansMot, horizontal);
                            x_final = lettre_plateau_x;
                            y_final = lettre_plateau_y-positionLettrePlateauDansMot;
                            if ((y_final + bestMot.length() <= 14 && y_final >= 0)) {
                                posable = true;
                            }
                        }

                        int nbLettrePlateau = 0;
                        if (posable) {

                            for (int k = 0; k < bestMot.length(); k++) {
                                char lettre = etatDuJeu.getPlateau().getCase(horizontal ? y_final : y_final + k, horizontal ? x_final + k : x_final).getValeur();
                                if (lettre != Character.MIN_VALUE) {
                                    if (lettre != bestMot.charAt(k)) {
                                        posable = false;
                                        break;
                                    }
                                    nbLettrePlateau++;
                                }
                            }
                            if (nbLettrePlateau == bestMot.length()) {
                                posable = false;
                            }
                        }

                        if (posable) {
                            int pos=1;
                            String motBase = bestMot;
                            if(horizontal){
                                if(x_final !=0 && etatDuJeu.getPlateau().getCase(y_final, x_final-1).getValeur() != Character.MIN_VALUE || x_final+bestMot.length() !=14 && etatDuJeu.getPlateau().getCase(y_final, x_final+bestMot.length()).getValeur() != Character.MIN_VALUE){
                                    while( x_final-pos >= 0 && etatDuJeu.getPlateau().getCase(y_final, x_final-pos).getValeur() != Character.MIN_VALUE){
                                        motBase= Character.toLowerCase(etatDuJeu.getPlateau().getCase(y_final, x_final-pos).getValeur()) + motBase;
                                        pos +=1;
                                    }
                                    pos = bestMot.length();
                                    while(x_final+pos <=14 && etatDuJeu.getPlateau().getCase(y_final, x_final+pos).getValeur() != Character.MIN_VALUE){
                                        motBase= motBase + Character.toLowerCase(etatDuJeu.getPlateau().getCase(y_final, x_final+pos).getValeur());
                                        pos +=1;
                                    }
                                    if (!this.lesMots.verificationExistanceMot(motBase)) {
                                        posable = false;
                                    }
                                }
                                for(int k=0;k<bestMot.length();k++){
                                    if( y_final != 0 && etatDuJeu.getPlateau().getCase(y_final-1, x_final+k).getValeur() != Character.MIN_VALUE || y_final != 14 && etatDuJeu.getPlateau().getCase(y_final+1, x_final+k).getValeur() != Character.MIN_VALUE){
                                        pos = 1;
                                        String motAdjacent= Character.toString(bestMot.charAt(k));
                                        while(y_final-pos >= 0 && etatDuJeu.getPlateau().getCase(y_final-pos, x_final+k).getValeur() != Character.MIN_VALUE){
                                            motAdjacent = Character.toLowerCase(etatDuJeu.getPlateau().getCase(y_final-pos, x_final+k).getValeur()) + motAdjacent;
                                            pos +=1;
                                        }
                                        pos = 1;
                                        while(y_final+pos <= 14 && etatDuJeu.getPlateau().getCase(y_final+pos, x_final+k).getValeur() != Character.MIN_VALUE){
                                            motAdjacent = motAdjacent + Character.toLowerCase(etatDuJeu.getPlateau().getCase(y_final+pos, x_final+k).getValeur());
                                            pos +=1;
                                        }
                                        if(!this.lesMots.verificationExistanceMot(motAdjacent)){
                                            posable = false;
                                        }
                                    }
                                }
                            }
                            else{
                                if(y_final != 0 && etatDuJeu.getPlateau().getCase(y_final-1, x_final).getValeur() != Character.MIN_VALUE || y_final+bestMot.length() != 14 && etatDuJeu.getPlateau().getCase(y_final+bestMot.length(), x_final).getValeur() != Character.MIN_VALUE){
                                    while(y_final-pos >= 0 &&etatDuJeu.getPlateau().getCase(y_final-pos, x_final).getValeur() != Character.MIN_VALUE){
                                        motBase= Character.toLowerCase(etatDuJeu.getPlateau().getCase(y_final-pos, x_final).getValeur()) + motBase;
                                        pos +=1;
                                    }
                                    pos = bestMot.length();
                                    while(y_final+pos <=14 && etatDuJeu.getPlateau().getCase(y_final+pos, x_final).getValeur() != Character.MIN_VALUE){
                                        motBase= motBase + Character.toLowerCase(etatDuJeu.getPlateau().getCase(y_final+pos, x_final).getValeur());
                                        pos +=1;
                                    }
                                    if (!this.lesMots.verificationExistanceMot(motBase)) {
                                        posable = false;
                                    }
                                }
                                for(int k=0;k<bestMot.length();k++){
                                    if(x_final != 0 && etatDuJeu.getPlateau().getCase(y_final+k, x_final-1).getValeur() != Character.MIN_VALUE || x_final != 14 && etatDuJeu.getPlateau().getCase(y_final+k, x_final+1).getValeur() != Character.MIN_VALUE){
                                        pos = 1;
                                        String motAdjacent= Character.toString(bestMot.charAt(k));
                                        while(x_final-pos >= 0 && etatDuJeu.getPlateau().getCase(y_final+k, x_final-pos).getValeur() != Character.MIN_VALUE){
                                            motAdjacent = Character.toLowerCase(etatDuJeu.getPlateau().getCase(y_final+k, x_final-pos).getValeur()) + motAdjacent;
                                            pos +=1;
                                        }
                                        pos = 1;
                                        while(x_final+pos <= 14 && etatDuJeu.getPlateau().getCase(y_final+k, x_final+pos).getValeur() != Character.MIN_VALUE){
                                            motAdjacent = motAdjacent + Character.toLowerCase(etatDuJeu.getPlateau().getCase(y_final+k, x_final+pos).getValeur());
                                            pos +=1;
                                        }
                                        // System.out.println("HELLOWORLD--"+bestMot+"--"+motAdjacent+"--"+x_final+"--"+y_final+"--"+k+"--"+bestMot.charAt(k)+"--"+etatDuJeu.getPlateau().getCase(y_final+k, x_final-1).getValeur()+"--"+etatDuJeu.getPlateau().getCase(y_final+k, x_final+1).getValeur());
                                        if(!this.lesMots.verificationExistanceMot(motAdjacent)){
                                            posable = false;
                                        }
                                    }
                                }
                            }
                            // System.out.println("ON EST OUT");
                        }


                        if (posable) {
                            bestScore = score;
                            //System.out.println("jai Trouver ! : "+bestMot_final+ " en : "+ x_final + " et " + y_final);
                            resultat = new MotPositionne(bestMot, x_final, y_final, horizontal);
                            posable = false;
                        }
                    }
                }

                currInventaire.remove(currInventaire.size()-1);

                if (bestMot == null) {
                    return null;
                }
            }
        }
        //System.out.println("J'ai trouver : "+resultat.toString());
        return resultat;
    }

}

