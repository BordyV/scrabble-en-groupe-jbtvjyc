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
        laCase = new Case(Bonus.MotTriple);
        laCaseMD = new Case(Bonus.MotDouble);
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
    void poserMotVer() {
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
        int score = plateau.getScoreCase(plateau.getCase(7,7));
        //le mot poser est "chameau" on test donc si le score est 3 car c=3
        Assertions.assertEquals(3, score);
    }

    /**
     * test score d'un char
     */
    @Test
    void getScore() {
        Plateau plateau = new Plateau();

        plateau.poserMot(motAPoserVer);
        int score = plateau.getScore('b');
        //le mot poser est "chameau" on test donc si le score est 3 car c=3
        Assertions.assertEquals(3, score);
    }
}
