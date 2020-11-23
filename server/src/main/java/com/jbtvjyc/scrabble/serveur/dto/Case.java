package com.jbtvjyc.scrabble.serveur.dto;

public class Case {
    private int x;
    private int y;
    private String valeur;
    private Bonus bonus;

    public Case(int x, int y, String valeur, Bonus bonus) {
        this.x = x;
        this.y = y;
        this.valeur = valeur;
        this.bonus = bonus;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Bonus getBonus() {
        return bonus;
    }

    public String getValeur() {
        return valeur;
    }
}

