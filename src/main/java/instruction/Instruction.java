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
    private InstructionType type;
    private int effectiveAddressInDecimal;
    private boolean indirect;

    public Instruction(Register firstRegister, Register secondRegister, InstructionType type,
                       int effectiveAddressInDecimal, boolean indirect) {

        this.firstRegister = firstRegister;
        this.secondRegister = secondRegister;
        this.type = type;
        this.effectiveAddressInDecimal = effectiveAddressInDecimal;
        this.indirect = indirect;
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

    public String symbolicForm() {
        String symbolicForm = type.name() + " " + (firstRegister == null ? "" : firstRegister.getRegisterNumber());

        if (isShowSecondRegister()) {
            if (firstRegister != null) {
                symbolicForm += ",";
            }

            symbolicForm += (secondRegister == null ? "0" : secondRegister.getRegisterNumber());
        }

        if (isShowAddress()) {
            symbolicForm += "," + effectiveAddressInDecimal;
        }

        if (indirect) {
            symbolicForm += ",1";
        }

        return symbolicForm;
    }

    private boolean isShowAddress() {
        return Utils.hasIndexRegister(type);
    }

    private boolean isShowSecondRegister() {
        return type != InstructionType.SIR && type != InstructionType.AIR && type != InstructionType.NOT;
    }
}
