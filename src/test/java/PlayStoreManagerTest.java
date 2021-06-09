import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static util.Util.isValidPackageName;

public class PlayStoreManagerTest {

    @Test
    public void isValidPackageNameTest(){
        String nameA = "";
        String nameB = "1.2.3";
        String nameC = null;

        boolean resultA = isValidPackageName(nameA);
        boolean resultB = isValidPackageName(nameB);
        boolean resultC = isValidPackageName(nameC);

        assertEquals(resultA, false);
        assertEquals(resultB, true);
        assertEquals(resultC, false);
    }
}