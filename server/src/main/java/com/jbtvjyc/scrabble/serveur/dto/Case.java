package com.jbtvjyc.scrabble.serveur.dto;

public class Case {
    private String valeur;
    private Bonus bonus;

    public Case( String valeur, Bonus bonus) {
        this.valeur = valeur;
        this.bonus = bonus;
    }

    public Bonus getBonus() {
        return bonus;
    }

    public String getValeur() {
        return valeur;
    }
}

