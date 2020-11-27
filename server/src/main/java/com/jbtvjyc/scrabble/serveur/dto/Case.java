package com.jbtvjyc.scrabble.serveur.dto;

public class Case {
    private char valeur;
    private Bonus bonus;

    public Case() {
        this.valeur = Character.MIN_VALUE;
        this.bonus = Bonus.vide;
    }

    public Case(Bonus bonus) {
        this.valeur = Character.MIN_VALUE;
        this.bonus = bonus;
    }

    public Case( char valeur, Bonus bonus) {
        this.valeur = valeur;
        this.bonus = bonus;
    }

    public Bonus getBonus() {
        return bonus;
    }

    public char getValeur() {
        return valeur;
    }

    public void setValeur(char valeur) {
        this.valeur = valeur;
    }
}

