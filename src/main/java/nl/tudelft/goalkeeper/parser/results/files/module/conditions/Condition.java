package nl.tudelft.goalkeeper.parser.results.files.module.conditions;

import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.checking.violations.source.Source;
import nl.tudelft.goalkeeper.parser.results.parts.Compound;
import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import nl.tudelft.goalkeeper.parser.results.parts.Parameter;
import nl.tudelft.goalkeeper.parser.results.parts.KRLanguage;
import nl.tudelft.goalkeeper.parser.results.parts.Linguistic;
import nl.tudelft.goalkeeper.parser.results.parts.Sourceable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract class for conditions.
 */
public abstract class Condition implements Sourceable, Linguistic {

    @Getter private Expression expression;
    @Getter @Setter private Source source;
    @Getter @Setter private KRLanguage kRLanguage = KRLanguage.UNKNOWN;


    /**
     * Gets the hash code modifier.
     *
     * @return Hashcode modifier.
     */
    protected abstract int getHashModifier();

    /**
     * Gets the type string of the condition type.
     *
     * @return The type string of the condition type.
     */
    protected abstract String getTypeName();

    /**
     * Gets the variable from a condition which are used in the condition.
     * @return A list of parameters. If there are no parameters, the list will start with NULL
     */
    public List<Parameter> getExpressionVariable() {
        return getExpressionParameterRecursion(Collections.singletonList(expression));
    }

    /**
     * A recursive method to retrieve the list of parameters which are used by a Condition.
     * @param expression A list of expressions to extract the parameters from.
     * @return A list of parameters which are in the expression.
     */
    private static List<Parameter> getExpressionParameterRecursion(List<Expression> expression) {
        if (expression == null || expression.size() == 0) {
            return null;
        }

        List<Parameter> retList = new ArrayList<>();

        expression.forEach(exp -> {
            if (exp instanceof Parameter) {
                retList.add((Parameter) exp);
            } else if (exp instanceof Compound) {
                List<Parameter> temp =
                        getExpressionParameterRecursion(((Compound) exp).getArguments());
                if (temp != null && !temp.contains(null)) {
                    retList.addAll(temp);
                }
            }
        });
        return retList;
    }

    /**
     * Creates a new Condition instance.
     *
     * @param expression Expression of the condition.
     */
    protected Condition(Expression expression) {
        this.expression = expression;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o != null && this.getClass().equals(o.getClass())) {
            Condition that = (Condition) o;
            return this.expression.equals(that.expression);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return expression.hashCode() ^ getHashModifier();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%s(%s)", getTypeName(), expression);
    }
}
