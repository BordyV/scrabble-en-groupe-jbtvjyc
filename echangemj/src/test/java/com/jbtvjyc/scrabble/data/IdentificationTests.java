package com.jbtvjyc.scrabble.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class IdentificationTests {

    @Test
    void testDefaultValueForNom() {
        Identification identification = new Identification();
        Assertions.assertEquals("nom par défaut", identification.getNom());
    }

    @Test
    void testDefaultValueForUrl() {
        Identification identification = new Identification();
        Assertions.assertEquals("http://localhost:0081/", identification.getUrl());
    }

    @Test
    void testValueForNom() {
        Identification identification = new Identification("bobidou","https//testurl");
        Assertions.assertEquals("bobidou", identification.getNom());
    }

    @Test
    void testValueForUrl() {
        Identification identification = new Identification("bobidou","https//testurl");
        Assertions.assertEquals("https//testurl", identification.getUrl());
    }

    @Test
    void testValueSetValueForNom() {
        Identification identification = new Identification();
        identification.setNom("patrick");
        Assertions.assertEquals("patrick", identification.getNom());
    }

    @Test
    void testValueSetValueForUrl() {
        Identification identification = new Identification();
        identification.setUrl("https//testurl2");
        Assertions.assertEquals("https//testurl2", identification.getUrl());
    }

    @Test
    void testToString() {
        Identification identification = new Identification("sonic","https//testurl3");
        Assertions.assertEquals(identification.getNom() + "[" + identification.getUrl() + "]", identification.toString());
    }
}