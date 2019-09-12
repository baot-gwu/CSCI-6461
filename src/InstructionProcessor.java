/**
 * @author jalal
 * @since 12/9/19
 */
public class InstructionProcessor {

    public void parseAndProcessInstruction(CiscComputer ciscComputer, String encodedInstruction) {
        String opcodeInBinary = encodedInstruction.substring(0, 6);
        InstructionType type = InstructionType.getType(opcodeInBinary);

        String generalPurposeRegisterNumberInBinary = encodedInstruction.substring(6, 8);

        GeneralPurposeRegister generalPurposeRegister = ciscComputer.getGeneralPurposeRegister(Utils.binaryToDecimal(generalPurposeRegisterNumberInBinary));

        String indexRegisterNumberInBinary = encodedInstruction.substring(8, 10);
        IndexRegister indexRegister = null;
        if (!indexRegisterNumberInBinary.equals("00")) {
            indexRegister = ciscComputer.getIndexRegister(Utils.binaryToDecimal(indexRegisterNumberInBinary));
        }

        boolean indirect = encodedInstruction.substring(10, 11).equals("1");
        int effectiveAddress = Utils.binaryToDecimal(encodedInstruction.substring(11, 16));

        System.out.println(symbolicForm(generalPurposeRegister, indexRegister, type, effectiveAddress, indirect));

        processInstruction(generalPurposeRegister, indexRegister, type, effectiveAddress, indirect);
    }

    private void processInstruction(GeneralPurposeRegister generalPurposeRegister, IndexRegister indexRegister,
                                    InstructionType type, int effectiveAddress, boolean indirect) {

        if (indexRegister != null) {
            effectiveAddress += Integer.parseInt(indexRegister.getValue());
        }

        if (indirect) {
            effectiveAddress = Integer.valueOf(Memory.memoryMap.get(effectiveAddress));
        }

        switch (type) {
            case LDR:
                loadRegisterFromMemory(generalPurposeRegister, effectiveAddress);
                break;
            case STR:
                storeRegisterToMemory(generalPurposeRegister, effectiveAddress);
                break;
        }

    }

    private void storeRegisterToMemory(GeneralPurposeRegister generalPurposeRegister, int effectiveAddress) {
        String value = generalPurposeRegister.getValue();

        Memory.memoryMap.put(effectiveAddress, value);
    }

    private void loadRegisterFromMemory(GeneralPurposeRegister generalPurposeRegister, int effectiveAddress) {
        String value = Memory.memoryMap.get(effectiveAddress);

        generalPurposeRegister.setValue(value);
    }

    public String symbolicForm(GeneralPurposeRegister generalPurposeRegister, IndexRegister indexRegister,
                               InstructionType type, int addressInDecimal, boolean indirect) {

        String symbolicForm = type.name() + " " + generalPurposeRegister.getRegisterNumber()
                + "," + (indexRegister == null ? "0" : indexRegister.getRegisterNumber()) +
                "," + addressInDecimal;

        if (indirect) {
            symbolicForm += ",1";
        }

        return symbolicForm;
    }

}
