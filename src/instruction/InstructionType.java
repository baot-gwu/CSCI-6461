package instruction;

/**
 * @author jalal
 * @since 12/9/19
 *
 * Enum to represent types of instruction, their Symbolic form and binary values
 */
public enum InstructionType {

    LDR("000001", new LoadStoreProcessor()),
    STR("000010", new LoadStoreProcessor()),
    LDA("000011", new LoadStoreProcessor()),
    AMR("000100", new ArithmeticLogicalProcessor()),
    SMR("000101", new ArithmeticLogicalProcessor()),
    AIR("000110", new ArithmeticLogicalProcessor()),
    SIR("000111", new ArithmeticLogicalProcessor()),
    MLT("010100", new ArithmeticLogicalProcessor()),
    DVD("010101", new ArithmeticLogicalProcessor()),
    TRR("010110", new ArithmeticLogicalProcessor()),
    AND("010111", new ArithmeticLogicalProcessor()),
    ORR("011000", new ArithmeticLogicalProcessor()),
    NOT("011001", new ArithmeticLogicalProcessor()),
    SRC("011111", new ArithmeticLogicalProcessor()),
    RRC("100000", new ArithmeticLogicalProcessor()),
    LDX("101001", new LoadStoreProcessor()),
    STX("101010", new LoadStoreProcessor());

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
