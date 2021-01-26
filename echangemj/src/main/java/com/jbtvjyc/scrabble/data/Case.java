package com.jbtvjyc.scrabble.data;

public class Case {
    private char valeur;
    private Bonus bonus;
    private boolean bonusActif;
    private boolean casePasTrouve;
    private boolean caseCommune;

    public Case() {
        this.valeur = Character.MIN_VALUE;
        this.bonus = bonus.VIDE;
        this.bonusActif = true;
        this.casePasTrouve = true;
        this.caseCommune = false;
    }

    public Case(Bonus bonus) {
        this.valeur = Character.MIN_VALUE;
        this.bonus = bonus;
        this.bonusActif = true;
        this.casePasTrouve = true;
        this.caseCommune = false;
    }

    public Case(char valeur, Bonus bonus) {
        this.valeur = valeur;
        this.bonus = bonus;
        this.bonusActif = true;
        this.casePasTrouve = true;
        this.caseCommune = false;
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

    public boolean getBonusActif(){
        return this.bonusActif;
    }

    public void setBonusActif(boolean actif){
        this.bonusActif = actif;
    }

    public void setBonusPlusActif(){
        this.bonusActif = false;
    }

    public boolean getCasePasTrouve(){
        return this.casePasTrouve;
    }

    public void setCaseTrouve() {
        this.casePasTrouve = false;
    }

    public boolean getCaseCommune(){
        return this.caseCommune;
    }

    public void setCaseCommune(boolean val) {
        this.caseCommune=val;
    }
}

