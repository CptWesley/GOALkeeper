package nl.tudelft.goalkeeper.parser.results.files.actionspec;

import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import nl.tudelft.goalkeeper.parser.results.parts.KRLanguage;
import nl.tudelft.goalkeeper.parser.results.parts.Linguistic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Class which holds data on action specifications.
 */
public final class ActionSpecification implements Linguistic {

    @Getter private String name; //NOPMD
    @Getter private Expression pre, post; //NOPMD
    private List<Expression> parameters;
    @Getter @Setter private KRLanguage kRLanguage;

    /**
     * Creates a new instance of the ActionSpecification class.
     * @param name Name of the action specification.
     * @param pre Pre-condition of the action.
     * @param post Post-condition of the action.
     * @param parameters Parameters of the action.
     */
    public ActionSpecification(String name, Expression pre,
                               Expression post, Collection<Expression> parameters) {
        this.name = name;
        this.pre = pre;
        this.post = post;
        this.parameters = new ArrayList<>();
        this.parameters.addAll(parameters);
        this.kRLanguage = findLanguage();
    }

    /**
     * Finds the KRLanguage of the action specification.
     * @return the KRLanguage of the action specification.
     */
    private KRLanguage findLanguage() {
        if (pre.getKRLanguage() != KRLanguage.UNKNOWN) {
            return pre.getKRLanguage();
        }
        if (post.getKRLanguage() != KRLanguage.UNKNOWN) {
            return post.getKRLanguage();
        }
        for (Expression parameter : parameters) {
            if (parameter.getKRLanguage() != KRLanguage.UNKNOWN) {
                return parameter.getKRLanguage();
            }
        }
        return KRLanguage.UNKNOWN;
    }

    /**
     * Gets a list of all parameters passed to the action.
     * @return List of all parameters passed to the action.
     */
    public List<Expression> getParameters() {
        return Collections.unmodifiableList(parameters);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder paramStringBuilder = new StringBuilder();
        if (parameters.size() > 0) {
            paramStringBuilder.append('(');
            for (int i = 0; i < parameters.size(); ++i) {
                paramStringBuilder.append(parameters.get(i));
                if (i < parameters.size() - 1) {
                    paramStringBuilder.append(", ");
                }
            }
            paramStringBuilder.append(')');
        }
        return String.format("define %s%s with pre {%s} post {%s}",
                name, paramStringBuilder.toString(), pre.toString(), post.toString());
    }
}
