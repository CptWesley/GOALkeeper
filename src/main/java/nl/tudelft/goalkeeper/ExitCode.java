package nl.tudelft.goalkeeper;

/**
 * Class containing exit codes.
 */
public final class ExitCode {

    public static final int SUCCESSFUL = 0;
    public static final int NO_RULES = 1;
    public static final int NO_MAS = 2;
    public static final int ERROR_FOUND = 3;

    /**
     * Prevents instantiation.
     */
    private ExitCode() { }
}
