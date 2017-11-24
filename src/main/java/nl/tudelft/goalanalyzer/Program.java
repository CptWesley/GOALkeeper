package nl.tudelft.goalanalyzer;

import nl.tudelft.goalanalyzer.checking.CheckerRunner;
import nl.tudelft.goalanalyzer.checking.violations.Violation;
import nl.tudelft.goalanalyzer.exceptions.MalformedRulesException;
import nl.tudelft.goalanalyzer.exceptions.WrongFileTypeException;
import nl.tudelft.goalanalyzer.rules.RuleSet;
import nl.tudelft.goalanalyzer.util.Configuration;
import nl.tudelft.goalanalyzer.util.MasIndexer;
import nl.tudelft.goalanalyzer.util.console.Console;
import nl.tudelft.goalanalyzer.util.console.ConsoleColor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

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

        Console.println("Acquiring files...");
        String[] fileSystem = getFileSystem();

        Console.println("Acquiring rules...");
        RuleSet rules = getRuleSet();

        Console.println("Analyzing...");
        CheckerRunner runner = new CheckerRunner();
        Collection<Violation> violations = runner.run(fileSystem, rules);
        Console.println("Found " + violations.size() + " violations.");
        int errors = 0;
        for (Violation violation : violations) {
            if (violation.isError()) {
                ++errors;
                Console.println(violation.toString(), ConsoleColor.RED);
            } else {
                Console.println(violation.toString(), ConsoleColor.YELLOW);
            }
        }

        if (errors > 0 && rules.failsOnError()) {
            Console.println("Build failed with "
                    + errors
                    + " errors and "
                    + (violations.size() - errors)
                    + " warnings.", ConsoleColor.RED);
            System.exit(ExitCode.ERROR_FOUND);
        }
        Console.println("Build succeeded with "
                + errors
                + " errors and "
                + (violations.size() - errors)
                + " warnings.", ConsoleColor.BLUE);
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

    /**
     * Gets the ruleset given by the parameters.
     * @return RuleSet of the parameters.
     */
    private static RuleSet getRuleSet() {
        String fileName = Configuration.getInstance().getParameter("rules").getAsString();
        try {
            return RuleSet.load(fileName);
        } catch (MalformedRulesException e) {
            Console.println("[ERROR] Malformed JSON '"
                    + fileName
                    + "'ruleset found.", ConsoleColor.RED);
        } catch (IOException e) {
            Console.println("[ERROR] An error occured while trying to open file '"
                    + fileName
                    + "'.", ConsoleColor.RED);
        }
        System.exit(ExitCode.NO_RULES);
        return null;
    }

    /**
     * Gets a file system from a mas2g file.
     * @return
     */
    private static String[] getFileSystem() {
        String fileName = Configuration.getInstance().getParameter("mas").getAsString();
        MasIndexer indexer = null;
        try {
            indexer = MasIndexer.create(fileName);
        } catch (WrongFileTypeException e) {
            Console.println("[ERROR] File '"
                    + fileName
                    + "' is not a '.mas2g' file.", ConsoleColor.RED);
            System.exit(ExitCode.NO_MAS);
        } catch (FileNotFoundException e) {
            Console.println("[ERROR] File '"
                    + fileName
                    + "' does not exist.", ConsoleColor.RED);
            System.exit(ExitCode.NO_MAS);
        }
        return indexer.getFileSystem();
    }
}
