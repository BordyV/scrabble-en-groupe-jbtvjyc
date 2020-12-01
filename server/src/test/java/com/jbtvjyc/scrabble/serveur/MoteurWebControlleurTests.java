package com.jbtvjyc.scrabble.serveur;

import com.jbtvjyc.scrabble.data.Identification;
import com.jbtvjyc.scrabble.data.EtatDuJeu;
import com.jbtvjyc.scrabble.data.MoteurWebControlleur;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoteurWebControlleurTests {

    @Test
    void testGetNomJoueur() {
        MoteurWebControlleur moteurWebControlleur = new MoteurWebControlleur();
        String resultat = "[NULL]";
        assertEquals(moteurWebControlleur.getNomJoueur(), resultat);
    }
}