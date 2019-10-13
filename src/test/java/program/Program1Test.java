package test.java.program;

import main.java.common.CiscComputer;
import main.java.common.Initializer;
import main.java.memory.Memory;
import main.java.program.Program1;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Program1Test {

    private static final int memoryAddressStartPoint = 2000;
    private Program1 program1;
    private CiscComputer ciscComputer;

    @Before
    public void setUp() {
        program1 = new Program1();
        ciscComputer = new Initializer().initialize();
        new Memory().loadContent();
    }

    @Test
    public void testFindClosestNumber() {
        int[] inputNumbers = {4587, 15541, 1554, 9455, 1551,
                5858, 8951, 3211, 4558, 2554, 5454, 7893, 4845, 3684, 1234, 1225, 15441, 15548, 1364, 6987};
        program1.inputAndStoreNumber(ciscComputer, inputNumbers, memoryAddressStartPoint);

        assertEquals(2554, program1.findClosestNumber(ciscComputer, 2258, inputNumbers.length, memoryAddressStartPoint));
    }
}
