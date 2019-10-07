package test.java.instruction;

import main.java.common.CiscComputer;
import main.java.common.Initializer;
import main.java.instruction.Instruction;
import main.java.instruction.InstructionDecoder;
import main.java.register.ConditionCode;
import main.java.register.ConditionCodeType;
import main.java.register.ProgramCounter;
import main.java.register.Register;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArithmeticLogicalProcessorTest {

    private CiscComputer ciscComputer;
    private Register register0;
    private Register register1;
    private Register register2;
    private Register register3;
    private ProgramCounter pc = new ProgramCounter();
    private ConditionCode cc;

    @Before
    public void setUp() {
        ciscComputer = new Initializer().initialize();
        register0 = ciscComputer.getGeneralPurposeRegister(0);
        register1 = ciscComputer.getGeneralPurposeRegister(1);
        register2 = ciscComputer.getGeneralPurposeRegister(2);
        register3 = ciscComputer.getGeneralPurposeRegister(3);
        pc.setDecimalValue(0);
        cc = ciscComputer.getConditionCode();
    }

    @Test
    public void testMLT() {
        register0.setDecimalValue(32001);
        register2.setDecimalValue(16384);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0101000010000000");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals(instruction.symbolicForm(), "MLT 0,2");
        assertEquals(register0.getDecimalValue(), 8000);
        assertEquals(register1.getDecimalValue(), 16384);
    }

    @Test
    public void testDVD() {
        register2.setDecimalValue(16583);
        register0.setDecimalValue(225);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0101011000000000");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals(instruction.symbolicForm(), "DVD 2,0");
        assertEquals(register2.getDecimalValue(), 73);
        assertEquals(register3.getDecimalValue(), 158);
    }

    @Test
    public void testDVDByZero() {
        register2.setDecimalValue(16583);
        register0.setDecimalValue(0);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0101011000000000");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals(ciscComputer.getConditionCode().getConditionCodeType(), ConditionCodeType.DIVZERO);
    }

    @Test
    public void testTRREqual() {
        register0.setDecimalValue(10);
        register1.setDecimalValue(10);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0101100001000000");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals(ciscComputer.getConditionCode().getConditionCodeType(), ConditionCodeType.EQUALORNOT);
        assertEquals(ciscComputer.getConditionCode().getDecimalValue(), 1);
    }

    @Test
    public void testTRRNotEqual() {
        register0.setDecimalValue(150);
        register1.setDecimalValue(101);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0101100001000000");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals(ciscComputer.getConditionCode().getConditionCodeType(), ConditionCodeType.EQUALORNOT);
        assertEquals(ciscComputer.getConditionCode().getDecimalValue(), 0);
    }

    @Test
    public void testAnd() {
        register0.setDecimalValue(2365);
        register1.setDecimalValue(984);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0101110001000000");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals(register0.getDecimalValue(), 280);
    }

    @Test
    public void testOr() {
        register0.setDecimalValue(1365);
        register1.setDecimalValue(1365);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0110000001000000");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals(register0.getDecimalValue(), 1365);
    }

    @Test
    public void testNot() {
        register0.setDecimalValue(23658);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0110010001000000");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals(register0.getDecimalValue(), -23659);
    }

    @Test
    public void testSRCLogicalLeftShift() {
        register0.setBinaryValue("0000000000000110");
        ciscComputer.getInstructionRegister().setBinaryInstruction("0111110011000011");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals(instruction.symbolicForm(), "SRC 0,3,1,1");
        assertEquals(register0.getValue(true), "0000000000110000");
    }

    @Test
    public void testSRCArithmeticalLeftShift() {
        register0.setBinaryValue("0000000000000110");
        ciscComputer.getInstructionRegister().setBinaryInstruction("0111110001000011");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals(instruction.symbolicForm(), "SRC 0,3,1,0");
        assertEquals(register0.getValue(true), "0000000000110000");
    }

    @Test
    public void testSRCArithmeticRightShift() {
        register0.setBinaryValue("1000000000000110");
        ciscComputer.getInstructionRegister().setBinaryInstruction("0111110000000010");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals(instruction.symbolicForm(), "SRC 0,2,0,0");
        assertEquals(register0.getValue(true), "1100000000000001");
    }

    @Test
    public void testSRCLogicalRightShift() {
        register0.setBinaryValue("1000000000000110");
        ciscComputer.getInstructionRegister().setBinaryInstruction("0111110010000010");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals(instruction.symbolicForm(), "SRC 0,2,0,1");
        assertEquals(register0.getValue(true), "0010000000000001");
    }

    @Test
    public void testRRCRight() {
        register0.setBinaryValue("1000000000000110");
        ciscComputer.getInstructionRegister().setBinaryInstruction("1000000010000010");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals(instruction.symbolicForm(), "RRC 0,2,0,1");
        assertEquals(register0.getValue(true), "1010000000000001");
    }

    @Test
    public void testRRCLeft() {
        register0.setBinaryValue("1000000000000110");
        ciscComputer.getInstructionRegister().setBinaryInstruction("1000000011000010");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals(instruction.symbolicForm(), "RRC 0,2,1,1");
        assertEquals(register0.getValue(true), "0000000000011010");
    }

    @Test
    public void testJZ_zero() {
        register0.setDecimalValue(0);
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0010100000000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("1", ciscComputer.getProgramCounter().getValue(true));
    }

    @Test
    public void testJZ_notZero() {
        register0.setDecimalValue(1);
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0010100000000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("11", ciscComputer.getProgramCounter().getValue(true));
    }

    @Test
    public void testJNE_notZero() {
        register0.setDecimalValue(1);
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0010110000000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("1", ciscComputer.getProgramCounter().getValue(true));
    }

    @Test
    public void testJNE_Zero() {
        register0.setDecimalValue(0);
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0010110000000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("11", ciscComputer.getProgramCounter().getValue(true));
    }

    @Test
    public void testJCC_Match() {
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        cc.setDecimalValue(1);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0011000100000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("1", ciscComputer.getProgramCounter().getValue(true));
    }

    @Test
    public void testJCC_notMatch() {
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        cc.setDecimalValue(0);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0011000100000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("11", ciscComputer.getProgramCounter().getValue(true));
    }

    @Test
    public void testJMA() {
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        cc.setDecimalValue(0);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0011010000000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("1", ciscComputer.getProgramCounter().getValue(true));
    }

    @Test
    public void testJSR() {
        register3.setDecimalValue(0);
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0011100000000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("1", ciscComputer.getProgramCounter().getValue(true));
        assertEquals("11", register3.getValue(true));
        //TODO: detect R0
    }

    @Test
    public void testRFS() {
        register3.setDecimalValue(3);
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0011110000000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("1", register0.getValue(true));
        assertEquals("11", ciscComputer.getProgramCounter().getValue(true));
    }

    @Test
    public void testSOB_Positive() {
        register0.setDecimalValue(2);
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0100000000000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("1", register0.getValue(true));
        assertEquals("1", ciscComputer.getProgramCounter().getValue(true));
    }

    @Test
    public void testSOB_notPositive() {
        register0.setDecimalValue(1);
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0100000000000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("0", register0.getValue(true));
        assertEquals("11", ciscComputer.getProgramCounter().getValue(true));
    }

    @Test
    public void testJGE_Positve() {
        register0.setDecimalValue(1);
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0100010000000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("1", ciscComputer.getProgramCounter().getValue(true));
    }

    @Test
    public void testJGE_Zero() {
        register0.setDecimalValue(0);
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0100010000000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("1", ciscComputer.getProgramCounter().getValue(true));
    }

    @Test
    public void testJGE_Negative() {
        register0.setDecimalValue(-1);
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0100010000000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("11", ciscComputer.getProgramCounter().getValue(true));
    }
}
