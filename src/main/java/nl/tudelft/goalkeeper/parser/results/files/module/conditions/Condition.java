package nl.tudelft.goalkeeper.parser.results.files.module.conditions;

import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.checking.violations.source.Source;
import nl.tudelft.goalkeeper.parser.results.parts.Expression;
import nl.tudelft.goalkeeper.parser.results.parts.Function;
import nl.tudelft.goalkeeper.parser.results.parts.Parameter;
import nl.tudelft.goalkeeper.parser.results.parts.Sourceable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract class for conditions.
 */
public abstract class Condition implements Sourceable {

    @Getter private Expression expression;
    @Getter @Setter private Source source;

    /**
     * Gets the hash code modifier.
     * @return Hashcode modifier.
     */
    protected abstract int getHashModifier();

    /**
     * Gets the type string of the condition type.
     * @return The type string of the condition type.
     */
    protected abstract String getTypeName();

    public List<Parameter> getExpressionVariable() {
        return getExpressionParameterRecusion(Collections.singletonList(expression));
    }

    private static List<Parameter> getExpressionParameterRecusion(List<Expression> expression) {
        if (expression == null || expression.size() == 0) {
            return null;
        }

        List<Parameter> retList = new ArrayList<>();

        expression.forEach(exp -> {
            if (exp instanceof Parameter) {
                retList.add((Parameter) exp);
            } else if (exp instanceof Function) {
               List<Parameter> temp =
                       getExpressionParameterRecusion(((Function)exp).getArguments());
               if (!temp.contains(null)) {
                   retList.addAll(temp);
               }
            }
        });
        return retList;
    }

    /**
     * Creates a new Condition instance.
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


    public static boolean equalConditions(final List<Condition> o1, final List<Condition> o2) {
        if (o1.size() != o2.size()) {
            return false;
        }

        o1.sort(new ConditionComparator());

        o2.sort(new ConditionComparator());

        return o1.equals(o2);
    }
}
