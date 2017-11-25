package nl.tudelft.goalanalyzer;

import nl.tudelft.goalanalyzer.util.Configuration;
import nl.tudelft.goalanalyzer.util.console.Console;
import nl.tudelft.goalanalyzer.util.console.ConsoleColor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.security.Permission;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Test class for the Program class.
 */
class ProgramTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private static final String NEWLINE = System.getProperty("line.separator");
    private static SecurityManager originalSecurityManager;

    /**
     * Sets up the security manager before the tests.
     */
    @BeforeAll
    static void setupBeforeAll() {
        originalSecurityManager = System.getSecurityManager();
        System.setSecurityManager(new NoSystemExitSecurityManager());
    }

    /**
     * Cleans up the security manager after the tests.
     */
    @AfterAll
    static void cleanupAfterAll() {
        System.setSecurityManager(originalSecurityManager);
    }

    /**
     * Prepares testing environment before each test.
     */
    @BeforeEach
    void setup() {
        Console.setUseColor(true);
        System.setOut(new PrintStream(out));
        Configuration.clear();
    }

    /**
     * Cleans the testing environment after each test.
     */
    @AfterEach
    void cleanup() {
        System.setOut(null);
    }

    /**
     * Checks that color overriding works.
     */
    @Test
    void colorsTest() {
        assertThat(Console.usesColor()).isTrue();
        assertThatThrownBy(() -> Program.main(new String[]{"-color=false"}))
                .isInstanceOf(SystemExitException.class);
        assertThat(Console.usesColor()).isFalse();
    }

    /**
     * Checks that rules validation works.
     */
    @Test
    void validateRulesTest() {
        assertThatThrownBy(() -> Program.main(new String[0]))
                .isInstanceOf(SystemExitException.class)
                .hasMessage(ExitCode.NO_RULES + "");
        assertThat(out.toString()).contains(ConsoleColor.RED.getAnsi()
                + "[ERROR] No '-rules=...' parameter found."
                + NEWLINE);
    }

    /**
     * Checks that mas validation works.
     */
    @Test
    void validateMasTest() {
        assertThatThrownBy(() -> Program.main(new String[]{"-rules"}))
                .isInstanceOf(SystemExitException.class)
                .hasMessage(ExitCode.NO_MAS + "");
        assertThat(out.toString()).contains(ConsoleColor.RED.getAnsi()
                + "[ERROR] No '-mas=...' parameter found."
                + NEWLINE);
    }

    /**
     * Checks that mas2g extension checking works.
     */
    @Test
    void getFileSystemNotMas2gTest() {
        assertThatThrownBy(() -> Program.main(new String[]{"-rules", "-mas"}))
                .isInstanceOf(SystemExitException.class)
                .hasMessage(ExitCode.NO_MAS + "");
        assertThat(out.toString()).contains(ConsoleColor.RED.getAnsi()
                + "[ERROR] File 'true' is not a '.mas2g' file."
                + NEWLINE);
    }

    /**
     * Checks that mas2g existence checking works.
     */
    @Test
    void getFileSystemMissingFileTest() {
        assertThatThrownBy(() -> Program.main(new String[]{"-rules", "-mas=blablabla.mas2g"}))
                .isInstanceOf(SystemExitException.class)
                .hasMessage(ExitCode.NO_MAS + "");
        assertThat(out.toString()).contains(ConsoleColor.RED.getAnsi()
                + "[ERROR] File 'blablabla.mas2g' does not exist."
                + NEWLINE);
    }

    /**
     * Checks that rule set existence checking works.
     */
    @Test
    void getRulesMissingTest() {
        assertThatThrownBy(() -> Program.main(new String[] {
                "-rules",
                "-mas=src/test/resources/testfiles/empty-project-files/mas.mas2g"
        })).isInstanceOf(SystemExitException.class)
                .hasMessage(ExitCode.NO_RULES + "");
        assertThat(out.toString()).contains(ConsoleColor.RED.getAnsi()
                + "[ERROR] An error occured while trying to open file 'true'."
                + NEWLINE);
    }

    /**
     * Checks that rule set malformation checking works.
     */
    @Test
    void getRulesMalformedTest() {
        assertThatThrownBy(() -> Program.main(new String[] {
                "-rules=src/test/resources/testfiles/empty-project-files/mas.mas2g",
                "-mas=src/test/resources/testfiles/empty-project-files/mas.mas2g"
        })).isInstanceOf(SystemExitException.class)
                .hasMessage(ExitCode.NO_RULES + "");
        assertThat(out.toString()).contains(ConsoleColor.RED.getAnsi()
                + "[ERROR] Malformed JSON 'src/test/resources/testfiles/"
                +  "empty-project-files/mas.mas2g' ruleset found."
                + NEWLINE);
    }

    /**
     * Checks that rule set malformation checking works.
     */
    @Test
    void successTest() {
        assertThatThrownBy(() -> Program.main(new String[] {
                "-rules=src/main/resources/rules.json",
                "-mas=src/test/resources/testfiles/empty-project-files/mas.mas2g"
        })).isInstanceOf(SystemExitException.class)
                .hasMessage(ExitCode.SUCCESSFUL + "");
        assertThat(out.toString()).contains(ConsoleColor.BLUE.getAnsi()
                + "Build succeeded with 0 errors and 0 warnings."
                + NEWLINE);
    }
}

/**
 * Provides an override for catching to throw an exception rather than exiting.
 */
class NoSystemExitSecurityManager extends SecurityManager {
    @Override
    public void checkExit(final int status) {
        throw new SystemExitException(status);
    }

    @Override
    public void checkPermission(final Permission perm) { }
}

/**
 * Provides an exception that can be thrown instead of exiting.
 */
class SystemExitException extends SecurityException {
    /**
     * Initializes a new instance of the SystemExitException class.
     * @param exitCode Exit code the System.Exit was called with.
     */
    SystemExitException(int exitCode) {
        super(exitCode + "");
    }
}
