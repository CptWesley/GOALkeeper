package nl.tudelft.goalkeeper.util.console;

import lombok.Getter;

/**
 * Enumeration of console colors.
 */
public enum ConsoleColor {
    EMPTY(""),
    DEFAULT("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m");

    @Getter private String ansi;

    /**
     * Initializes a new ConsoleColor enum.
     * @param ansi ANSI escape character set.
     */
    ConsoleColor(final String ansi) {
        this.ansi = ansi;
    }
}
