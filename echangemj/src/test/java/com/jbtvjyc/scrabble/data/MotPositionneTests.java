import org.junit.jupiter.api.Test;
import com.jbtvjyc.scrabble.data.MotPositionne;

public class MotPositionneTests {

    @Test
    void testSetGetMot() {
        MotPositionne motPositionne= new MotPositionne();
        motPositionne.setMot("cheval");
        Assertions.asserEquals(expected: "cheval", motPositionne.GetMot());
    }

    @Test
    void testSetGetAbscisse() {
        MotPositionne motPositionne= new MotPositionne();
        motPositionne.setAbscisse(5);
        Assertions.asserEquals(expected: 5, motPositionne.getAbscisse());
    }

    @Test
    void testSetGetOrdonnee() {
        MotPositionne motPositionne= new MotPositionne();
        motPositionne.setOrdonnee(4);
        Assertions.asserEquals(expected: 4, motPositionne.getOrdonnee());
    }

    @Test
    void testSetGetHorizontal() {
        MotPositionne motPositionne= new MotPositionne();
        motPositionne.setHorizontal(false);
        Assertions.asserEquals(expected: false, motPositionne.GetHorizontal());
    }

    @Test
    void testToString() {
        MotPositionne motPositionne= new MotPositionne();
        String dir = "horizontal";

        if (!motPositionne.getHorizontal()) {
            dir = "vertical";
        }
        Assertions.asserEquals(expected: "(" + motPositionne.getMot() + "," + motPositionne.getAbscisse() + "," + motPositionne.getOrdonnee() + "," + dir + ")", MotPositionne.toString());
    }
}