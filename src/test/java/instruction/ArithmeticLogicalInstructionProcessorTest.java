package test.java.instruction;

import main.java.common.CiscComputer;
import main.java.common.Initializer;
import main.java.instruction.Instruction;
import main.java.instruction.InstructionDecoder;
import main.java.register.ConditionCode;
import main.java.register.ConditionCodeType;
import main.java.register.Register;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArithmeticLogicalInstructionProcessorTest {

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
    public void testMLT() {
        register0.setDecimalValue(32001);
        register2.setDecimalValue(16384);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0101000010000000");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("MLT 0,2", instruction.symbolicForm());
        assertEquals(8000, register0.getDecimalValue());
        assertEquals(16384, register1.getDecimalValue());
    }

    @Test
    public void testMLTOverflow() {
        register0.setDecimalValue(65536);
        register2.setDecimalValue(65536);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0101000010000000");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals(ConditionCodeType.OVERFLOW, conditionCode.getConditionCodeType());
    }

    @Test
    public void testDVD() {
        register2.setDecimalValue(16583);
        register0.setDecimalValue(225);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0101011000000000");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("DVD 2,0", instruction.symbolicForm());
        assertEquals(73, register2.getDecimalValue());
        assertEquals(158, register3.getDecimalValue());
    }

    @Test
    public void testDVDByZero() {
        register2.setDecimalValue(16583);
        register0.setDecimalValue(0);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0101011000000000");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals(ConditionCodeType.DIVZERO, conditionCode.getConditionCodeType());
    }

    @Test
    public void testTRREqual() {
        register0.setDecimalValue(10);
        register1.setDecimalValue(10);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0101100001000000");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals(ConditionCodeType.EQUALORNOT, ciscComputer.getConditionCode().getConditionCodeType());
        assertEquals(1, ciscComputer.getConditionCode().getDecimalValue());
    }

    @Test
    public void testTRRNotEqual() {
        register0.setDecimalValue(150);
        register1.setDecimalValue(101);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0101100001000000");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals(ConditionCodeType.EQUALORNOT, ciscComputer.getConditionCode().getConditionCodeType());
        assertEquals(0, ciscComputer.getConditionCode().getDecimalValue());
    }

    @Test
    public void testAnd() {
        register0.setDecimalValue(2365);
        register1.setDecimalValue(984);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0101110001000000");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals(280, register0.getDecimalValue());
    }

    @Test
    public void testOr() {
        register0.setDecimalValue(1365);
        register1.setDecimalValue(1365);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0110000001000000");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals(1365, register0.getDecimalValue());
    }

    @Test
    public void testNot() {
        register0.setDecimalValue(23658);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0110010001000000");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals(-23659, register0.getDecimalValue());
    }

    @Test
    public void testSRCLogicalLeftShift() {
        register0.setBinaryValue("0000000000000110");
        ciscComputer.getInstructionRegister().setBinaryInstruction("0111110011000011");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("SRC 0,3,1,1", instruction.symbolicForm());
        assertEquals("0000000000110000", register0.getValue(true));
    }

    @Test
    public void testSRCArithmeticalLeftShift() {
        register0.setBinaryValue("0000000000000110");
        ciscComputer.getInstructionRegister().setBinaryInstruction("0111110001000011");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("SRC 0,3,1,0", instruction.symbolicForm());
        assertEquals("0000000000110000", register0.getValue(true));
    }

    @Test
    public void testSRCArithmeticRightShift() {
        register0.setBinaryValue("1000000000000110");
        ciscComputer.getInstructionRegister().setBinaryInstruction("0111110000000010");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals(instruction.symbolicForm(), "SRC 0,2,0,0");
        assertEquals("1100000000000001", register0.getValue(true));
    }

    @Test
    public void testSRCLogicalRightShift() {
        register0.setBinaryValue("1000000000000110");
        ciscComputer.getInstructionRegister().setBinaryInstruction("0111110010000010");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("SRC 0,2,0,1", instruction.symbolicForm());
        assertEquals("0010000000000001", register0.getValue(true));
    }

    @Test
    public void testRRCRight() {
        register0.setBinaryValue("1000000000000110");
        ciscComputer.getInstructionRegister().setBinaryInstruction("1000000010000010");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("RRC 0,2,0,1", instruction.symbolicForm());
        assertEquals("1010000000000001", register0.getValue(true));
    }

    @Test
    public void testRRCLeft() {
        register0.setBinaryValue("1000000000000110");
        ciscComputer.getInstructionRegister().setBinaryInstruction("1000000011000010");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("RRC 0,2,1,1", instruction.symbolicForm());
        assertEquals("0000000000011010", register0.getValue(true));
    }
}
