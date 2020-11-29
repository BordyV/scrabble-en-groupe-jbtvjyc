package com.jbtvjyc.scrabble.serveur;
import com.jbtvjyc.scrabble.data.MotPositionne;
import com.jbtvjyc.scrabble.data.Mots;
import com.jbtvjyc.scrabble.data.Plateau;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class VerificationTests {

    Verification verifTest;

    @BeforeEach
    void setUp(){
        MotPositionne position = new MotPositionne("manger",7,7,true);
        Plateau plateau = new Plateau();
        Mots lesMots = new Mots();
        ArrayList<Character> lettres = new ArrayList<>();
        lettres.add('m');
        lettres.add('a');
        lettres.add('n');
        lettres.add('g');
        lettres.add('e');
        lettres.add('r');
        this.verifTest = new Verification(lettres,position,plateau, lesMots);

    }

    @AfterEach
    void tearDown(){}

    //Test pour voir si le mot existe sans lettre sur le plateau en horizontale
    @Test
    void verificationTrueHori(){
        assertTrue(verifTest.verifMot());
    }

    //Test pour voir si le mot qui commence par Z existe sans lettre sur le plateau en horizontale
    @Test
    void verificationTrueHoriZ(){
        verifTest.lettresjoueur.add('z');
        verifTest.lettresjoueur.add('o');
        verifTest.lettresjoueur.add('n');
        verifTest.lettresjoueur.add('e');
        assertTrue(verifTest.verifMot());
    }

    //Test pour voir si le mot existe sans lettre sur le plateau en horizontale et qui arrive en bout de plateau
    @Test
    void verificationTrueHoriFin(){
        verifTest.motPosition.setAbscisse(8);
        assertTrue(verifTest.verifMot());
    }

    //Test pour voir si le mot existe sans lettre sur le plateau en horizontale et qui commence en bout de plateau
    @Test
    void verificationTrueHoriDebut(){
        verifTest.motPosition.setAbscisse(0);
        assertTrue(verifTest.verifMot());
    }

    //Test pour voir si le mot existe sans lettre sur le plateau en verticale
    @Test
    void verificationTrueVerti(){
        verifTest.motPosition.setHorizontal(false);
        assertTrue(verifTest.verifMot());
    }

    //Test pour voir si le mot existe sans lettre sur le plateau en verticale et qui arrive en bout de plateau
    @Test
    void verificationTrueVertiFin(){
        verifTest.motPosition.setOrdonnee(8);
        verifTest.motPosition.setHorizontal(false);
        assertTrue(verifTest.verifMot());
    }

    //Test pour voir si le mot existe sans lettre sur le plateau en verticale et qui commence en bout de plateau
    @Test
    void verificationTrueVertiDebut(){
        verifTest.motPosition.setOrdonnee(0);
        verifTest.motPosition.setHorizontal(false);
        assertTrue(verifTest.verifMot());
    }

    //Test pour voir si le mot existe avec une lettre sur le plateau en horizontale
    @Test
    void verificationTrueHoriLettre(){
        verifTest.plateau.setValeurCasePlateau('n', 7, 9);
        assertTrue(verifTest.verifMot());
    }

    //Test pour voir si le mot existe avec une lettre sur le plateau en verticale
    @Test
    void verificationTrueVertiLettre(){
        verifTest.plateau.setValeurCasePlateau('a', 8, 7);
        verifTest.motPosition.setHorizontal(false);
        assertTrue(verifTest.verifMot());
    }

    //Test pour voir si le mot n'existe pas
    @Test
    void verificationFalsemot() {
        verifTest.mot = "afqsfs";
        assertFalse(verifTest.verifMot());
    }

    //Test pour voir si la première lettre du plateau se ne trouve pas sur le plateau
    @Test
    void verificationFalsePos1ereLettre() {
        verifTest.motPosition.setAbscisse(15);
        assertFalse(verifTest.verifMot());
        verifTest.motPosition.setAbscisse(-1);
        assertFalse(verifTest.verifMot());
        verifTest.motPosition.setAbscisse(15);
        assertFalse(verifTest.verifMot());
        verifTest.motPosition.setAbscisse(-1);
        assertFalse(verifTest.verifMot());
    }

    //Test pour voir si le mot ne rentre pas dans le plateau horizontalement
    @Test
    void verificationFalsePosMotHori() {
        verifTest.motPosition.setAbscisse(13);
        assertFalse(verifTest.verifMot());
    }

    //Test pour voir si le mot ne rentre pas dans le plateau verticalement
    @Test
    void verificationFalsePosMotVerti() {
        verifTest.motPosition.setOrdonnee(13);
        verifTest.motPosition.setHorizontal(false);
        assertFalse(verifTest.verifMot());
    }

    //Test pour voir si une des lettres sur le plateau ne corresponds pas horizontalement
    @Test
    void verificationFalseLettrePlateauHori() {
        verifTest.plateau.setValeurCasePlateau('f', 7, 9);
        assertFalse(verifTest.verifMot());
    }

    //Test pour voir si une des lettres sur le plateau ne corresponds pas verticalement
    @Test
    void verificationFalseLettrePlateauVerti() {
        verifTest.plateau.setValeurCasePlateau('f', 9, 7);
        verifTest.motPosition.setHorizontal(false);
        assertFalse(verifTest.verifMot());
    }

    //Test pour voir si le joueur n'a pas assez de lettre pour créer son mot
    @Test
    void verificationFalseTotaleLettreManquante() {
        verifTest.lettresjoueur.remove(0);
        verifTest.lettresjoueur.remove(1);
        verifTest.plateau.setValeurCasePlateau('a', 7, 8);
        assertFalse(verifTest.verifMot());
    }

    //Test pour voir si le joueur n'a pas une des lettres du mot
    @Test
    void verificationFalseLettreManquante() {
        verifTest.lettresjoueur.remove(0);
        verifTest.lettresjoueur.add('g');
        assertFalse(verifTest.verifMot());

    }
}

