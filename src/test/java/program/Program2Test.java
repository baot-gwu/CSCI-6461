package test.java.program;

import main.java.common.CiscComputer;
import main.java.common.Initializer;
import main.java.memory.Memory;
import main.java.program.Program2;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class Program2Test {

    private Program2 program2;
    private CiscComputer ciscComputer;

    @Before
    public void setUp() {
        program2 = new Program2();
        ciscComputer = new Initializer().initialize();
        new Memory().loadContent();
    }

    @Test
    public void testProgram2() throws IOException {
        int endAddress = program2.readAndStoreParagraphIntoMemory(ciscComputer);

        assertEquals("Given Word: library Line Number: 2 Word Number: 4", program2.matchWord(ciscComputer, endAddress, "library"));
    }
}
