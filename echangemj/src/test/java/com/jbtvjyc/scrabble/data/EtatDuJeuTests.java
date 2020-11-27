import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EtatDuJeuTest {

    @Test
    void testGetListeDeMots() {
        EtatDuJeu etatDuJeu = new EtatDuJeu();
        ArrayList<MotPositionne> listeDeMotsTest = new ArrayList<>();
        ArrayList<Character> chariotTest = new ArrayList<>();
        listeDeMotsTest.add(new MotPositionne("chameaux"));
        listeDeMotsTest.add(new MotPositionne("vache"));
        listeDeMotsTest.add(new MotPositionne("moto"));
        etatDuJeu.setListeDeMots(listeDeMotsTest);
        Assertions.asserEquals(expected: listeDeMotsTest, etatDuJeu.getListeDeMots());
    }

    @Test
    void testGetListeDeMots() {
        EtatDuJeu etatDuJeu = new EtatDuJeu();
        ArrayList<MotPositionne> listeDeMotsTest = new ArrayList<>();
        ArrayList<Character> chariotTest = new ArrayList<>();
        chariotTest.add(new Character('a'));
        chariotTest.add(new Character('b'));
        chariotTest.add(new Character('c'));
        etatDuJeu.setChariot(chariotTest);
        Assertions.asserEquals(expected: chariotTest, etatDuJeu.getChariot());
    }

    @Test
    void testAjouterLettres() {
        EtatDuJeu etatDuJeu = new EtatDuJeu();
        ArrayList<Character> chariotTest = new ArrayList<>();
        chariotTest.add(new Character('a'));
        chariotTest.add(new Character('b'));
        chariotTest.add(new Character('c'));
        ch1 = new Character('a');
        ch2 = new Character('b');
        ch3 = new Character('c');
        ajouterLettres(ch1, ch2, ch3);
        Assertions.asserEquals(expected: chariotTest, etatDuJeu.getChariot());
    }

    @Test
    void testAddMotPlace() {
        EtatDuJeu etatDuJeu = new EtatDuJeu();
        MotPositionne motPositionne= new MotPositionne();
        motPositionne.setMot("cheval");
        ArrayList<MotPositionne> listeDeMotsTest = new ArrayList<>();
        listeDeMotsTest.add(motPositionne);
        etatDuJeu.addMotPlace(motPositionne);
        Assertions.asserEquals(expected: listeDeMotsTest, etatDuJeu.getListeDeMots());
    }
}