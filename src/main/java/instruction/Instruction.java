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
    private boolean arithmetic;
    private boolean left;
    private InstructionType type;
    private int effectiveAddressInDecimal;
    private int count;
    private boolean indirect;

    public Instruction(Register firstRegister, Register secondRegister, InstructionType type,
                       int effectiveAddressInDecimal, boolean indirect, boolean arithmetic, boolean left, int count) {

        this.firstRegister = firstRegister;
        this.secondRegister = secondRegister;
        this.type = type;
        this.effectiveAddressInDecimal = effectiveAddressInDecimal;
        this.count = count;
        this.indirect = indirect;
        this.arithmetic = arithmetic;
        this.left = left;
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

    public int getEffectiveAddressInDecimal() {
        return effectiveAddressInDecimal;
    }

    public boolean isIndirect() {
        return indirect;
    }

    public boolean isArithmetic() {
        return arithmetic;
    }

    public boolean isLeft() {
        return left;
    }

    public int getCount() {
        return count;
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
        }

        if (indirect) {
            symbolicForm += ",1";
        }

        return symbolicForm;
    }

}
