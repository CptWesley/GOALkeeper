package nl.tudelft.goalkeeper.parser.results.parts;

import lombok.Getter;
import lombok.Setter;
import nl.tudelft.goalkeeper.checking.violations.source.Source;
import nl.tudelft.goalkeeper.parser.queries.KRLanguage;

/**
 * Abstract class for expressions.
 */
public abstract class Expression implements Sourceable {

    /**
     * Gets the identifier of this variable.
     * @return Identifier of this variable.
     */
    public abstract String getIdentifier();

    @Getter @Setter private Source source = null;
    @Getter @Setter private KRLanguage kRLanguage = KRLanguage.UNKNOWN;
}
