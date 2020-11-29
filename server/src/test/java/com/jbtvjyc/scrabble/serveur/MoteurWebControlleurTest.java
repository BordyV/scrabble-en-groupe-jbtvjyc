import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockto.times;
import static org.mockito.Mockto.verify;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MoteurWebControlleurTest {

    Identification id;
    EtatDuJeu etat;

    @SpyBean
    MoteurWebControlleur webControlleur;

    @Autowired
    Moteur moteur;

    Moteur mSpy;

    @BeforeEach
    void setUp()throws Exception {
        id = new Identification("Joueur pour integration", "http://127.0.0.1:8081");
        etat = new EtatDuJeu();

        mSpy = Mockito.spy(moteur);
        ReflectionTestUtils.setField(webControlleur, "moteur", mSpy);
    }

    @Test
    void demanderAuJoueurDeJoueurTest() {
        webControlleur.getValue(id);

        try {
            TimeUnit.MILISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        verify(mSpy, times(1)).lancerPartie();
    }
}