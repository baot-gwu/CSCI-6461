package main.java.register;

import main.java.util.Utils;

/**
 * @author jalal
 * @since 12/9/19
 *
 * Abstract class for all the register.
 *
 * Hold register number, binary and decimal value
 * and enforce to define register type in child class
 */
public abstract class Register {

    int registerNumber;
    protected String binaryValue;
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
        binaryValue = Utils.trimBinaryValue(binaryValue, getType().getSize());

        this.binaryValue = binaryValue;
        this.decimalValue = Utils.unsignedBinaryToDecimal(binaryValue);
    }

    public int getDecimalValue() {
        return decimalValue;
    }

    public void setDecimalValue(int decimalValue) {
        this.decimalValue = decimalValue;
        binaryValue = Utils.decimalToUnsignedBinary(decimalValue);

        this.binaryValue = Utils.trimBinaryValue(binaryValue, getType().getSize());
    }
}
