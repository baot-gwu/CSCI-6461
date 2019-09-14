/**
 * @author jalal
 * @since 12/9/19
 */
public enum RegisterType {

    GENERAL_PURPOSE("R"),
    INDEX("IX"),
    INSTRUCTION_REGISTER("IR"),
    MEMORY_ADDRESS_REGISTER("MAR"),
    MEMORY_BUFFER_REGISTER("MBR");

    private String symbol;

    RegisterType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

}
