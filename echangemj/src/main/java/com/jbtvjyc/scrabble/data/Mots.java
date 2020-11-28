package com.jbtvjyc.scrabble.data;

import org.yaml.snakeyaml.util.ArrayUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;


public class Mots {

    private String fileName;
    private Hashtable<Character,Integer> associationAlphabetiqueLigne;


    public Mots(){
        this.fileName = "mots_francais.txt";
        this.associationAlphabetiqueLigne = new Hashtable<Character,Integer>();
        associationAlphabetiqueLigne();
    }

    //Remplis le tableau qui associe les mots commencant par une lettre a un ligne (exemple: mots commencant par "e" a partir de la ligne 3541)
    public void associationAlphabetiqueLigne() {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        String line;
        int i = 0;
        int numeroLigne = 0;
        ClassLoader classLoader = getClass().getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(this.fileName);
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader)) {

            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
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
        return false;
    }

    public String getFileName() {
        return fileName;
    }
}