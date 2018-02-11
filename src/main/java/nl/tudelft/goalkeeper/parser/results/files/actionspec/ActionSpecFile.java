package nl.tudelft.goalkeeper.parser.results.files.actionspec;

import nl.tudelft.goalkeeper.parser.results.files.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class representing action spec files.
 */
public final class ActionSpecFile extends File {

    private List<ActionSpecification> specifications;

    /**
     * Instantiates a new instance of the ActionSpecFile class.
     * @param fileName File path of the action spec file.
     * @throws IOException Thrown when file could not be read.
     */
    public ActionSpecFile(String fileName) throws IOException {
        super(fileName);
        specifications = new ArrayList<>();
    }

    /**
     * Gets a list of all action specifications in this actionspec.
     * @return List of all action specifications in this actionspec.
     */
    public List<ActionSpecification> getActionSpecifications() {
        return Collections.unmodifiableList(specifications);
    }

    /**
     * Adds an action specification to the actionspec.
     * @param specification Action specification which is to be added.
     */
    public void addActionSpecification(ActionSpecification specification) {
        specifications.add(specification);
    }
}
