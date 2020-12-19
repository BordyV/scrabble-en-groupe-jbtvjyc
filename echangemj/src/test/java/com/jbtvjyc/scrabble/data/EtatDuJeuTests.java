package com.jbtvjyc.scrabble.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class EtatDuJeuTests {

    @Test
    void testGetListeDeMots() {
        EtatDuJeu etatDuJeu = new EtatDuJeu();
        List<MotPositionne> listeDeMotsTest = new ArrayList<>();
        listeDeMotsTest.add(new MotPositionne("chameaux",1,1));
        listeDeMotsTest.add(new MotPositionne("vache",1,1));
        listeDeMotsTest.add(new MotPositionne("moto",1,1));
        etatDuJeu.setListeDeMots(listeDeMotsTest);
        Assertions.assertEquals(listeDeMotsTest, etatDuJeu.getListeDeMots());
    }

    @Test
    void testGetListeDeMots2() {
        EtatDuJeu etatDuJeu = new EtatDuJeu();
        List<Character> chariotTest = new ArrayList<>();
        chariotTest.add(new Character('a'));
        chariotTest.add(new Character('b'));
        chariotTest.add(new Character('c'));
        etatDuJeu.getInventaire().setLettres(chariotTest);
        Assertions.assertEquals(chariotTest, etatDuJeu.getInventaire().getLettres());
    }

    @Test
    void testAjouterLettres() {
        EtatDuJeu etatDuJeu = new EtatDuJeu();
        List<Character> chariotTest = new ArrayList<>();
        chariotTest.add(new Character('a'));
        chariotTest.add(new Character('b'));
        chariotTest.add(new Character('c'));
        Character ch1 = new Character('a');
        Character ch2 = new Character('b');
        Character ch3 = new Character('c');
        etatDuJeu.ajouterLettres(ch1, ch2, ch3);
        Assertions.assertEquals(chariotTest, etatDuJeu.getInventaire().getLettres());
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