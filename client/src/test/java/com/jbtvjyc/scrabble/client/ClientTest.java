package com.jbtvjyc.scrabble.client;

import com.jbtvjyc.scrabble.data.EtatDuJeu;
import com.jbtvjyc.scrabble.data.MotPositionne;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.jbtvjyc.scrabble.data.Plateau;
import com.jbtvjyc.scrabble.data.Mots;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientTest {

    EtatDuJeu etatDuJeu;
    Mots mots;
    Client client;

    @BeforeEach
    void setup() {
        this.etatDuJeu = new EtatDuJeu(new Plateau());
        this.mots = new Mots();
        this.client = new Client();
    }

    @Test
    void trouverMotTest() {
        ArrayList<Character> chariot = new ArrayList<Character>();
        chariot.add('a');
        chariot.add('b');
        chariot.add('a');
        chariot.add('i');
        chariot.add('s');
        chariot.add('s');
        chariot.add('e');
        this.etatDuJeu.setChariot(chariot);
        MotPositionne motTrouve = this.client.trouverMot(this.etatDuJeu, this.mots.getListeDeMots());
        assertEquals(motTrouve.getMot(), "abaisse");
    }
}