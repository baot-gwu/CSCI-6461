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
            return null;
        }

        String generalPurposeRegisterNumberInBinary = binaryInstruction.substring(6, 8);

        GeneralPurposeRegister generalPurposeRegister = null;
        if (type != InstructionType.LDX && type != InstructionType.STX) {
            generalPurposeRegister = ciscComputer.getGeneralPurposeRegister(Utils.binaryToDecimal(generalPurposeRegisterNumberInBinary));
        }

        String indexRegisterNumberInBinary = binaryInstruction.substring(8, 10);
        IndexRegister indexRegister = null;
        if (!indexRegisterNumberInBinary.equals("00")) {
            indexRegister = ciscComputer.getIndexRegister(Utils.binaryToDecimal(indexRegisterNumberInBinary));
        }

        boolean indirect = binaryInstruction.substring(10, 11).equals("1");
        int effectiveAddressInDecimal = Utils.binaryToDecimal(binaryInstruction.substring(11, 16));

        return new Instruction(generalPurposeRegister, indexRegister, type, effectiveAddressInDecimal, indirect);
    }

    private InstructionType getInstructionType(String binaryInstruction) {
        if (binaryInstruction == null || binaryInstruction.length() < 6) {
            return null;
        }

        String opcodeInBinary = binaryInstruction.substring(0, 6);

        return InstructionType.getType(opcodeInBinary);
    }

}
