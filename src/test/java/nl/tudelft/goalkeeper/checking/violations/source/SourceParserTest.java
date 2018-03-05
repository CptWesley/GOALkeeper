package nl.tudelft.goalkeeper.checking.violations.source;

import krTools.parser.SourceInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the SourceParser class.
 */
class SourceParserTest {

    private static final String FILE_NAME = "fgdsuionfgdsuion";
    private static int LINE_NUMBER = 32;
    private static int CHARACTER_POSITION = 2;

    private SourceInfo si;

    /**
     * Sets up the testing environment before each test.
     */
    @BeforeEach
    void setup() {
        si = Mockito.mock(SourceInfo.class);
        Mockito.when(si.getSource()).thenReturn(FILE_NAME);
        Mockito.when(si.getLineNumber()).thenReturn(LINE_NUMBER);
        Mockito.when(si.getCharacterPosition()).thenReturn(CHARACTER_POSITION);
    }

    /**
     * Checks that the parsing is handled properly.
     */
    @Test
    void parseTest() {
        Source s = new SourceParser().parse(si);
        assertThat(s.getFile()).isEqualTo(FILE_NAME);
        assertThat(s).isInstanceOf(CharacterSource.class);
        CharacterSource cs = (CharacterSource) s;
        assertThat(cs.getLine()).isEqualTo(LINE_NUMBER);
        assertThat(cs.getPosition()).isEqualTo(CHARACTER_POSITION);
    }
}
