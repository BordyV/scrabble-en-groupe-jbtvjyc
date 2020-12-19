package com.jbtvjyc.scrabble.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class PiocheTests {

    @Test
    void testInitialisationTailleDuSac() {
        Pioche pioche = new Pioche();
        Assertions.assertEquals( 100, pioche.getTailleDuSac());
    }

    @Test
    void testSacVide() {
        Pioche pioche = new Pioche();
        pioche.piocherUneLettre();
        Assertions.assertEquals(false, pioche.getSacVide());
        pioche = new Pioche();
        for(int i = 0; i<100; i++){
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
