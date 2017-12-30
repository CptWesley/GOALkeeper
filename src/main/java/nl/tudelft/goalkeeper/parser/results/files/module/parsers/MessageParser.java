package nl.tudelft.goalkeeper.parser.results.files.module.parsers;

import languageTools.errors.Message;
import nl.tudelft.goalkeeper.checking.violations.Violation;
import nl.tudelft.goalkeeper.checking.violations.source.CharacterSource;

/**
 * Class that parses messages to violations.
 */
public final class MessageParser {

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
    public static Violation parse(Message error) {
        return new Violation(error.toShortString(), SYNTAX_SEVERITY)
                .setSource(new CharacterSource(
                        error.getSource().getSource(),
                        error.getSource().getLineNumber(),
                        error.getSource().getStartIndex()))
                .setError(true);
    }
}