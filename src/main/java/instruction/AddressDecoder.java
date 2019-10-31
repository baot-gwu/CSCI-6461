package main.java.instruction;

import main.java.memory.Address;
import main.java.memory.Cache;
import main.java.memory.Memory;
import main.java.register.MachineFaultRegister;
import main.java.register.Register;
import main.java.util.Utils;

public class AddressDecoder {

    public static final int RESERVED_LOCATION = 6;
    private static final int RESERVED_ADDRESS_MACHINE_FAULT = 1;

    protected static Address decodeAddress(Instruction instruction, MachineFaultRegister machineFaultRegister) {
        Register firstRegister = instruction.getFirstRegister();
        Register secondRegister = instruction.getSecondRegister();
        InstructionType type = instruction.getType();
        Boolean indirect = instruction.isIndirect();
        int effectiveAddressInDecimal = instruction.getEffectiveAddressInDecimal();

        if (type == InstructionType.AIR || type == InstructionType.SIR) {
            return new Address(effectiveAddressInDecimal);
        }

        if (secondRegister == null) {
            if (type == InstructionType.LDX || type == InstructionType.STX) {
                throw new IllegalArgumentException("Invalid Index Register");
            }
        } else if (firstRegister != null) {
            effectiveAddressInDecimal += secondRegister.getDecimalValue();
        }

        if (Boolean.TRUE.equals(indirect)) {
            effectiveAddressInDecimal = Cache.getWordDecimalValue(new Address(effectiveAddressInDecimal));
        }

        if (effectiveAddressInDecimal >= Memory.MAX_MEMORY_SIZE) {
            machineFaultRegister.setDecimalValue(Utils.MFR_ID_ILLEGAL_MEMORY_ADDRESS_BEYOND_SIZE);
            effectiveAddressInDecimal = RESERVED_ADDRESS_MACHINE_FAULT;
        } else if (effectiveAddressInDecimal < RESERVED_LOCATION) {
            machineFaultRegister.setDecimalValue(Utils.MFR_ID_ILLEGAL_MEMORY_ADDRESS_RESERVED_LOCATION);
            effectiveAddressInDecimal = RESERVED_ADDRESS_MACHINE_FAULT;
        }

        return new Address(effectiveAddressInDecimal);
    }
}
