package register;

import util.Utils;

/**
 * @author jalal
 * @since 12/9/19
 *
 * Abstruct class for all the register.
 *
 * Hold register number, binary and decimal value
 * and enforce to define register type in child class
 */
public abstract class Register {

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

    public int getDecimalValue() {
        return decimalValue;
    }

    public void setDecimalValue(int decimalValue) {
        this.decimalValue = decimalValue;
        this.binaryValue = Utils.decimalToBinary(decimalValue);
    }
}
