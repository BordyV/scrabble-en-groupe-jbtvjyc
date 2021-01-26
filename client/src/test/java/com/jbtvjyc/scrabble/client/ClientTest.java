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

    @BeforeEach
    void setup() {
        this.etatDuJeu = new EtatDuJeu(new Plateau(), new Inventaire(), new Pioche());
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

    @Test
    void trouverMotAvecPlateauTestVertical() {
        ArrayList<Character> chariot = new ArrayList<Character>();
        chariot.add('a');
        chariot.add('b');
        chariot.add('a');
        chariot.add('i');
        chariot.add('s');
        chariot.add('s');
        //chariot.add('e');
        this.etatDuJeu.getInventaire().setLettres(chariot);

        MotPositionne motAPoserVer = new MotPositionne("chameau", 7, 7, true);
        this.etatDuJeu.getPlateau().poserMot(motAPoserVer);

        MotPositionne motTrouve = this.client.trouverMotAvecPlateau(this.etatDuJeu, this.mots.getListeDeMots());
        assertEquals(false, motTrouve.getHorizontal());
    }

    @Test
    void trouverMotAvecPlateauTestVerticalPremiereLigne() {
        ArrayList<Character> chariot = new ArrayList<Character>();
        chariot.add('a');
        chariot.add('b');
        chariot.add('a');
        chariot.add('i');
        chariot.add('s');
        chariot.add('s');
        //chariot.add('e');
        this.etatDuJeu.getInventaire().setLettres(chariot);

        MotPositionne motAPoserVer = new MotPositionne("z", 7, 7, true);
        MotPositionne motAPoserVer2 = new MotPositionne("chameau", 0, 0, true);
        this.etatDuJeu.getPlateau().poserMot(motAPoserVer);
        this.etatDuJeu.getPlateau().poserMot(motAPoserVer2);

        MotPositionne motTrouve = this.client.trouverMotAvecPlateau(this.etatDuJeu, this.mots.getListeDeMots());
        assertEquals(false, motTrouve.getHorizontal());
    }

    @Test
    void trouverMotAvecPlateauTestMotsAdjacents() {
        ArrayList<Character> chariot = new ArrayList<Character>();
        chariot.add('l');
        this.etatDuJeu.getInventaire().setLettres(chariot);

        MotPositionne motAPoserVer = new MotPositionne("ample", 7, 7, true);
        MotPositionne motAPoserVer2 = new MotPositionne("rythmes", 8, 3, false);
        MotPositionne motAPoserVer3 = new MotPositionne("us", 7, 9, true);
        this.etatDuJeu.getPlateau().poserMot(motAPoserVer);
        this.etatDuJeu.getPlateau().poserMot(motAPoserVer2);
        this.etatDuJeu.getPlateau().poserMot(motAPoserVer3);

        MotPositionne motTrouve = this.client.trouverMotAvecPlateau(this.etatDuJeu, this.mots.getListeDeMots());
        this.etatDuJeu.getPlateau().poserMot(motTrouve);
        assertEquals(7, motTrouve.getAbscisse());
        assertEquals(7, motTrouve.getOrdonnee());
    }

}