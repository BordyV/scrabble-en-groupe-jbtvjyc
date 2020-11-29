package com.jbtvjyc.scrabble.serveur;

import com.jbtvjyc.scrabble.data.EtatDuJeu;
import com.jbtvjyc.scrabble.serveur.Moteur;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoteurTests {

    @Test
    void testGetPlateau() {
        Moteur moteur = new Moteur();
        assertEquals(moteur.getPlateau(), moteur.etatDuJeu);
    }
}