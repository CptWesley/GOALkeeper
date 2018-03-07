package nl.tudelft.goalkeeper.parser.results.files.mas2g.parser;

import languageTools.program.mas.LaunchRule;
import languageTools.program.mas.MASProgram;
import nl.tudelft.goalkeeper.parser.results.files.mas2g.Launch.LaunchInstruction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Cedric Willekens (4530373) on 2/25/2018.
 */
class MasParserTest {

    @BeforeEach
    void setup() {
        MASProgram program = Mockito.mock(MASProgram.class);
        Mockito.when(program.getSourceFile().toString()).thenReturn("test");
        LaunchRule rule = Mockito.mock(LaunchRule.class);
        Mockito.when(program.getLaunchRules()).thenReturn(new ArrayList<>(Arrays.asList(rule)));
        LaunchInstruction instruction = Mockito.mock(LaunchInstruction.class);
//        Mockito.when(rule.getInstructions()).thenReturn(Collections.singletonList(instruction));
    }
}