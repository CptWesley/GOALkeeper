package nl.tudelft.goalkeeper.util.console;

import lombok.Setter;

/**
 * Class that handles console printing for different operating systems.
 */
public final class Console {

    @Setter private static boolean useColor;

    static {
        useColor = !System.getProperty("os.name").startsWith("Windows");
    }

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
     * Prints the given object.
     * @param object Object to print.
     */
    public static void print(Object object) {
        print(String.valueOf(object));
    }

    /**
     * Prints the current string.
     * @param text String to print.
     * @param color Color to print with.
     */
    public static void print(String text, ConsoleColor color) {
        if (useColor) {
            System.out.print(color.getAnsi() + text);
        } else {
            System.out.print(text);
        }
    }

    /**
     * Prints the given object.
     * @param object Object to print.
     * @param color Color to print in.
     */
    public static void print(Object object, ConsoleColor color) {
        print(String.valueOf(object), color);
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
     * Prints the given object and appends a new line.
     * @param object Object to print.
     */
    public static void println(Object object) {
        println(String.valueOf(object));
    }

    /**
     * Prints text and appends a new line.
     * @param text Text to print.
     * @param color Color to print with.
     */
    public static void println(String text, ConsoleColor color) {
        print(text, color);
        println();
    }

    /**
     * Prints the given object and appends a new line.
     * @param object Object to print.
     * @param color Color to print in.
     */
    public static void println(Object object, ConsoleColor color) {
        println(String.valueOf(object), color);
    }

    /**
     * Checks if we are printing with color.
     * @return True if printing with color.
     */
    public static boolean usesColor() {
        return useColor;
    }
}
