package test.java.instruction;

import main.java.common.CiscComputer;
import main.java.common.Initializer;
import main.java.instruction.Instruction;
import main.java.instruction.InstructionDecoder;
import main.java.memory.Address;
import main.java.memory.Cache;
import main.java.memory.Word;
import main.java.register.ConditionCode;
import main.java.register.FloatingPointRegister;
import main.java.register.Register;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class FloatingPointInstructionProcessorTest {

    private CiscComputer ciscComputer;
    private FloatingPointRegister fr0;
    private FloatingPointRegister fr1;
    private Register register;
    private ConditionCode conditionCode;

    @Before
    public void setUp() {
        ciscComputer = new Initializer().initialize();
        fr0 = ciscComputer.getFloatingPointRegister(0);
        fr1 = ciscComputer.getFloatingPointRegister(1);
        register = ciscComputer.getGeneralPurposeRegister(0);
        conditionCode = ciscComputer.getConditionCode();
    }

    @Test
    public void testFadd() {
        fr0.setFloatingPointValue(BigDecimal.valueOf(2.15E20));
        Cache.writeToMemory(new Address(16), new Word("0001001011010111"));

        ciscComputer.getInstructionRegister().setBinaryInstruction("1000010000010000");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("FADD 0,0,16", instruction.symbolicForm());
        assertEquals(BigDecimal.valueOf(4.3E+20).toEngineeringString(), fr0.getFloatingPointValue().toEngineeringString());
    }
}
