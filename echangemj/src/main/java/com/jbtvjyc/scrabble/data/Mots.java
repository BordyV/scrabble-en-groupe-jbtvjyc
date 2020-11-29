package com.jbtvjyc.scrabble.data;

import org.yaml.snakeyaml.util.ArrayUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Hashtable;


public class Mots {

    private String fileName;
    private Hashtable<Character,Integer> associationAlphabetiqueLigne;
    private ArrayList<String> listeDeMots;


    public Mots(){
        this.fileName = "mots_francais_pur_utf-8.txt";
        this.listeDeMots = new ArrayList<String>();
        this.associationAlphabetiqueLigne = new Hashtable<Character,Integer>();
        generationMots();
    }

    //Remplis le tableau qui associe les mots commencant par une lettre a un ligne (exemple: mots commencant par "e" a partir de la ligne 3541)
    public void generationMots() {
        // Le - à la fin permet d'éviter le out of bound dans le while juste en bas (aucun mot ne contient de -, donc il ne sera pas utilisé
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz-".toCharArray();
        String line;
        int i = 0;
        int numeroLigne = 0;
        ClassLoader classLoader = getClass().getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(this.fileName);
             InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            while ((line = reader.readLine()) != null) {
                this.listeDeMots.add(line);
                if(Character.toLowerCase(line.charAt(0)) == alphabet[i]){
                    this.associationAlphabetiqueLigne.put(alphabet[i], numeroLigne);
                    i++;
                }
                numeroLigne++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean verificationExistanceMot(String leMot){
        char lettreDebut = Character.toLowerCase(leMot.charAt(0));
        int indexDebutRecherche = this.associationAlphabetiqueLigne.get(lettreDebut);
        int indexFinRecherche;
        if(lettreDebut == 'z'){
            indexFinRecherche = this.listeDeMots.size()-1;
        }else{
            indexFinRecherche = this.associationAlphabetiqueLigne.get(lettreDebut+1);
        }
        for (int i = indexDebutRecherche; i < indexFinRecherche; i++){
            if(leMot.toLowerCase().equals(this.listeDeMots.get(i))){
                return true;
            }
        }

        return false;
    }

    public String getFileName() {
        return fileName;
    }

    public Hashtable<Character, Integer> getAssociationAlphabetiqueLigne() {
        return associationAlphabetiqueLigne;
    }

    public ArrayList<String> getListeDeMots() {
        return listeDeMots;
    }
}