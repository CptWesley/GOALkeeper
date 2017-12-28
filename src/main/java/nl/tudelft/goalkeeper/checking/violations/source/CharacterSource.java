package nl.tudelft.goalkeeper.checking.violations.source;

import lombok.Getter;

/**
 * Class representing a source in a character in a line.
 */
public final class CharacterSource implements Source {
    private String fileName;
    @Getter private int line;
    @Getter private int position;

    /**
     * Creates a new instance of CharacterSource.
     * @param fileName File to link to.
     * @param line Line number of the source.
     * @param position Character position of the source.
     */
    public CharacterSource(String fileName, int line, int position) {
        this.fileName = fileName;
        this.line = line;
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFile() {
        return fileName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("in '%s' at line %d at position %d", fileName, line, position);
    }
}
