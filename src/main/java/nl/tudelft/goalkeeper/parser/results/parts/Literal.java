package nl.tudelft.goalkeeper.parser.results.parts;

import lombok.Getter;

/**
 * Class containing literals.
 */
public class Literal {
    @Getter private String signature;
    @Getter private Variable[] variables;

    /**
     * Creates a new literal instance.
     * @param signature Signature of the literal.
     * @param variables Variables of the literal.
     */
    public Literal(String signature, Variable[] variables) {
        this.signature = signature;
        this.variables = variables;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Literal) {
            Literal that = (Literal) o;
            if (!this.signature.equals(that.signature)) {
                return false;
            }
            if (this.variables.length != that.variables.length) {
                return false;
            }
            for (int i = 0; i < variables.length; ++i) {
                if (!this.variables[i].equals(that.variables[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int res = signature.hashCode();
        for (int i = 0; i < variables.length; ++i) {
            res += variables[i].hashCode() ^ (i + 1);
        }
        return res;
    }
}
