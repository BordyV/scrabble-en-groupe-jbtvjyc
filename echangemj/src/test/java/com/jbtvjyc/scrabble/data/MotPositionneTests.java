package com.jbtvjyc.scrabble.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MotPositionneTests {

    @Test
    void testSetGetMot() {
        MotPositionne motPositionne= new MotPositionne();
        motPositionne.setMot("cheval");
        Assertions.assertEquals( "cheval", motPositionne.getMot());
    }

    @Test
    void testSetGetAbscisse() {
        MotPositionne motPositionne= new MotPositionne();
        motPositionne.setAbscisse(5);
        Assertions.assertEquals( 5, motPositionne.getAbscisse());
    }

    @Test
    void testSetGetOrdonnee() {
        MotPositionne motPositionne= new MotPositionne();
        motPositionne.setOrdonnee(4);
        Assertions.assertEquals( 4, motPositionne.getOrdonnee());
    }

    @Test
    void testSetGetHorizontal() {
        MotPositionne motPositionne= new MotPositionne();
        motPositionne.setHorizontal(false);
        Assertions.assertEquals( false, motPositionne.getHorizontal());
    }

    /*@Test
    void testToString() {
        MotPositionne motPositionne= new MotPositionne();
        String dir = "horizontal";

        if (!motPositionne.getHorizontal()) {
            dir = "vertical";
        }
        Assertions.assertEquals( "(" + motPositionne.getMot() + "," + motPositionne.getAbscisse() + "," + motPositionne.getOrdonnee() + "," + dir + ")", MotPositionne.toString());
    }*/
}