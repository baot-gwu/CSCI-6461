package main.java.instruction;

import main.java.register.Register;
import main.java.util.Utils;

/**
 * @author jalal
 * @since 12/9/19
 * <p>
 * This is the class to to represent an instruction
 */
public class Instruction {

    private Register firstRegister;
    private Register secondRegister;
    private Boolean arithmetic;
    private Boolean left;
    private InstructionType type;
    private Integer effectiveAddressInDecimal;
    private Integer count;
    private Boolean indirect;
    private Integer deviceId;

    public Instruction(Register firstRegister, Register secondRegister, InstructionType type, Integer effectiveAddressInDecimal,
                       Boolean indirect, Boolean arithmetic, Boolean left, Integer count, Integer deviceId) {

        this.firstRegister = firstRegister;
        this.secondRegister = secondRegister;
        this.type = type;
        this.effectiveAddressInDecimal = effectiveAddressInDecimal;
        this.count = count;
        this.indirect = indirect;
        this.arithmetic = arithmetic;
        this.left = left;
        this.deviceId = deviceId;
    }

    public Register getFirstRegister() {
        return firstRegister;
    }

    public void setFirstRegister(Register firstRegister) {
        this.firstRegister = firstRegister;
    }

    public Register getSecondRegister() {
        return secondRegister;
    }

    public void setSecondRegister(Register secondRegister) {
        this.secondRegister = secondRegister;
    }

    public InstructionType getType() {
        return type;
    }

    public Integer getEffectiveAddressInDecimal() {
        return effectiveAddressInDecimal;
    }

    public Boolean isIndirect() {
        return indirect;
    }

    public Boolean isArithmetic() {
        return arithmetic;
    }

    public Boolean isLeft() {
        return left;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public String symbolicForm() {
        String symbolicForm = type.name() + " " + (firstRegister == null ? "" : firstRegister.getRegisterNumber());

        if (Utils.isShiftOrRotateInstruction(type)) {
            symbolicForm += "," + count + "," + (left ? "1" : "0") + "," + (arithmetic ? "0" : "1");
        }

        if (Utils.hasSecondRegister(type)) {
            if (firstRegister != null) {
                symbolicForm += ",";
            }

            symbolicForm += (secondRegister == null ? "0" : secondRegister.getRegisterNumber());
        }

        if (Utils.hasIndexRegister(type)) {
            symbolicForm += "," + effectiveAddressInDecimal;
        } else if (Utils.isIoInstruction(type)) {
            symbolicForm += "," + deviceId;
        }

        if (Boolean.TRUE.equals(indirect)) {
            symbolicForm += ",1";
        }

        return symbolicForm;
    }

}
