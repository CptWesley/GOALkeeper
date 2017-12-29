package nl.tudelft.goalkeeper.checking.violations.source;

/**
 * Interface for violation sources.
 */
public interface Source {
    @Override
    String toString();

    /**
     * Gets the file of the source.
     * @return File path to the source.
     */
    String getFile();
}
