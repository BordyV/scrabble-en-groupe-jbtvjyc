package com.jbtvjyc.scrabble.data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InventaireTests {

    Inventaire invTest;
    Statistique stats;

    @BeforeEach
    void setUp(){
        this.invTest = new Inventaire(stats);
    }
    @AfterEach
    void tearDown(){}
    @Test
    void InventaireAdd() {
        List<Character> lettres = new ArrayList<>();
        lettres.add('a');
        lettres.add('b');
        lettres.add('c');
        assertEquals(invTest.lettres,invTest.getLettres());
        invTest.setLettres(lettres);
        assertEquals(lettres,invTest.getLettres());
        invTest.ajouterLettres('d','e','f');
        lettres.add('d');
        lettres.add('e');
        lettres.add('f');
        assertEquals(lettres,invTest.getLettres());
        assertEquals(0,invTest.score);
        invTest.score += 500;
        assertEquals(500,invTest.getScore());
        invTest.addScore(500);
        assertEquals(1000,invTest.getScore());
    }

}