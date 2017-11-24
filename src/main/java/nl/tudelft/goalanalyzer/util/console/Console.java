package nl.tudelft.goalanalyzer.util.console;

/**
 * Class that handles console printing for different operating systems.
 */
public final class Console {
    /**
     * Prevents instantiation.
     */
    private Console() { }

    /**
     * Prints the current string.
     * @param text String to print.
     */
    public static void print(String text) {
        print(text, ConsoleColor.DEFAULT);
    }

    /**
     * Prints the current string.
     * @param text String to print.
     * @param color Color to print with.
     */
    public static void print(String text, ConsoleColor color) {
        if (System.getProperty("os.name").startsWith("Windows")) {
            System.out.print(text);
        } else {
            System.out.print(color.getAnsi() + text);
        }
    }

    /**
     * Prints an empty line.
     */
    public static void println() {
        System.out.println();
    }

    /**
     * Prints text and appends a new line.
     * @param text Text to print.
     */
    public static void println(String text) {
        println(text, ConsoleColor.DEFAULT);
    }

    /**
     * Prints text and appends a new line.
     * @param text Text to print.
     * @param color Color to print with.
     */
    public static void println(String text, ConsoleColor color) {
        print(color.getAnsi() + text);
        println();
    }
}
