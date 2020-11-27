package com.jbtvjyc.scrabble.serveur;

import com.jbtvjyc.scrabble.data.MotPositionne;
import com.jbtvjyc.scrabble.serveur.dto.Bonus;
import com.jbtvjyc.scrabble.serveur.dto.Case;
import com.jbtvjyc.scrabble.serveur.dto.Plateau;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

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

        assertEquals(plateau.getLePlateau().length, 15);
    }

    @Test
    void getCaseTest() {
        Plateau plateau = new Plateau();

        assertEquals(plateau.getCasePlateau(0,0).getBonus(), laCase.getBonus());
        assertEquals(plateau.getCasePlateau(14,14).getBonus(), laCase.getBonus());
        assertEquals(plateau.getCasePlateau(7,7).getBonus(), laCaseMD.getBonus());
    }

    @Test
    void poserMotHorTest() {
        Plateau plateau = new Plateau();

        plateau.poserMot(motAPoserHor);
        //le mot poser est "kaleidoscope" on test donc le debut et la fin
        assertEquals('k', plateau.getCasePlateau(0,0).getValeur());
        assertEquals('e', plateau.getCasePlateau(11,0).getValeur());
    }

    @Test
    void poserMotVer() {
        Plateau plateau = new Plateau();

        plateau.poserMot(motAPoserVer);
        //le mot poser est "chameau" on test donc le debut et la fin
        assertEquals('c', plateau.getCasePlateau(7,7).getValeur());
        assertEquals('u', plateau.getCasePlateau(7,13).getValeur());
    }
}
