package nl.tudelft.goalkeeper.parser.queries;

import krTools.language.Query;
import krTools.parser.SourceInfo;
import nl.tudelft.goalkeeper.checking.violations.source.CharacterSource;
import nl.tudelft.goalkeeper.exceptions.InvalidKRLanguageException;
import nl.tudelft.goalkeeper.exceptions.UnknownKRLanguageException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import swiprolog.language.PrologExpression;
import swiprolog.language.PrologQuery;
import swiprolog.language.PrologVar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * Test class for the ExpressionParser class.
 */
class ExpressionParserTest {

    private static final String FILE_NAME = "456fxzw0994t";
    private static final int LINE_NUMBER = 78;
    private static final int CHARACTER_POSITION = 306;

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

    /**
     * Checks that the source is parsed properly.
     */
    @Test
    void sourceTest() throws UnknownKRLanguageException, InvalidKRLanguageException {
        SourceInfo si = Mockito.mock(SourceInfo.class);
        Mockito.when(si.getSource()).thenReturn(FILE_NAME);
        Mockito.when(si.getLineNumber()).thenReturn(LINE_NUMBER);
        Mockito.when(si.getCharacterPosition()).thenReturn(CHARACTER_POSITION);
        PrologExpression query = Mockito.mock(PrologVar.class);
        Mockito.when(query.getSourceInfo()).thenReturn(si);
        CharacterSource source = (CharacterSource) new ExpressionParser().parse(query).getSource();
        assertThat(source.getFile()).isEqualTo(FILE_NAME);
        assertThat(source.getLine()).isEqualTo(LINE_NUMBER);
        assertThat(source.getPosition()).isEqualTo(CHARACTER_POSITION);
    }
}
