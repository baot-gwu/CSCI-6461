package test.java.instruction;

import main.java.common.CiscComputer;
import main.java.common.Initializer;
import main.java.instruction.Instruction;
import main.java.instruction.InstructionDecoder;
import main.java.register.Register;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArithmeticLogicalProcessorTest {

    private CiscComputer ciscComputer;
    private Register register1;
    private Register register2;

    @Before
    public void setUp() {
        ciscComputer = new Initializer().initialize();
        register1 = ciscComputer.getGeneralPurposeRegister(1);
        register2 = ciscComputer.getGeneralPurposeRegister(2);
    }

    @Test
    public void testMLT() {
        register1.setDecimalValue(32001);
        register2.setDecimalValue(16384);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0101000110000000");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals(register1.getDecimalValue(), 8000);
        assertEquals(register2.getDecimalValue(), 16384);
    }
}
