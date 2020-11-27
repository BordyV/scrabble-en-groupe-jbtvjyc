import org.junit.jupiter.api.Test;
import com.jbtvjyc.scrabble.data.Identification;

public class IdentificationTest {

    @Test
    void testDefaultValueForNom() {
        Identification identification = new Identification();
        Assertions.asserEquals(expected: "nom par défaut", identification.getNom());
    }

    @Test
    void testDefaultValueForUrl() {
        Identification identification = new Identification();
        Assertions.asserEquals(expected: "http://localhost:0081/", identification.getUrl());
    }

    @Test
    void testValueForNom() {
        Identification identification = new Identification("bobidou","https//testurl");
        Assertions.asserEquals(expected: "bobidou", identification.getNom());
    }

    @Test
    void testValueForUrl() {
        Identification identification = new Identification();
        Assertions.asserEquals(expected: "https//testurl", identification.getUrl());
    }

    @Test
    void testValueSetValueForNom() {
        Identification identification = new Identification();
        identification.setNom("patrick");
        Assertions.asserEquals(expected: "patrick", identification.getNom());
    }

    @Test
    void testValueSetValueForUrl() {
        Identification identification = new Identification();
        identification.setNom("https//testurl2");
        Assertions.asserEquals(expected: "https//testurl2", identification.getUrl());
    }

    @Test
    void testToString() {
        Identification identification = new Identification("sonic","https//testurl3");
        Assertions.asserEquals(expected: identification.getNom() + "[" + identification.getUrl() + "]", identification.toString())
    }
}