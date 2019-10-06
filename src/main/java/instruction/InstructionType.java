package main.java.instruction;

/**
 * @author jalal
 * @since 12/9/19
 *
 * Enum to represent types of instruction, their Symbolic form and binary values
 */
public enum InstructionType {

    LDR("000001", LoadStoreProcessor.getInstance()),
    STR("000010", LoadStoreProcessor.getInstance()),
    LDA("000011", LoadStoreProcessor.getInstance()),
    AMR("000100", ArithmeticLogicalProcessor.getInstance()),
    SMR("000101", ArithmeticLogicalProcessor.getInstance()),
    AIR("000110", ArithmeticLogicalProcessor.getInstance()),
    SIR("000111", ArithmeticLogicalProcessor.getInstance()),
    MLT("010100", ArithmeticLogicalProcessor.getInstance()),
    DVD("010101", ArithmeticLogicalProcessor.getInstance()),
    TRR("010110", ArithmeticLogicalProcessor.getInstance()),
    AND("010111", ArithmeticLogicalProcessor.getInstance()),
    ORR("011000", ArithmeticLogicalProcessor.getInstance()),
    NOT("011001", ArithmeticLogicalProcessor.getInstance()),
    SRC("011111", ArithmeticLogicalProcessor.getInstance()),
    RRC("100000", ArithmeticLogicalProcessor.getInstance()),
    LDX("101001", LoadStoreProcessor.getInstance()),
    STX("101010", LoadStoreProcessor.getInstance());

    private final String opcodeInBinary;
    private final InstructionProcessor processor;


    InstructionType(String opcodeInBinary, InstructionProcessor processor) {
        this.opcodeInBinary = opcodeInBinary;
        this.processor = processor;
    }

    public String getOpcodeInBinary() {
        return opcodeInBinary;
    }

    public InstructionProcessor getProcessor() {
        return processor;
    }

    public static InstructionType getType(String opcodeInBinary) {
        for (InstructionType instruction : InstructionType.values()) {
            if (instruction.getOpcodeInBinary().equals(opcodeInBinary)) {
                return instruction;
            }
        }

        return null;
    }
}
