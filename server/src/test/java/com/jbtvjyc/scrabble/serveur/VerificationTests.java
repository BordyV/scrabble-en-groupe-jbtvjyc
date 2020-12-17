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
        this.verifTest = new Verification(lettres,position,plateau,lesMots);

    }

    @AfterEach
    void tearDown(){}

    //Test pour voir si le mot existe sans lettre sur le plateau en horizontale
    @Test
    void verificationTrueHori(){
        assertTrue(verifTest.verifMot());
    }

    //Test pour voir si le mot Manger en (7,7) fonctionne avec le mot adjacent Rire en verticale en (12,7)
    @Test
    void verificationAdjacentHoriVertiTrue() {
        MotPositionne rireVerti = new MotPositionne("rire",12,7,false);
        verifTest.plateau.poserMot(rireVerti);
        assertTrue(verifTest.verifMot());
    }
    //Test pour voir si le mot Manger en (7,7) ne fonctionne pas avec le mot adjacent Riret en verticale en (12,7)
    @Test
    void verificationAdjacentHoriVertiFalse() {
        MotPositionne riretVerti = new MotPositionne("riret",12,7,false);
        verifTest.plateau.poserMot(riretVerti);
        assertFalse(verifTest.verifMot());
    }
    //Test pour voir si le mot Manger en (7,7) fonctionne avec le mot adjacent Rire en verticale en (12,5)
    @Test
    void verificationAdjacentHoriVertiMilieuTrue() {
        MotPositionne rireVerti = new MotPositionne("rire",12,5,false);
        verifTest.plateau.poserMot(rireVerti);
        assertTrue(verifTest.verifMot());
    }
    //Test pour voir si le mot Manger en (7,7) ne fonctionne pas avec le mot adjacent Riret en verticale en (12,5)
    @Test
    void verificationAdjacentHoriVertiMilieuFalse() {
        MotPositionne riretVerti = new MotPositionne("riret",12,5,false);
        verifTest.plateau.poserMot(riretVerti);
        assertFalse(verifTest.verifMot());
    }
    //Test pour voir si le mot Manger en (7,7) fonctionne quand il s'ajoute à la fin du mot De en (5,7) et forme Démanger
    @Test
    void verificationAdjacentDevantHoriTrue() {
        MotPositionne deHori = new MotPositionne("de",5,7,true);
        verifTest.plateau.poserMot(deHori);
        assertTrue(verifTest.verifMot());
    }
    //Test pour voir si le mot Manger en (7,7) ne fonctionne  pas quand il s'ajoute à la fin du mot Det en (4,7) et forme Détmanger
    @Test
    void verificationAdjacentDevantHoriFalse() {
        MotPositionne detHori = new MotPositionne("det",4,7,true);
        verifTest.plateau.poserMot(detHori);
        assertFalse(verifTest.verifMot());
    }
    //Test pour voir si le mot Manger en (7,7) fonctionne avec les mots adjacents en verticale Rire en (12,5) et Chanter en (8,5)
    @Test
    void verificationDeuxAdjacentHoriVertiMilieuTrue() {
        MotPositionne rireVerti = new MotPositionne("rire",12,5,false);
        MotPositionne chanterVerti = new MotPositionne("chanter",8,5,false);
        verifTest.plateau.poserMot(rireVerti);
        verifTest.plateau.poserMot(chanterVerti);
        assertTrue(verifTest.verifMot());
    }
    //Test pour voir si le mot Manger en (7,7) ne fonctionne pas avec les mots adjacents en verticale Rire en (12,5) et Chanter en (8,5)
    @Test
    void verificationDeuxAdjacentHoriVertiMilieuFalse() {
        MotPositionne riretVerti = new MotPositionne("riret",12,5,false);
        MotPositionne chantertVerti = new MotPositionne("chantert",8,5,false);
        verifTest.plateau.poserMot(riretVerti);
        verifTest.plateau.poserMot(chantertVerti);
        assertFalse(verifTest.verifMot());
    }
    //Test pour voir si le mot Manger en (7,7) fonctionne avec les mots adjacents en verticale Rire en (12,5) ,Chanter en (8,5) et N en (11,6)
    @Test
    void verificationTroisAdjacentHoriVertiMilieuTrue() {
        MotPositionne rireVerti = new MotPositionne("rire",12,5,false);
        MotPositionne chanterVerti = new MotPositionne("chanter",8,5,false);
        verifTest.plateau.setValeurCasePlateau('n', 6, 11);
        verifTest.plateau.poserMot(rireVerti);
        verifTest.plateau.poserMot(chanterVerti);
        assertTrue(verifTest.verifMot());
    }
    //Test pour voir si le mot Manger en (7,7) ne fonctionne pas avec les mots adjacents en verticale Rire en (12,5) ,Chanter en (8,5) et B en (11,6)
    @Test
    void verificationTroisAdjacentHoriVertiMilieuFalse() {
        MotPositionne rireVerti = new MotPositionne("rire",12,5,false);
        MotPositionne chanterVerti = new MotPositionne("chanter",8,5,false);
        verifTest.plateau.setValeurCasePlateau('b', 6, 11);
        verifTest.plateau.poserMot(rireVerti);
        verifTest.plateau.poserMot(chanterVerti);
        assertFalse(verifTest.verifMot());
    }
    //Test pour voir si le mot Manger en (7,7) fonctionne avec le mot adjacent Rire en horizontal en (7,12)
    @Test
    void verificationAdjacentVertiHoriTrue() {
        verifTest.motPosition.setHorizontal(false);
        MotPositionne rireHori = new MotPositionne("rire",7,12,true);
        verifTest.plateau.poserMot(rireHori);
        assertTrue(verifTest.verifMot());
    }
    //Test pour voir si le mot Manger en (7,7) ne fonctionne pas avec le mot adjacent Riret en horizontal en (7,12)
    @Test
    void verificationAdjacentVertiHoriFalse() {
        verifTest.motPosition.setHorizontal(false);
        MotPositionne riretHori = new MotPositionne("riret",7,12,true);
        verifTest.plateau.poserMot(riretHori);
        assertFalse(verifTest.verifMot());
    }
    //Test pour voir si le mot Manger en (7,7) fonctionne avec le mot adjacent Rire en horizontal en (5,12)
    @Test
    void verificationAdjacentVertiHoriMilieuTrue() {
        verifTest.motPosition.setHorizontal(false);
        MotPositionne rireHori = new MotPositionne("rire",5,12,true);
        verifTest.plateau.poserMot(rireHori);
        assertTrue(verifTest.verifMot());
    }
    //Test pour voir si le mot Manger en (7,7) ne fonctionne pas avec le mot adjacent Riret en horizontal en (5,12)
    @Test
    void verificationAdjacentVertiHoriMilieuFalse() {
        verifTest.motPosition.setHorizontal(false);
        MotPositionne riretHori = new MotPositionne("riret",5,12,true);
        verifTest.plateau.poserMot(riretHori);
        assertFalse(verifTest.verifMot());
    }
    //Test pour voir si le mot Manger en (7,7) fonctionne quand il s'ajoute à la fin du mot De en (7,5) et forme Démanger
    @Test
    void verificationAdjacentDevantVertiTrue() {
        verifTest.motPosition.setHorizontal(false);
        MotPositionne deVerti = new MotPositionne("de",7,5,false);
        verifTest.plateau.poserMot(deVerti);
        assertTrue(verifTest.verifMot());
    }
    //Test pour voir si le mot Manger en (7,7) ne fonctionne  pas quand il s'ajoute à la fin du mot Det en (7,4) et forme Détmanger
    @Test
    void verificationAdjacentDevantVertiFalse() {
        verifTest.motPosition.setHorizontal(false);
        MotPositionne detVerti = new MotPositionne("det",7,4,false);
        verifTest.plateau.poserMot(detVerti);
        assertFalse(verifTest.verifMot());
    }
    //Test pour voir si le mot Manger en (7,7) ne fonctionne pas quand il s'ajoute au debut du mot N en (7,13) et forme Mangern
    @Test
    void verificationAdjacentDerriereVertiFalse() {
        verifTest.motPosition.setHorizontal(false);
        verifTest.plateau.setValeurCasePlateau('n', 13, 7);
        assertFalse(verifTest.verifMot());
    }
    //Test pour voir si le mot Manger en (7,7) fonctionne avec les mots adjacents en horizontal Rire en (5,12) et Chanter en (5,8)
    @Test
    void verificationDeuxAdjacentVertiHoriMilieuTrue() {
        verifTest.motPosition.setHorizontal(false);
        MotPositionne rireHori = new MotPositionne("rire",5,12,true);
        MotPositionne chanterHori = new MotPositionne("chanter",5,8,true);
        verifTest.plateau.poserMot(rireHori);
        verifTest.plateau.poserMot(chanterHori);
        assertTrue(verifTest.verifMot());
    }
    //Test pour voir si le mot Manger en (7,7) ne fonctionne pas avec les mots adjacents en horizontal Riret en (5,12) et Chantert en (5,8)
    @Test
    void verificationDeuxAdjacentVertiHoriMilieuFalse() {
        verifTest.motPosition.setHorizontal(false);
        MotPositionne riretHori = new MotPositionne("riret",5,12,true);
        MotPositionne chantertHori = new MotPositionne("chantert",5,8,true);
        verifTest.plateau.poserMot(riretHori);
        verifTest.plateau.poserMot(chantertHori);
        assertFalse(verifTest.verifMot());
    }
    //Test pour voir si le mot Manger en (7,7) fonctionne avec les mots adjacents en horizontal Rire en (5,12) ,Chanter en (5,8) et N en (6,11)
    @Test
    void verificationTroisAdjacentVertiHoriMilieuTrue() {
        verifTest.motPosition.setHorizontal(false);
        MotPositionne rireHori = new MotPositionne("rire",5,12,true);
        MotPositionne chanterHori = new MotPositionne("chanter",5,8,true);
        verifTest.plateau.setValeurCasePlateau('n', 11, 6);
        verifTest.plateau.poserMot(rireHori);
        verifTest.plateau.poserMot(chanterHori);
        assertTrue(verifTest.verifMot());
    }
    //Test pour voir si le mot Manger en (7,7) ne fonctionne pas avec les mots adjacents en horizontal Rire en (5,12) ,Chanter en (5,8) et B en (6,11)
    @Test
    void verificationTroisAdjacentVertiHoriMilieuFalse() {
        verifTest.motPosition.setHorizontal(false);
        MotPositionne rireHori = new MotPositionne("rire",5,12,true);
        MotPositionne chanterHori = new MotPositionne("chanter",5,8,true);
        verifTest.plateau.setValeurCasePlateau('b', 11, 6);
        verifTest.plateau.poserMot(rireHori);
        verifTest.plateau.poserMot(chanterHori);
        assertFalse(verifTest.verifMot());
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

    //Test pour voir si le joueur pose le même mot sur un mot déjà posé sur le palteau
    @Test
    void verificationFalseMotDejaExistant() {
        MotPositionne manger = new MotPositionne("manger",7,7,true);
        verifTest.plateau.poserMot(manger);
        assertFalse(verifTest.verifMot());
    }
}

