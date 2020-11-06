package com.jbtvjyc.scrabble.client;

import com.jbtvjyc.scrabble.data.EtatDuJeu;
import com.jbtvjyc.scrabble.data.MotPositionne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class ClientWebController {

    @Autowired
    Client client;

    @PostMapping("/jouer")
    public MotPositionne jouer(@RequestBody EtatDuJeu etatDuJeu) {
        System.out.println("Je suis le client et on me demande de jouer !!");
        return client.jouer(etatDuJeu);
    }

    @PostMapping("/finir")
    public void finir() {
        // fin brutale (pour abréger sur travis), mais il faut répondre un peu après
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("fin du programme du joueur");
                try {
                    TimeUnit.MILLISECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.exit(0);
                }

            }
        });
        t.start();
    }
}
