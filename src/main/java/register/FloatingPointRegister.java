package main.java.register;

import main.java.util.Utils;

import java.math.BigDecimal;

/*
 * Register for floating points.
 *
 * In CISC there are two floating point registers
 */
public class FloatingPointRegister extends Register {

    private BigDecimal floatingPointValue;

    public FloatingPointRegister(int registerNumber) {
        super.registerNumber = registerNumber;
    }

    public BigDecimal getFloatingPointValue() {
        return floatingPointValue;
    }

    @Override
    RegisterType getType() {
        return RegisterType.FLOATING_POINT_REGISTER;
    }

    public void setFloatingPointValue(BigDecimal value) {
        this.floatingPointValue = value;
        super.binaryValue = Utils.floatingPointToBinary(value);
    }

    @Override
    public void setBinaryValue(String binaryValue) {
        super.binaryValue = binaryValue;
        this.floatingPointValue = Utils.binaryToFloatingPoint(binaryValue);
    }
}
