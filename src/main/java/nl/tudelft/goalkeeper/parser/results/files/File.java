package nl.tudelft.goalkeeper.parser.results.files;

import lombok.Getter;
import lombok.Setter;

/**
 * Abstract class for file types.
 */
public abstract class File {
    @Getter @Setter private String[] content;
}
