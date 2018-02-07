package nl.tudelft.goalkeeper.parser.results.parts;

import nl.tudelft.goalkeeper.checking.violations.source.Source;

/**
 * Interface for classes that have source locations.
 */
public interface Sourceable {
    /**
     * Gets the source of the object.
     * @return The source of the object.
     */
    Source getSource();

    /**
     * Sets the source of the object.
     * @param source Source of the object.
     */
    void setSource(Source source);
}
