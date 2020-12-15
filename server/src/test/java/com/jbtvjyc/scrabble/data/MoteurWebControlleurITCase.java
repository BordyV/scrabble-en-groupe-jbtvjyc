package com.jbtvjyc.scrabble.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import com.jbtvjyc.scrabble.data.EtatDuJeu;
import com.jbtvjyc.scrabble.data.Identification;
import com.jbtvjyc.scrabble.data.Moteur;
import com.jbtvjyc.scrabble.data.MoteurWebControlleur;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@WebMvcTest(MoteurWebControlleur.class)
public class MoteurWebControlleurITCase {

    // data
    Identification id;
    EtatDuJeu etat;

    @MockBean
    Moteur moteur;

    @MockBean
    MoteurWebControlleur webControlleur;

    @BeforeEach
    void setUp() throws Exception {
        id = new Identification("Joueur pour intégration", "http://127.0.0.1:8081/");
        etat = new EtatDuJeu();

        // ReflectionTestUtils.setField(webControlleur, "moteur", moteur);
    }

    @Test
    void demanderAuJoueurDeJouerTest() {
        // pour que le controlleur ait un joueur...
        // l'appel extérieur qui lance tout
        webControlleur.getValue(id);

        // some code are in a thread...
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        verify(moteur, times(1)).lancerPartie();
        verify(webControlleur, times(2)).demanderAuJoueurDeJouer(any());
        verify(webControlleur, times(1)).envoyerFin();


        EtatDuJeu plateau = moteur.getEtatDuJeu();
        assertEquals(2, plateau.getListeDeMots().size());

        verify(moteur, times(3)).getEtatDuJeu();
        // error to verify it is a good spy // verify(mSpy, times(16)).aMethod();

        // normalement, à la fin le client est éteint
        assertThrows(org.springframework.web.client.ResourceAccessException.class, () -> moteur.run());

        // etc.

    }



    // un test est possible avec le lancement d'une  docker. IL

    @Test
    void demanderAuJoueurDeJouerTest2foisDeSuite() {
        // pour que le controlleur ait un joueur...

        webControlleur.getValue(id);
        webControlleur.getValue(id);

        // some code are in a thread...
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // 2 fois lancerPartie, mais une seule avec effet... comme c'est un sout, on ne peut pas vérfier la 2e fois si ce n'est que le reste ne change pas
        verify(moteur, times(2)).lancerPartie();
        verify(webControlleur, times(2)).demanderAuJoueurDeJouer(any());
        verify(webControlleur, times(1)).envoyerFin();


        EtatDuJeu plateau = moteur.getEtatDuJeu();
        assertEquals(2, plateau.getListeDeMots().size());

        verify(moteur, times(3)).getEtatDuJeu();
        // error to verify it is a good spy // verify(mSpy, times(16)).aMethod();

        // normalement, à la fin le client est éteint
        assertThrows(org.springframework.web.client.ResourceAccessException.class, () -> moteur.run());

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