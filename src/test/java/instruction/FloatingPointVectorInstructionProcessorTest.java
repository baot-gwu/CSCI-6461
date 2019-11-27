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
import main.java.register.IndexRegister;
import main.java.register.Register;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class FloatingPointVectorInstructionProcessorTest {

    private CiscComputer ciscComputer;
    private FloatingPointRegister fr0;
    private FloatingPointRegister fr1;
    private IndexRegister ix1;
    private Register register;
    private ConditionCode conditionCode;

    @Before
    public void setUp() {
        ciscComputer = new Initializer().initialize();
        fr0 = ciscComputer.getFloatingPointRegister(0);
        fr1 = ciscComputer.getFloatingPointRegister(1);
        ix1 = ciscComputer.getIndexRegister(1);
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

    @Test
    public void testVAdd() {
        fr0.setFloatingPointValue(BigDecimal.valueOf(4));
        ix1.setDecimalValue(100);
        Cache.writeToMemory(new Address(131), new Word("0000000010010110"));
        Cache.writeToMemory(new Address(132), new Word("0000000010011010"));

        Cache.writeToMemory(new Address(150), new Word("0000000000110010"));
        Cache.writeToMemory(new Address(151), new Word("0000000000110011"));
        Cache.writeToMemory(new Address(152), new Word("0000000000110100"));
        Cache.writeToMemory(new Address(153), new Word("0000000000110101"));
        Cache.writeToMemory(new Address(154), new Word("0000000000110010"));
        Cache.writeToMemory(new Address(155), new Word("0000000000110001"));
        Cache.writeToMemory(new Address(156), new Word("0000000000110000"));
        Cache.writeToMemory(new Address(157), new Word("0000000000101111"));

        ciscComputer.getInstructionRegister().setBinaryInstruction("1000110001011111");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("VADD 0,1,31", instruction.symbolicForm());
        assertEquals(Cache.getWordDecimalValue(new Address(150)), 100);
        assertEquals(Cache.getWordDecimalValue(new Address(151)), 100);
        assertEquals(Cache.getWordDecimalValue(new Address(152)), 100);
        assertEquals(Cache.getWordDecimalValue(new Address(153)), 100);
    }

    @Test
    public void testVSub() {
        fr0.setFloatingPointValue(BigDecimal.valueOf(4));
        ix1.setDecimalValue(100);
        Cache.writeToMemory(new Address(131), new Word("0000000010010110"));
        Cache.writeToMemory(new Address(132), new Word("0000000010011010"));

        Cache.writeToMemory(new Address(150), new Word("0000000000110010"));
        Cache.writeToMemory(new Address(151), new Word("0000000000110011"));
        Cache.writeToMemory(new Address(152), new Word("0000000000110100"));
        Cache.writeToMemory(new Address(153), new Word("0000000000110101"));
        Cache.writeToMemory(new Address(154), new Word("0000000000110010"));
        Cache.writeToMemory(new Address(155), new Word("0000000000110001"));
        Cache.writeToMemory(new Address(156), new Word("0000000000110000"));
        Cache.writeToMemory(new Address(157), new Word("0000000000101111"));

        ciscComputer.getInstructionRegister().setBinaryInstruction("1001000001011111");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("VSUB 0,1,31", instruction.symbolicForm());
        assertEquals(Cache.getWordDecimalValue(new Address(150)), 0);
        assertEquals(Cache.getWordDecimalValue(new Address(151)), 2);
        assertEquals(Cache.getWordDecimalValue(new Address(152)), 4);
        assertEquals(Cache.getWordDecimalValue(new Address(153)), 6);
    }
}
