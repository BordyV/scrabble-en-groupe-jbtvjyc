package com.jbtvjyc.scrabble.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.not;
import static org.assertj.core.api.InstanceOfAssertFactories.ARRAY;
import static org.hamcrest.Matchers.equalTo;

class EtatDuJeuTests {

    EtatDuJeu etatDuJeu;

    @BeforeEach void setUp() {
        etatDuJeu = new EtatDuJeu();

        List<Character> chariotTest = new ArrayList<>();
        chariotTest.add('a');
        chariotTest.add('b');
        chariotTest.add('c');
        chariotTest.add('d');
        chariotTest.add('e');
        chariotTest.add('f');
        chariotTest.add('g');
        etatDuJeu.getInventaire().setLettres(chariotTest);
    }

    @Test
    void testGetListeDeMots() {
        List<MotPositionne> listeDeMotsTest = new ArrayList<>();
        listeDeMotsTest.add(new MotPositionne("chameaux",1,1));
        listeDeMotsTest.add(new MotPositionne("vache",1,1));
        listeDeMotsTest.add(new MotPositionne("moto",1,1));
        etatDuJeu.setListeDeMots(listeDeMotsTest);
        Assertions.assertEquals(listeDeMotsTest, etatDuJeu.getListeDeMots());
    }

    @Test
    void testGetListeDeMots2() {
        List<Character> chariotTest = new ArrayList<>();
        chariotTest.add(new Character('a'));
        chariotTest.add(new Character('b'));
        chariotTest.add(new Character('c'));
        etatDuJeu.getInventaire().setLettres(chariotTest);
        Assertions.assertEquals(chariotTest, etatDuJeu.getInventaire().getLettres());
    }

    @Test
    void testAjouterLettres() {
        List<Character> chariotTest = new ArrayList<>();
        chariotTest.add(new Character('a'));
        chariotTest.add(new Character('b'));
        chariotTest.add(new Character('c'));

        List<Character> chariotInventaireTest = new ArrayList<>();
        chariotInventaireTest.add(new Character('a'));
        chariotInventaireTest.add(new Character('b'));
        chariotInventaireTest.add(new Character('c'));
        etatDuJeu.getInventaire().setLettres(chariotInventaireTest);
        Assertions.assertEquals(chariotTest, etatDuJeu.getInventaire().getLettres());
    }

    @Test
    void piocherLettreTest() {
        List<Character> deleteLetter = new ArrayList<>();
        deleteLetter.add('f');
        etatDuJeu.getInventaire().enleverLettres(deleteLetter);
        Assertions.assertEquals(6, etatDuJeu.getInventaire().getLettres().toArray().length);
        etatDuJeu.piocherLettre();
        Assertions.assertEquals(7, etatDuJeu.getInventaire().getLettres().toArray().length);
    }

    @Test
    void piocherAucuneLettreTest() {
        etatDuJeu.piocherLettre();
        Assertions.assertEquals(7, etatDuJeu.getInventaire().getLettres().toArray().length);
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

    @Test
    void changerLettresTest() {
        List<Character> lettresInventaire = new ArrayList<>();
        lettresInventaire.add('a');
        lettresInventaire.add('b');
        lettresInventaire.add('c');
        lettresInventaire.add('d');
        lettresInventaire.add('e');
        lettresInventaire.add('f');
        lettresInventaire.add('g');
        etatDuJeu.getInventaire().setLettres(lettresInventaire);
        etatDuJeu.changerLettres();
        Assertions.assertEquals(Arrays.toString(lettresInventaire.toArray()), Arrays.toString(etatDuJeu.getInventaire().getLettres().toArray()));

    }

}