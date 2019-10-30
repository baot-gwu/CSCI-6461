package test.java.instruction;

import main.java.common.CiscComputer;
import main.java.common.Initializer;
import main.java.instruction.Instruction;
import main.java.instruction.InstructionDecoder;
import main.java.register.ConditionCode;
import main.java.register.Register;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MiscellaneousInstructionProcessorTest {

    private CiscComputer ciscComputer;
    private Register register0;
    private Register register1;
    private Register register2;
    private Register register3;
    private ConditionCode conditionCode;

    @Before
    public void setUp() {
        ciscComputer = new Initializer().initialize();
        register0 = ciscComputer.getGeneralPurposeRegister(0);
        register1 = ciscComputer.getGeneralPurposeRegister(1);
        register2 = ciscComputer.getGeneralPurposeRegister(2);
        register3 = ciscComputer.getGeneralPurposeRegister(3);
        conditionCode = ciscComputer.getConditionCode();
    }


    @Test
    public void testTrap() {
        ciscComputer.getProgramCounter().setDecimalValue(265);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0111100000001010");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);

        assertEquals("TRAP 10", instruction.symbolicForm());

        instruction.getType().getProcessor().process(ciscComputer, instruction);
        assertEquals(266, ciscComputer.getProgramCounter().getDecimalValue());
    }
}
