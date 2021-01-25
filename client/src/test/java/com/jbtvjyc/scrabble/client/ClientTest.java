package com.jbtvjyc.scrabble.client;

import com.jbtvjyc.scrabble.data.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    EtatDuJeu etatDuJeu;
    Mots mots;
    Client client;
    Statistique stats;

    @BeforeEach
    void setup() {
        this.etatDuJeu = new EtatDuJeu(new Plateau(), new Inventaire(stats), new Pioche());
        this.mots = new Mots();
        this.client = new Client();
    }

    /**
     * Test de trouverMot
     * return abaisse
     */
    @Test
    void trouverMotTest() {
        ArrayList<Character> chariot = new ArrayList<>();
        chariot.add('a');
        chariot.add('b');
        chariot.add('a');
        chariot.add('i');
        chariot.add('s');
        chariot.add('s');
        chariot.add('e');
        //etat du jeu n'aura plus le chariot donc on passera par l'inventaire
        //l'inventaire sera peutetre dans l'état du jeu
        this.etatDuJeu.getInventaire().setLettres(chariot);
        //this.etatDuJeu.getInventaire().setLettres(chariot);
        MotPositionne motTrouve = this.client.trouverMot(this.etatDuJeu, this.mots.getListeDeMots());
        assertEquals("abaisse",motTrouve.getMot());
    }

    /**
     * Test de trouver Mot avec " z z z z z z z "
     * Return null
     */
    @Test
    void trouverMotNullTest() {
        ArrayList<Character> chariot = new ArrayList<Character>();
        chariot.add('z');
        chariot.add('z');
        chariot.add('z');
        chariot.add('z');
        chariot.add('z');
        chariot.add('z');
        chariot.add('z');
        //etat du jeu n'aura plus le chariot donc on passera par l'inventaire
        //l'inventaire sera peutetre dans l'état du jeu
        this.etatDuJeu.getInventaire().setLettres(chariot);
        MotPositionne motTrouve = this.client.trouverMot(this.etatDuJeu, this.mots.getListeDeMots());
        assertNull(motTrouve);
    }

    @Test
    void jouerMotTest() {
        ArrayList<Character> chariot = new ArrayList<Character>();
        chariot.add('a');
        chariot.add('b');
        chariot.add('a');
        chariot.add('i');
        chariot.add('s');
        chariot.add('s');
        chariot.add('e');


        this.etatDuJeu.getInventaire().setLettres(chariot);
        MotPositionne motTrouve = this.client.jouer(this.etatDuJeu);
        assertEquals("abaisse",motTrouve.getMot());
    }

    @Test
    void trouverMotAvecPlateauTest() {
        ArrayList<Character> chariot = new ArrayList<Character>();
        chariot.add('a');
        chariot.add('b');
        chariot.add('a');
        chariot.add('i');
        chariot.add('s');
        chariot.add('s');
        //chariot.add('e');
        this.etatDuJeu.getInventaire().setLettres(chariot);

        MotPositionne motAPoserVer = new MotPositionne("chameau", 7, 7, false);
        this.etatDuJeu.getPlateau().poserMot(motAPoserVer);

        MotPositionne motTrouve = this.client.trouverMotAvecPlateau(this.etatDuJeu, this.mots.getListeDeMots());
        //System.out.println("Le mot trouver est: "+motTrouve.toString());
        assertEquals("abaisse", motTrouve.getMot());

    }

    @Test
    void trouverMotAvecPlateauTestEchec() {
        ArrayList<Character> chariot = new ArrayList<Character>();
        chariot.add('a');
        chariot.add('b');
        chariot.add('a');
        chariot.add('i');
        chariot.add('s');
        chariot.add('s');
        //chariot.add('e');

        this.etatDuJeu.getInventaire().setLettres(chariot);

        MotPositionne motAPoserVerInitial = new MotPositionne("z", 7, 7, false);
        this.etatDuJeu.getPlateau().poserMot(motAPoserVerInitial);

        MotPositionne motAPoserVer = new MotPositionne("chameau", 0, 0, false);
        this.etatDuJeu.getPlateau().poserMot(motAPoserVer);

        MotPositionne motTrouve = this.client.trouverMotAvecPlateau(this.etatDuJeu, this.mots.getListeDeMots());
        System.out.println("Le mot trouver est: "+motTrouve.toString());
        assertNotEquals("abaisse", motTrouve.getMot());
    }

}