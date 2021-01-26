package com.jbtvjyc.scrabble.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MoteurWebControlleurITCase {

    // data
    Identification[] ids = new Identification[4] ;
    EtatDuJeu etat;

    @SpyBean
    MoteurWebControlleur webControlleur;

    @Autowired
    Moteur moteur;

    Moteur mSpy;

    @BeforeEach
    void setUp()throws Exception {
        String[] noms = new String[4];
        noms[0] = "Mopolo le Roi des Mots";
        noms[1] = "Menez la Falaise";
        noms[2] = "Renevier le Pionnier";
        noms[3] = "Arnault le plus beau";
        for (int i=0; i < this.ids.length; i++) {
            this.ids[i] = new Identification(noms[i], "http://127.0.0.1:808"+(i+1)+"/");
        }
        this.etat = new EtatDuJeu();

        this.mSpy = Mockito.spy(this.moteur);
        ReflectionTestUtils.setField(this.webControlleur, "moteur", this.mSpy);
    }

    @Test
    void demanderAuJoueurDeJouerTest() {

        // pour que le controlleur ait un joueur...
        // l'appel extérieur qui lance tout
        for (Identification id : this.ids) {
            this.webControlleur.getValue(id);
        }

        // some code are in a thread...
        try {
            TimeUnit.MILLISECONDS.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        verify(this.mSpy, times(1)).lancerPartie();

        verify(this.webControlleur, times(500)).demanderAuJoueurDeJouer(any(), anyInt());
        verify(this.webControlleur, times(1)).envoyerFin();

        // TODO Il faudrait réussir à faire placer un certain nombre de mots sur le plateau pour le tester
        // EtatDuJeu plateau = mSpy.getEtatDuJeu();
        // assertEquals(2, plateau.getListeDeMots().size());

        verify(this.mSpy, times(500)).getEtatDuJeu();
        // error to verify it is a good spy // verify(mSpy, times(16)).aMethod();

        // normalement, à la fin le client est éteint
        assertThrows(org.springframework.web.client.ResourceAccessException.class, () -> this.mSpy.run());

        // etc.

    }



    // un test est possible avec le lancement d'une  docker. IL

    @Test
    void demanderAuJoueurDeJouerTest2foisDeSuite() {
        // pour que le controlleur ait un joueur...
        for(Identification id : this.ids) {
            this.webControlleur.getValue(id);
        }
        this.webControlleur.getValue(ids[0]);

        // some code are in a thread...
        try {
            TimeUnit.MILLISECONDS.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // 1 fois lancerPartie même avec 5 clients
        verify(this.mSpy, times(1)).lancerPartie();
        verify(this.webControlleur, times(500)).demanderAuJoueurDeJouer(any(), anyInt());
        verify(this.webControlleur, times(1)).envoyerFin();

        // TODO Pareil que demanderAuJoueurDeJoueurTest
        // EtatDuJeu plateau = mSpy.getEtatDuJeu();
        // assertEquals(2, plateau.getListeDeMots().size());

        verify(this.mSpy, times(500)).getEtatDuJeu();
        // error to verify it is a good spy // verify(mSpy, times(16)).aMethod();

        // normalement, à la fin le client est éteint
        assertThrows(org.springframework.web.client.ResourceAccessException.class, () -> this.mSpy.run());

        // etc.

    }

/*
mvn clean install -DskipTests
mvn test
docker build joueur -t scrabble:joueur
cd moteur
docker run -d --name joueur_test  -e LANCEMENT="POUR_LES_TEST" -p 8081:8081 scrabble:joueur
mvn failsafe:integration-test -Dit.test=prg.exemple.demoscrabble.MoteurWebControleurITCase#demanderAuJoueurDeJoueurTest
docker start joueur_test
mvn failsafe:integration-test -Dit.test=prg.exemple.demoscrabble.MoteurWebControleurITCase#demanderAuJoueurDeJoueurTest2foisDeSuite
*/

}