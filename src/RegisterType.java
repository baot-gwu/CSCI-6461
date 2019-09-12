/**
 * @author jalal
 * @since 12/9/19
 */
public enum RegisterType {

    GENERAL_PURPOSE("R"),
    INDEX("IX");

    private String symbol;

    RegisterType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

}
