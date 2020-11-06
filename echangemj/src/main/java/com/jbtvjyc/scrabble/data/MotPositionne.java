package com.jbtvjyc.scrabble.data;

public class MotPositionne {
    private String mot;
    private int abscisse;
    private int ordonnee;
    private boolean horizontal;

    public MotPositionne() {
        this("mot", 0, 0, true);
    }

    public MotPositionne(String mot, int x, int y) {
        this(mot, x, y, true);
    }

    public MotPositionne(String mot, int x, int y, boolean horizontal) {
        setMot(mot);
        setAbscisse(x);
        setOrdonnee(y);
        setHorizontal(horizontal);
    }

    public void setMot(String mot) {
        this.mot = mot;
    }

    public String getMot() {
        return this.mot;
    }

    public void setAbscisse(int abscisse) {
        this.abscisse = abscisse;
    }

    public int getAbscisse() {
        return this.abscisse;
    }

    public void setOrdonnee(int ordonnee) {
        this.ordonnee = ordonnee;
    }

    public int getOrdonnee() {
        return this.ordonnee;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public boolean getHorizontal() {
        return this.horizontal;
    }

    public String toString() {
        String dir = "horizontal";

        if (!this.getHorizontal()) {
            dir = "vertical";
        }

        return "(" + this.getMot() + "," + this.getAbscisse() + "," + this.getOrdonnee() + "," + dir + ")";
    }
}
