public class InstructionDecoder {

    public Instruction decode(CiscComputer ciscComputer) {
        String binaryInstruction = ciscComputer.getInstructionRegister().getBinaryInstruction();

        if (binaryInstruction == null || binaryInstruction.length() < 6) {
            return null;
        }

        String opcodeInBinary = binaryInstruction.substring(0, 6);
        InstructionType type = InstructionType.getType(opcodeInBinary);

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

}
