package test.java.program;

import main.java.common.CiscComputer;
import main.java.common.Initializer;
import main.java.memory.Memory;
import main.java.program.Program2;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Program2Test {

    private Program2 program2;
    private CiscComputer ciscComputer;

    @Before
    public void setUp() {
        program2 = new Program2();
        ciscComputer = new Initializer().initialize();
        new Memory().loadContent(null);
    }

    @Test
    public void testProgram2() {
        int endAddress = program2.readAndStoreParagraphIntoMemory(ciscComputer);

        assertEquals("Given Word: library, Sentence Number: 2, Word Number: 9", program2.matchWord(ciscComputer, endAddress, "library"));
    }

    @Test
    public void testEncodeDecode() {
        program2.encodeToBinaryAndSave("Test.file");

        assertEquals("Hello\n" +
                "CSCI6461\n" +
                "534545\n" +
                "GWU", program2.readAndDecodeFromBinary());
    }
}
