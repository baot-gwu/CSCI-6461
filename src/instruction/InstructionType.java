package instruction;

/**
 * @author jalal
 * @since 12/9/19
 *
 * Enum to represent types of instruction, their Symbolic form and binary values
 */
public enum InstructionType {

    LDR("000001"),
    STR("000010"),
    LDA("000011"),
    LDX("101001"),
    STX("101010");

    private final String opcodeInBinary;

    InstructionType(String opcodeInBinary) {
        this.opcodeInBinary = opcodeInBinary;
    }

    public String getOpcodeInBinary() {
        return opcodeInBinary;
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
