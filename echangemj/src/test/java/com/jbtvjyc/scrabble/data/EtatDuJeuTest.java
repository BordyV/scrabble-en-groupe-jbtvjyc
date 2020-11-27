package com.jbtvjyc.scrabble.data;

import com.jbtvjyc.scrabble.data.EtatDuJeu;
import com.jbtvjyc.scrabble.data.MotPositionne;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EtatDuJeuTest {

    @Test
    void testGetListeDeMots() {
        EtatDuJeu etatDuJeu = new EtatDuJeu();
        ArrayList<MotPositionne> listeDeMotsTest = new ArrayList<>();
        ArrayList<Character> chariotTest = new ArrayList<>();
        listeDeMotsTest.add(new MotPositionne("chameaux"));
        listeDeMotsTest.add(new MotPositionne("vache"));
        listeDeMotsTest.add(new MotPositionne("moto"));
        etatDuJeu.setListeDeMots(listeDeMotsTest);
        Assertions.assertEquals(listeDeMotsTest, etatDuJeu.getListeDeMots());
    }

    @Test
    void testGetListeDeMots2() {
        EtatDuJeu etatDuJeu = new EtatDuJeu();
        ArrayList<MotPositionne> listeDeMotsTest = new ArrayList<>();
        ArrayList<Character> chariotTest = new ArrayList<>();
        chariotTest.add(new Character('a'));
        chariotTest.add(new Character('b'));
        chariotTest.add(new Character('c'));
        etatDuJeu.setChariot(chariotTest);
        Assertions.assertEquals(chariotTest, etatDuJeu.getChariot());
    }

    @Test
    void testAjouterLettres() {
        EtatDuJeu etatDuJeu = new EtatDuJeu();
        ArrayList<Character> chariotTest = new ArrayList<>();
        chariotTest.add(new Character('a'));
        chariotTest.add(new Character('b'));
        chariotTest.add(new Character('c'));
        Character ch1 = new Character('a');
        Character ch2 = new Character('b');
        Character ch3 = new Character('c');
        etatDuJeu.ajouterLettres(ch1, ch2, ch3);
        Assertions.assertEquals(chariotTest, etatDuJeu.getChariot());
    }

    @Test
    void testAddMotPlace() {
        EtatDuJeu etatDuJeu = new EtatDuJeu();
        MotPositionne motPositionne= new MotPositionne();
        motPositionne.setMot("cheval");
        ArrayList<MotPositionne> listeDeMotsTest = new ArrayList<>();
        listeDeMotsTest.add(motPositionne);
        etatDuJeu.addMotPlace(motPositionne);
        Assertions.assertEquals(listeDeMotsTest, etatDuJeu.getListeDeMots());
    }
}