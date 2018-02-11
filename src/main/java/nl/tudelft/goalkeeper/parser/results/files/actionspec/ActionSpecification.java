package nl.tudelft.goalkeeper.parser.results.files.actionspec;

import lombok.Getter;
import nl.tudelft.goalkeeper.parser.results.parts.Expression;

/**
 * Class which holds data on action specifications.
 */
public final class ActionSpecification {

    @Getter private String name; //NOPMD
    @Getter private Expression pre, post; //NOPMD

    /**
     * Creates a new instance of the ActionSpecification class.
     * @param name Name of the action specification.
     * @param pre Pre-condition of the action.
     * @param post Post-condition of the action.
     */
    public ActionSpecification(String name, Expression pre, Expression post) {
        this.name = name;
        this.pre = pre;
        this.post = post;
    }
}
