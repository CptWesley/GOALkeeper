package nl.tudelft.goalanalyzer;

import nl.tudelft.goalanalyzer.util.Configuration;

/**
 * Main class of the program.
 */
public final class Program {

    /**
     * Prevent instantiation.
     */
    private Program() { }

    /**
     * Entry point of the program.
     * @param args Launch arguments.
     */
    public static void main(String[] args) {
        Configuration.load(args);
        // TODO: Add logic.
    }
}
