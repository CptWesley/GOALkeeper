package nl.tudelft.goalkeeper.parser.queries;

import krTools.language.Query;
import nl.tudelft.goalkeeper.exceptions.UnknownKRLanguageException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import swiprolog.language.PrologQuery;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * Test class for the ExpressionParser class.
 */
class ExpressionParserTest {

    /**
     * Checks that we can correctly get a prolog expression parser.
     */
    @Test
    void getParserPrologTest() throws UnknownKRLanguageException {
        assertThat(ExpressionParser
                .getParser(Mockito.mock(PrologQuery.class)))
                .isInstanceOf(PrologExpressionParser.class);
    }

    /**
     * Checks that we can correctly get a prolog expression parser.
     */
    @Test
    void getParserUnknownTest() {
        assertThatExceptionOfType(UnknownKRLanguageException.class)
                .isThrownBy(() -> ExpressionParser.getParser(Mockito.mock(Query.class)))
                .withMessageStartingWith("Found query of type '")
                .withMessageContaining("krTools.language.Query")
                .withMessageEndingWith("'.");
    }
}
