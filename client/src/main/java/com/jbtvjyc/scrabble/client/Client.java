package com.jbtvjyc.scrabble.client;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public class Client {
    @Component
    @Scope("singleton")
    public class Client {

        public MotPositionne jouer(EtatDuJeu etatDuJeu) {
            System.out.println("Joueur > je joue mot au milieu");
            return new MotPositionne("mot", 7, 7) ;
        }
    }

}