package common;

import memory.Memory;
import register.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author jalal
 * @since 12/9/19
 *
 * This is the class to initialize all the class associated with {@link CiscComputer}
 */
public class Initializer {

    public CiscComputer initialize() {

        CiscComputer ciscComputer = new CiscComputer();

        ciscComputer.setMemory(createMemory());

        ciscComputer.setIndexRegisters(createIndexRegisters());

        ciscComputer.setGeneralPurposeRegisters(createGeneralPurposeRegisters());

        ciscComputer.setInstructionRegister(new InstructionRegister());

        ciscComputer.setMemoryAddressRegister(new MemoryAddressRegister());

        ciscComputer.setMemoryBufferRegister(new MemoryBufferRegister());

        ciscComputer.setProgramCounter(new ProgramCounter());

        ciscComputer.setConditionCode(new ConditionCode());

        return ciscComputer;
    }

    private List<GeneralPurposeRegister> createGeneralPurposeRegisters() {
        return Arrays.asList(new GeneralPurposeRegister(0),
                new GeneralPurposeRegister(1),
                new GeneralPurposeRegister(2),
                new GeneralPurposeRegister(3));
    }

    private List<IndexRegister> createIndexRegisters() {
        return Arrays.asList(new IndexRegister(1),
                new IndexRegister(2),
                new IndexRegister(3));
    }

    private Memory createMemory() {
        Memory memory = new Memory();

        memory.loadContent();

        return memory;
    }
}
