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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MoteurWebControlleurTest {

    Identification id;
    EtatDuJeu etat;

    @SpyBean
    MoteurWebControlleur webControlleur;

    @Autowired
    Moteur moteur;

    Moteur mSpy;

    @BeforeEach
    void setUp()throws Exception {
        id = new Identification("Joueur pour integration", "http://127.0.0.1:8081");
        etat = new EtatDuJeu();

        mSpy = Mockito.spy(moteur);
        ReflectionTestUtils.setField(webControlleur, "moteur", mSpy);
    }

    @Test
    void demanderAuJoueurDeJoueurTest() {
        webControlleur.getValue(id);

        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        verify(mSpy, times(1)).lancerPartie();
    }
}