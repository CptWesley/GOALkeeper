package nl.tudelft.goalkeeper.checking.violations.source;

/**
 * Class containing source info of files.
 */
public final class FileSource implements Source {

    private String fileName;

    /**
     * Creates a new instance of FileSource.
     * @param fileName File to link to.
     */
    public FileSource(String fileName) {
        this.fileName = fileName;
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
        return String.format("in '%s'", fileName);
    }
}
