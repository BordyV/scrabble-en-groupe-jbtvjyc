package com.jbtvjyc.scrabble.client;

import com.jbtvjyc.scrabble.data.Mots;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.jbtvjyc.scrabble.data.MotPositionne;
import com.jbtvjyc.scrabble.data.EtatDuJeu;

import java.util.ArrayList;

@Component
@Scope("singleton")
public class Client {
    Mots lesMots;

    public Client() {
        this.lesMots = new Mots();
    }

    public MotPositionne jouer(EtatDuJeu etatDuJeu) {
        MotPositionne motTrouver = this.trouverMot(etatDuJeu, this.lesMots.getListeDeMots());
        System.out.println("Joueur > je joue en " + motTrouver.getAbscisse() + " , " + motTrouver.getOrdonnee() +
                " le mot " + motTrouver.getMot() + " de facon " + (motTrouver.getHorizontal() ? "horizontal" : "vertical"));

        return motTrouver;
    }

    public MotPositionne trouverMot(EtatDuJeu etatDuJeu, ArrayList<String> listeDeMots) {
        ArrayList<Character> currInventaire = etatDuJeu.getChariot();

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
        int x = 7, y = 7;
        boolean posable = false;
        boolean horizontal = true;
        int nbTries = 0;

        do {
            x = (int)Math.floor(Math.random() * (14+1-bestMot.length()));
            y = (int)Math.floor(Math.random() * (14+1-bestMot.length()));

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

}