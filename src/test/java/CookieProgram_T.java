import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CookieProgram_T extends CookieProgram {

    @Test
    public void correctCommand() throws Exception {
        run(new String[]
                {
                        "src/test/resources/logExample1.log",
                        "-d",
                        "1970-01-01"
                });
    }

    @Test
    public void fileNotFound() {
        assertThrows(
                FileNotFoundException.class,
                () -> run(new String[]
                        {
                                "not_a_file.log",
                                "-d",
                                "1970-01-01"
                        }),
                "Should throw file not found exception");
    }

    @Test
    public void fileArgMissing() {
        assertThrows(
                Exception.class,
                () -> run(new String[]
                {
                        "-d",
                        "1970-01-01"
                }),
                "Should throw a missing arg exception");
    }

    @Test
    public void dateArgMissing() {
        assertThrows(
                Exception.class,
                () -> run(new String[]
                        {
                                "src/test/resources/logExample1.log",
                                "-d"
                        }),
                "Should throw a missing date exception");

    }

    @Test
    public void incorrectDateFormat() {
        assertThrows(
                Exception.class,
                () -> run(new String[]
                        {
                                "src/test/resources/logExample1.log",
                                "-d",
                                "430"
                        }),
                "Should throw incorrect date format exception");
    }

    @Test
    public void dateOptionAndArgMissing() {
        assertThrows(
                Exception.class,
                () -> run(new String[]
                        {
                                "src/test/resources/logExample1.log"
                        }),
                "Should throw a missing date exception");

    }

    @Test
    public void dateOptionTagMissing() {
        assertThrows(
                Exception.class,
                () -> run(new String[]
                        {
                                "src/test/resources/logExample1.log",
                                "1970-01-01"
                        }),
                "Should throw missing option exception");
    }

}
