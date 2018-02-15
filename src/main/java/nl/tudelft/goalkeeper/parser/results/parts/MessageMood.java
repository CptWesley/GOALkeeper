package nl.tudelft.goalkeeper.parser.results.parts;

import lombok.Getter;

/**
 * Message mood enum.
 */
public enum MessageMood {
    INDICATIVE(':'),
    IMPERATIVE('!'),
    INTERROGATIVE('?');

    @Getter private char symbol;

    /**
     * Creates a new MessageMood enum.
     * @param symbol Symbol of the mood.
     */
    MessageMood(char symbol) {
        this.symbol = symbol;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return Character.toString(symbol);
    }
}
