package main.java.instruction;

/**
 * @author jalal
 * @since 12/9/19
 * <p>
 * Enum to represent types of instruction, their Symbolic form and binary values
 */
public enum InstructionType {

    LDR("000001", LoadStoreInstructionProcessor.getInstance()),
    STR("000010", LoadStoreInstructionProcessor.getInstance()),
    LDA("000011", LoadStoreInstructionProcessor.getInstance()),
    AMR("000100", ArithmeticLogicalInstructionProcessor.getInstance()),
    SMR("000101", ArithmeticLogicalInstructionProcessor.getInstance()),
    AIR("000110", ArithmeticLogicalInstructionProcessor.getInstance()),
    SIR("000111", ArithmeticLogicalInstructionProcessor.getInstance()),
    JZ("001010", TransferInstructionProcessor.getInstance()),
    JNE("001011", TransferInstructionProcessor.getInstance()),
    JCC("001100", TransferInstructionProcessor.getInstance()),
    JMA("001101", TransferInstructionProcessor.getInstance()),
    JSR("001110", TransferInstructionProcessor.getInstance()),
    RFS("001111", TransferInstructionProcessor.getInstance()),
    SOB("010000", TransferInstructionProcessor.getInstance()),
    JGE("010001", TransferInstructionProcessor.getInstance()),
    MLT("010100", ArithmeticLogicalInstructionProcessor.getInstance()),
    DVD("010101", ArithmeticLogicalInstructionProcessor.getInstance()),
    TRR("010110", ArithmeticLogicalInstructionProcessor.getInstance()),
    AND("010111", ArithmeticLogicalInstructionProcessor.getInstance()),
    ORR("011000", ArithmeticLogicalInstructionProcessor.getInstance()),
    NOT("011001", ArithmeticLogicalInstructionProcessor.getInstance()),
    SRC("011111", ArithmeticLogicalInstructionProcessor.getInstance()),
    RRC("100000", ArithmeticLogicalInstructionProcessor.getInstance()),
    LDX("101001", LoadStoreInstructionProcessor.getInstance()),
    STX("101010", LoadStoreInstructionProcessor.getInstance()),
    IN("111101", InputOutputInstructionProcessor.getInstance()),
    OUT("111110", InputOutputInstructionProcessor.getInstance()),
    CHK("111111", InputOutputInstructionProcessor.getInstance());

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
