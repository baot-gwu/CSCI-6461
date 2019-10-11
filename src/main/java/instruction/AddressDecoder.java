package main.java.instruction;

import main.java.memory.Address;
import main.java.memory.Cache;
import main.java.register.Register;

public class AddressDecoder {

    protected static Address decodeAddress(Instruction instruction) {
        Register generalPurposeRegister = instruction.getFirstRegister();
        Register indexRegister = instruction.getSecondRegister();
        InstructionType type = instruction.getType();
        Boolean indirect = instruction.isIndirect();
        int effectiveAddressInDecimal = instruction.getEffectiveAddressInDecimal();

        if (type == InstructionType.AIR || type == InstructionType.SIR) {
            return new Address(effectiveAddressInDecimal);
        }

        if (indexRegister == null) {
            if (type == InstructionType.LDX || type == InstructionType.STX) {
                throw new IllegalArgumentException("Invalid Index Register");
            }
        } else if (generalPurposeRegister != null) {
            effectiveAddressInDecimal += indexRegister.getDecimalValue();
        }

        if (Boolean.TRUE.equals(indirect)) {
            effectiveAddressInDecimal = Cache.getWordDecimalValue(new Address(effectiveAddressInDecimal));
        }

        return new Address(effectiveAddressInDecimal);
    }
}
