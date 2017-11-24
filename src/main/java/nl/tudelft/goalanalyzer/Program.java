package nl.tudelft.goalanalyzer;

import nl.tudelft.goalanalyzer.util.Configuration;
import nl.tudelft.goalanalyzer.util.console.Console;
import nl.tudelft.goalanalyzer.util.console.ConsoleColor;

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
        if (Configuration.getInstance().hasParameter("color")) {
            Console.setUseColor(
                    Configuration.getInstance().getParameter("color").getAsBoolean());
        }
        Console.println("Performing goal-analyzer...");
        validateConfiguration();
    }

    /**
     * Validates configuration.
     */
    private static void validateConfiguration() {
        if (!Configuration.getInstance().hasParameter("rules")) {
            Console.println("[ERROR] No '-rules=...' parameter found.", ConsoleColor.RED);
            System.exit(ExitCode.NO_RULES);
        }
        if (!Configuration.getInstance().hasParameter("mas")) {
            Console.println("[ERROR] No '-mas=...' parameter found.", ConsoleColor.RED);
            System.exit(ExitCode.NO_MAS);
        }
    }
}
