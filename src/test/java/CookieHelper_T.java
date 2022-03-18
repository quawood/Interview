import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Set;
import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.*;

public class CookieHelper_T extends CookieHelper {

    @Test
    public void findsNoActiveCookies1() throws FileNotFoundException, ParseException {
        Set<String> cookies = getMostActiveCookie(
                "src/test/resources/logExample1.log",
                "1970-01-01");
        assertEquals(cookies, emptySet());
    }

    @Test
    public void findsNoActiveCookies2() throws FileNotFoundException, ParseException {
        Set<String> cookies = getMostActiveCookie(
                "src/test/resources/logExample2.log",
                "1970-01-01");
        assertEquals(cookies, emptySet());
    }

    @Test
    public void findsActiveCookies1() throws FileNotFoundException, ParseException {
        Set<String> cookies = getMostActiveCookie(
                "src/test/resources/logExample1.log",
                "2018-12-08");
        Set<String> expectedCookies =  Set.of(
                "SAZuXPGUrfbcn5UA",
                "4sMM2LxV07bPJzwf",
                "fbcn5UAVanZf6UtG");
        assertEquals(expectedCookies, cookies);
    }

    @Test
    public void findsActiveCookies2() throws FileNotFoundException, ParseException {
        Set<String> cookies = getMostActiveCookie(
                "src/test/resources/logExample1.log",
                "2018-12-09");
        Set<String> expectedCookies =  Set.of("AtY0laUfhglK3lC7");
        assertEquals(expectedCookies, cookies);
    }

    @Test
    public void throwsFileNotFound() {
        assertThrows(
                FileNotFoundException.class,
                () -> CookieHelper.getMostActiveCookie("not_a_file.log", "1970-01-01"),
                "Excepted a file not expected error to throw");
    }
}
