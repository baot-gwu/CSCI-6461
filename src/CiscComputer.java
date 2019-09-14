import java.util.ArrayList;
import java.util.List;

/**
 * @author jalal
 * @since 12/9/19
 */
public class CiscComputer {

    private List<GeneralPurposeRegister> generalPurposeRegisters = new ArrayList<>(4);
    private List<IndexRegister> indexRegisters = new ArrayList<>(3);
    private MemoryAddressRegister memoryAddressRegister;
    private MemoryBufferRegister memoryBufferRegister;
    private InstructionRegister instructionRegister;
    private Memory memory;

    public List<GeneralPurposeRegister> getGeneralPurposeRegisters() {
        return generalPurposeRegisters;
    }

    public void setGeneralPurposeRegisters(List<GeneralPurposeRegister> generalPurposeRegisters) {
        this.generalPurposeRegisters = generalPurposeRegisters;
    }

    public List<IndexRegister> getIndexRegisters() {
        return indexRegisters;
    }

    public void setIndexRegisters(List<IndexRegister> indexRegisters) {
        this.indexRegisters = indexRegisters;
    }

    public MemoryAddressRegister getMemoryAddressRegister() {
        return memoryAddressRegister;
    }

    public void setMemoryAddressRegister(MemoryAddressRegister memoryAddressRegister) {
        this.memoryAddressRegister = memoryAddressRegister;
    }

    public MemoryBufferRegister getMemoryBufferRegister() {
        return memoryBufferRegister;
    }

    public void setMemoryBufferRegister(MemoryBufferRegister memoryBufferRegister) {
        this.memoryBufferRegister = memoryBufferRegister;
    }

    public InstructionRegister getInstructionRegister() {
        return instructionRegister;
    }

    public void setInstructionRegister(InstructionRegister instructionRegister) {
        this.instructionRegister = instructionRegister;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public GeneralPurposeRegister getGeneralPurposeRegister(int registerNumber) {
        for (GeneralPurposeRegister register : generalPurposeRegisters) {
            if (register.getRegisterNumber() == registerNumber) {
                return register;
            }
        }

        throw new IllegalArgumentException("Invalid General Purpose Register Number" + registerNumber);
    }

    public IndexRegister getIndexRegister(int registerNumber) {
        for (IndexRegister register : indexRegisters) {
            if (register.getRegisterNumber() == registerNumber) {
                return register;
            }
        }

        throw new IllegalArgumentException("Invalid Index Register Number" + registerNumber);
    }
}
