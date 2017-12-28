package nl.tudelft.goalkeeper.checking.violations.source;

import lombok.Getter;

/**
 * Class representing a source in a line.
 */
public final class LineSource implements Source {
    private String fileName;
    @Getter private int line;

    /**
     * Creates a new instance of LineSource.
     * @param fileName File to link to.
     * @param line Line number of the source.
     */
    public LineSource(String fileName, int line) {
        this.fileName = fileName;
        this.line = line;
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
        return String.format("in '%s' at line %d", fileName, line);
    }
}
