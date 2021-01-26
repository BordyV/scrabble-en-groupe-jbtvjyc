package com.jbtvjyc.scrabble.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class PiocheTests {

    @Test
    void testInitialisationTailleDuSac() {
        Pioche pioche = new Pioche();
        Assertions.assertEquals( 100, pioche.getTailleDuSac());
    }

    @Test
    void rendreUneLettreTest() {
        Pioche pioche = new Pioche();
        //12 est la lettre m
        pioche.getSac().set(12,0);
        Assertions.assertEquals(0 , pioche.getSac().get(12));

        pioche.rendreUneLettre('m');
        Assertions.assertEquals(1,pioche.getSac().get(12));
    }


    @Test
    void rendrePlusieursLettresTest() {
        Pioche pioche = new Pioche();
        //12 est la lettre m
        pioche.getSac().set(12,0);
        Assertions.assertEquals(0 , pioche.getSac().get(12));
        //25 est la lettre z
        pioche.getSac().set(25,0);
        Assertions.assertEquals(0 , pioche.getSac().get(25));

        //taille du sac -3(m) + -1(z)
        pioche.setTailleDuSac(96);

        List<Character> lettresARendre = new ArrayList<Character>();
        lettresARendre.add('m');
        lettresARendre.add('z');

        //on rend la lettre m et z
        pioche.rendrePlusieursLettres(lettresARendre);
        Assertions.assertEquals(1,pioche.getSac().get(12));
        Assertions.assertEquals(1,pioche.getSac().get(25));
        Assertions.assertEquals(98, pioche.getTailleDuSac());
    }

    @Test
    void testSacVide() {
        Pioche pioche = new Pioche();
        pioche.piocherUneLettre();
        Assertions.assertEquals(false, pioche.getSacVide());
        pioche = new Pioche();
        for(int i = 0; i<110; i++){
            pioche.piocherUneLettre();
        }
        Assertions.assertEquals(true, pioche.getSacVide());
    }

    @Test
    void testListDeLettre() {
        Pioche pioche = new Pioche();
        for (int i = 0; i<26; i++){
            Assertions.assertNotEquals(0, pioche.getSac().get(i));
        }
        Assertions.assertEquals(15, pioche.getSac().get(4));
        for(int i = 0; i<100; i++){
            pioche.piocherUneLettre();
        }
        for (int i = 0; i<26; i++){
            Assertions.assertEquals(true, pioche.lettreVide(i));
        }
    }

    @Test
    void testPiocherPlusieursLettres(){
        Pioche pioche = new Pioche();
        EtatDuJeu etatDuJeu = new EtatDuJeu();
        pioche.piocherPlusieursLettres(etatDuJeu, 4);
        Assertions.assertEquals(96, pioche.getTailleDuSac());
    }

}
