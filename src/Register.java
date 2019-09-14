/**
 * @author jalal
 * @since 12/9/19
 */
public abstract class Register {

    public static final int SIZE = 16;

    int registerNumber;
    private String binaryValue;
    private int decimalValue;

    abstract RegisterType getType();

    public int getRegisterNumber() {
        return registerNumber;
    }

    public String getValue(boolean binary) {
        return binary ? getBinaryValue() : String.valueOf(getDecimalValue());
    }

    protected String getBinaryValue() {
        return binaryValue;
    }

    public void setBinaryValue(String binaryValue) {
        this.binaryValue = binaryValue;
        this.decimalValue = Utils.binaryToDecimal(binaryValue);
    }

    protected int getDecimalValue() {
        return decimalValue;
    }

    public void setDecimalValue(int decimalValue) {
        this.decimalValue = decimalValue;
        this.binaryValue = Utils.decimalToBinary(decimalValue);
    }
}
