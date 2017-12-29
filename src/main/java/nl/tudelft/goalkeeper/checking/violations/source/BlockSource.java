package nl.tudelft.goalkeeper.checking.violations.source;

import lombok.Getter;

/**
 * Class representing a source of multiple lines.
 */
public final class BlockSource implements Source {
    private String fileName;
    @Getter private int startingLine;
    @Getter private int endingLine;

    /**
     * Creates a new instance of LineSource.
     * @param fileName File to link to.
     * @param startingLine Starting line number of the source.
     * @param endingLine Ending line number of the source.
     */
    public BlockSource(String fileName, int startingLine, int endingLine) {
        this.fileName = fileName;
        this.startingLine = startingLine;
        this.endingLine = endingLine;
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
        return String.format("in '%s' at lines %d-%d", fileName, startingLine, endingLine);
    }
}
