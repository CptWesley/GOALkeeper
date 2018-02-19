package nl.tudelft.goalkeeper.parser.results.files.module.parsers;

import krTools.parser.SourceInfo;
import languageTools.errors.Message;
import nl.tudelft.goalkeeper.checking.violations.Violation;
import nl.tudelft.goalkeeper.checking.violations.source.CharacterSource;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class of the MessageParser class.
 */
class MessageParserTest {

    /**
     * Checks that we can properly convert errors to violations.
     */
    @Test
    void toViolationTest() {
        Message error = Mockito.mock(Message.class);
        SourceInfo si = Mockito.mock(SourceInfo.class);
        Mockito.when(error.getSource()).thenReturn(si);
        Mockito.when(error.toShortString()).thenReturn("bla");
        Mockito.when(si.getLineNumber()).thenReturn(23);
        Mockito.when(si.getStartIndex()).thenReturn(12);
        Mockito.when(si.getSource()).thenReturn("foobar");
        Violation violation = new MessageParser().parse(error);
        assertThat(((CharacterSource) violation.getSource()).getLine()).isEqualTo(23);
        assertThat(((CharacterSource) violation.getSource()).getPosition()).isEqualTo(12);
        assertThat(violation.getSource().getFile()).isEqualTo("foobar");
        assertThat(violation.getMessage()).isEqualTo("bla");
    }
}
