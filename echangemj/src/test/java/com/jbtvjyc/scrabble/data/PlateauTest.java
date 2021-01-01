package com.jbtvjyc.scrabble.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlateauTest {

    Case laCase;
    Case laCaseMD;
    MotPositionne motAPoserHor;
    MotPositionne motAPoserVer;

    @BeforeEach
    void setUp() {
        laCase = new Case(Bonus.MOTTRIPLE);
        laCaseMD = new Case(Bonus.MOTDOUBLE);
        motAPoserHor = new MotPositionne("kaleidoscope", 0, 0, true);
        motAPoserVer = new MotPositionne("chameau", 7, 7, false);

    }

    @Test
    void creationPlateauTest() {
        Plateau plateau = new Plateau();
        Assertions.assertEquals(plateau.getLePlateau().length, 15);
    }

    @Test
    void getCaseTest() {
        Plateau plateau = new Plateau();

        Assertions.assertEquals(plateau.getCase(0,0).getBonus(), laCase.getBonus());
        Assertions.assertEquals(plateau.getCase(14,14).getBonus(), laCase.getBonus());
        Assertions.assertEquals(plateau.getCase(7,7).getBonus(), laCaseMD.getBonus());
    }

    @Test
    void poserMotHorTest() {
        Plateau plateau = new Plateau();

        plateau.poserMot(motAPoserHor);
        //le mot poser est "kaleidoscope" on test donc le debut et la fin
        Assertions.assertEquals('k', plateau.getCase(0,0).getValeur());
        Assertions.assertEquals('e', plateau.getCase(0,11).getValeur());
    }

    /**
     * test pour poser un mot en vertical
     */
    @Test
    void poserMotVerTest() {
        Plateau plateau = new Plateau();

        plateau.poserMot(motAPoserVer);
        //le mot poser est "chameau" on test donc le debut et la fin
        Assertions.assertEquals('c', plateau.getCase(7,7).getValeur());
        Assertions.assertEquals('u', plateau.getCase(13,7).getValeur());
    }

    /**
     * test score X et Y
     */
    @Test
    void getScoreCaseXYTest() {
        Plateau plateau = new Plateau();

        plateau.poserMot(motAPoserVer);
        int score = plateau.getScoreCaseXY(7,7);
        //le mot poser est "chameau" on test donc si le score est 3 car c=3
        Assertions.assertEquals(3, score);
    }

    /**
     * test score d'une case
     */
    @Test
    void getScoreCaseTest() {
        Plateau plateau = new Plateau();

        plateau.poserMot(motAPoserVer);
        plateau.getCase(7,7).setBonusActif(true);
        int score = plateau.getScoreCase(plateau.getCase(7,7));
        //le mot poser est "chameau" on test donc si le score est 3 car c=3
        Assertions.assertEquals(3, score);

        System.out.println("");
        MotPositionne motAPoserHor2 = new MotPositionne("chat", 5, 5, true);
        plateau.poserMot(motAPoserHor2);
        plateau.getCase(5,5).setBonusActif(true);
        int score2  = plateau.getScoreCase(plateau.getCase(5, 5));
        Assertions.assertEquals(9, score2);
    }

    /**
     * test score d'un char
     */
    @Test
    void getScoreTest() {
        Plateau plateau = new Plateau();

        plateau.poserMot(motAPoserVer);
        int score = plateau.getScore('b');
        //le mot poser est "chameau" on test donc si le score est 3 car c=3
        Assertions.assertEquals(3, score);
    }

    /**
     * test score d'un mot
     */
    @Test
    void getScoreMotTest() {
        Plateau plateau = new Plateau();
        MotPositionne motAPoserHor2 = new MotPositionne("chien", 0, 0);
        int scoreMot = plateau.poserMot(motAPoserHor2);
        //chien c=3, h=4, i=1, e=1, n=1
        // motTriple l3=lettreDouble
        // e=2 ; 3*(3+4+1+2+1) = 33
        Assertions.assertEquals(33, scoreMot);

        Plateau plateau2 = new Plateau();
        MotPositionne motAPoserHor3 = new MotPositionne("cheval", 0, 0);
        int scoreMot2 = plateau2.poserMot(motAPoserHor3);
        //chien c=3, h=4, e=1, v=4, a=1, l=1
        // motTriple l3=lettreDouble
        // v=8 ; 3*(3+4+1+8+1+1) = 54
        Assertions.assertEquals(54, scoreMot2);

        MotPositionne motAPoserHor4 = new MotPositionne("chevalier", 0, 0);
        int scoreMot3 = plateau2.poserMot(motAPoserHor4);
        //chien c=3, h=4, e=1, v=4, a=1, l=1, i=1, e=1, r=1
        // motTriple
        // 3*(3+4+1+4+1+1+1+1+1) = 3*(8+9) = 3*17 = 51
        Assertions.assertEquals(51, scoreMot3);

        plateau = new Plateau();
        motAPoserHor2 = new MotPositionne("chien", 2, 5);
        int scoreMot4 = plateau.poserMot(motAPoserHor2);

    }

    /**
     * test score d'un mot avec mot accole
     */
    @Test
    void motAccoleTest() {
        Plateau plateau = new Plateau();
        MotPositionne motAPoser2 = new MotPositionne("test", 0, 0, true);
        MotPositionne motAPoser3 = new MotPositionne("ta", 0, 0, false);
        MotPositionne motAPoser4 = new MotPositionne("an", 0, 1, true);
        plateau.poserMot(motAPoser2);
        plateau.poserMot(motAPoser3);
        int scoreMot5=plateau.poserMot(motAPoser4);
        //System.out.println(plateau);
        Assertions.assertEquals(8, scoreMot5);

        plateau = new Plateau();
        motAPoser2 = new MotPositionne("test", 3, 10, true);
        motAPoser3 = new MotPositionne("ta", 3, 10, false);
        motAPoser4 = new MotPositionne("an", 3, 11, true);
        plateau.poserMot(motAPoser2);
        plateau.poserMot(motAPoser3);
        scoreMot5=plateau.poserMot(motAPoser4);
        //System.out.println(plateau);
        Assertions.assertEquals(4, scoreMot5);

        plateau = new Plateau();
        motAPoser2 = new MotPositionne("test", 0, 0, false);
        motAPoser3 = new MotPositionne("ta", 0, 0, true);
        motAPoser4 = new MotPositionne("an", 1, 0, false);
        plateau.poserMot(motAPoser2);
        plateau.poserMot(motAPoser3);
        scoreMot5=plateau.poserMot(motAPoser4);
        //System.out.println(plateau);
        Assertions.assertEquals(8, scoreMot5);

        plateau = new Plateau();
        motAPoser2 = new MotPositionne("test", 14, 11, false);
        motAPoser3 = new MotPositionne("at", 13, 14, true);
        motAPoser4 = new MotPositionne("na", 13, 13, false);
        plateau.poserMot(motAPoser2);
        plateau.poserMot(motAPoser3);
        scoreMot5=plateau.poserMot(motAPoser4);
        //System.out.println(plateau);
        Assertions.assertEquals(8, scoreMot5);
    }
}
