package nl.tudelft.goalkeeper.parser;

import languageTools.errors.Message;
import nl.tudelft.goalkeeper.checking.violations.Violation;

/**
 * Class that parses messages to violations.
 */
final class MessageParser {

    private static final int SYNTAX_SEVERITY = 1;

    /**
     * Prevents instantiation.
     */
    private MessageParser() { }

    /**
     * Convert error message to a violation.
     * @param error Error message to parse.
     * @return Violation.
     */
    static Violation parse(Message error) {
        return new Violation(error.toShortString(), SYNTAX_SEVERITY)
                .setFile(error.getSource().getSource())
                .setStartingLine(error.getSource().getLineNumber())
                .setEndingLine(error.getSource().getLineNumber())
                .setError(true);
    }
}
