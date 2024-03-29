package main.java.register;

/**
 * @author jalal
 * @since 12/9/19
 *
 * Enum defines types of register and number of bits in the register
 */
public enum RegisterType {

    GENERAL_PURPOSE("R", 16),
    INDEX("IX", 16),
    INSTRUCTION_REGISTER("IR", 16),
    MEMORY_ADDRESS_REGISTER("MAR", 16),
    MEMORY_BUFFER_REGISTER("MBR", 16),
    MACHINE_FAULT_REGISTER("MFR", 4),
    PROGRAM_COUNTER("PC", 12),
    CONDITION_CODE("CC", 4),
    FLOATING_POINT_REGISTER("FR", 16);

    private String symbol;
    private int size;

    RegisterType(String symbol, int size) {
        this.symbol = symbol;
        this.size = size;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getSize() {
        return size;
    }
}
