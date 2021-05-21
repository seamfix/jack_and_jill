import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PlayStoreManagerTest {

    @Test
    public void isValidPackageName(){
        String nameA = "";
        String nameB = "1.2.3";
        String nameC = null;

        boolean resultA = PlayStoreManager.isValidPackageName(nameA);
        boolean resultB = PlayStoreManager.isValidPackageName(nameB);
        boolean resultC = PlayStoreManager.isValidPackageName(nameC);

        assertEquals(resultA, false);
        assertEquals(resultB, true);
        assertEquals(resultC, false);
    }
}