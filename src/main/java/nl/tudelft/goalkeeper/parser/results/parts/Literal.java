package nl.tudelft.goalkeeper.parser.results.parts;

import lombok.Getter;

/**
 * Class containing literals.
 */
public class Literal {
    @Getter private String signature;
    @Getter private Parameter[] parameters;

    /**
     * Creates a new literal instance.
     * @param signature Signature of the literal.
     * @param parameters Variables of the literal.
     */
    public Literal(String signature, Parameter[] parameters) {
        this.signature = signature;
        this.parameters = parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Literal) {
            Literal that = (Literal) o;
            if (!this.signature.equals(that.signature)) {
                return false;
            }
            if (this.parameters.length != that.parameters.length) {
                return false;
            }
            for (int i = 0; i < parameters.length; ++i) {
                if (!this.parameters[i].equals(that.parameters[i])) {
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
        for (int i = 0; i < parameters.length; ++i) {
            res += parameters[i].hashCode() ^ (i + 1);
        }
        return res;
    }
}
