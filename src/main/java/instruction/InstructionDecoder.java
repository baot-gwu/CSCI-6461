package main.java.instruction;

import main.java.common.CiscComputer;
import main.java.register.InstructionRegister;
import main.java.register.Register;
import main.java.util.Utils;

/**
 * @author jalal
 * @since 12/9/19
 *
 * This class decode instruction.
 *
 */
public class InstructionDecoder {

    /**
     * Method to decode an instruction, if not proper instruction return null
     *
     * Takes binary instruction from {@link InstructionRegister} then
     * validate by type. If first 6 bit represent a valid instruction then
     * define the type of the instruction
     *
     * Then fetch general purpose register from next two bit (7 and 8)
     *
     * Then fetch index register from next two bit (9 and 10)
     *
     * Then check direct or indirect from bit 11
     *
     * Then 12 to 16 bit parsed as address in memory
     */
    public Instruction decode(CiscComputer ciscComputer) {
        String binaryInstruction = ciscComputer.getInstructionRegister().getBinaryInstruction();

        InstructionType type = getInstructionType(binaryInstruction);
        if (type == null) {
            ciscComputer.getMachineFaultRegister().setDecimalValue(Utils.MFR_ID_ILLEGAL_OPCODE);
            return null;
        }

        String firstRegisterNumberInBinary = binaryInstruction.substring(6, 8);

        Register fistRegister = null;
        if (type != InstructionType.LDX && type != InstructionType.STX) {
            fistRegister = ciscComputer.getGeneralPurposeRegister(Utils.binaryToDecimal(firstRegisterNumberInBinary));
        }

        String secondRegisterNumberInBinary = binaryInstruction.substring(8, 10);
        Register secondRegister = null;
        Boolean arithmeticShift = null;
        Boolean leftShift = null;
        Integer count = null;
        Integer effectiveAddressInDecimal = null;
        Integer deviceId = null;

        if (Utils.hasSecondRegister(type)) {
            if (Utils.hasIndexRegister(type)) {
                if (!secondRegisterNumberInBinary.equals("00")) {
                    secondRegister = ciscComputer.getIndexRegister(Utils.binaryToDecimal(secondRegisterNumberInBinary));
                }
            } else {
                secondRegister = ciscComputer.getGeneralPurposeRegister(Utils.binaryToDecimal(secondRegisterNumberInBinary));
            }
        } else if (Utils.isShiftOrRotateInstruction(type)) {
            arithmeticShift = binaryInstruction.charAt(8) == '0';
            leftShift = binaryInstruction.charAt(9) == '1';
            count = Utils.binaryToDecimal(binaryInstruction.substring(12, 16));
        }

        boolean indirect = binaryInstruction.substring(10, 11).equals("1");
        int valueOf11To16 = Utils.binaryToDecimal(binaryInstruction.substring(11, 16));

        if (Utils.isIoInstruction(type)) {
            deviceId = valueOf11To16;
        } else {
            effectiveAddressInDecimal = valueOf11To16;
        }

        return new Instruction(fistRegister, secondRegister, type, effectiveAddressInDecimal, indirect, arithmeticShift,
                leftShift, count, deviceId);
    }

    private InstructionType getInstructionType(String binaryInstruction) {
        if (binaryInstruction == null || binaryInstruction.length() < 6) {
            return null;
        }

        String opcodeInBinary = binaryInstruction.substring(0, 6);

        return InstructionType.getType(opcodeInBinary);
    }

}
