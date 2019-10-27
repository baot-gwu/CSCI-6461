package test.java.instruction;

import main.java.common.CiscComputer;
import main.java.common.Initializer;
import main.java.instruction.Instruction;
import main.java.instruction.InstructionDecoder;
import main.java.memory.Memory;
import main.java.memory.Word;
import main.java.register.ConditionCode;
import main.java.register.ProgramCounter;
import main.java.register.Register;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransferInstructionProcessorTest {

    private CiscComputer ciscComputer;
    private Register register0;
    private Register register3;
    private ProgramCounter pc = new ProgramCounter();
    private ConditionCode cc;

    @Before
    public void setUp() {
        ciscComputer = new Initializer().initialize();
        register0 = ciscComputer.getGeneralPurposeRegister(0);
        register3 = ciscComputer.getGeneralPurposeRegister(3);
        pc.setDecimalValue(0);
        cc = ciscComputer.getConditionCode();
    }

    @Test
    public void testJZ_zero() {
        register0.setDecimalValue(0);
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0010100000000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("000000000001", ciscComputer.getProgramCounter().getValue(true));
    }

    @Test
    public void testJZ_notZero() {
        register0.setDecimalValue(1);
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0010100000000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("000000000011", ciscComputer.getProgramCounter().getValue(true));
    }

    @Test
    public void testJNE_notZero() {
        register0.setDecimalValue(1);
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0010110000000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("000000000001", ciscComputer.getProgramCounter().getValue(true));
    }

    @Test
    public void testJNE_Zero() {
        register0.setDecimalValue(0);
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0010110000000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("000000000011", ciscComputer.getProgramCounter().getValue(true));
    }

    @Test
    public void testJCC_Match() {
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        cc.setDecimalValue(1);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0011000100000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("000000000001", ciscComputer.getProgramCounter().getValue(true));
    }

    @Test
    public void testJCC_notMatch() {
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        cc.setDecimalValue(0);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0011000100000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("000000000011", ciscComputer.getProgramCounter().getValue(true));
    }

    @Test
    public void testJMA() {
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        cc.setDecimalValue(0);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0011010000000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("000000000001", ciscComputer.getProgramCounter().getValue(true));
    }

    @Test
    public void testJSR() {
        register3.setDecimalValue(0);
        Memory.memoryMap.replace(8,new Word("0000000000010000"));
        Memory.memoryMap.replace(16,new Word("0000000000011111"));
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0011100000000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("000000000001", ciscComputer.getProgramCounter().getValue(true));
        assertEquals("0000000000000011", register3.getValue(true));
        assertEquals("0000000000010000", register0.getValue(true));
    }

    @Test
    public void testRFS() {
        register3.setDecimalValue(3);
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0011110000000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("0000000000000001", register0.getValue(true));
        assertEquals("000000000011", ciscComputer.getProgramCounter().getValue(true));
    }

    @Test
    public void testSOB_Positive() {
        register0.setDecimalValue(2);
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0100000000000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("0000000000000001", register0.getValue(true));
        assertEquals("000000000001", ciscComputer.getProgramCounter().getValue(true));
    }

    @Test
    public void testSOB_notPositive() {
        register0.setDecimalValue(1);
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0100000000000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("0000000000000000", register0.getValue(true));
        assertEquals("000000000011", ciscComputer.getProgramCounter().getValue(true));
    }

    @Test
    public void testJGE_Positve() {
        register0.setDecimalValue(1);
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0100010000000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("000000000001", ciscComputer.getProgramCounter().getValue(true));
    }

    @Test
    public void testJGE_Zero() {
        register0.setDecimalValue(0);
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0100010000000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("000000000001", ciscComputer.getProgramCounter().getValue(true));
    }

    @Test
    public void testJGE_Negative() {
        register0.setDecimalValue(-1);
        pc.setDecimalValue(2);
        ciscComputer.setProgramCounter(pc);
        ciscComputer.getInstructionRegister().setBinaryInstruction("0100010000000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("000000000011", ciscComputer.getProgramCounter().getValue(true));
    }
}
