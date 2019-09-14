/**
 * @author jalal
 * @since 12/9/19
 */
public class InstructionProcessor {

    public void processInstruction(CiscComputer ciscComputer) {
        String binaryInstruction = ciscComputer.getInstructionRegister().getBinaryInstruction();

        String opcodeInBinary = binaryInstruction.substring(0, 6);
        InstructionType type = InstructionType.getType(opcodeInBinary);

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
        int effectiveAddress = Utils.binaryToDecimal(binaryInstruction.substring(11, 16));

        System.out.print(symbolicForm(generalPurposeRegister, indexRegister, type, effectiveAddress, indirect));

        processInstruction(ciscComputer, generalPurposeRegister, indexRegister, type, effectiveAddress, indirect);

        System.out.println(" -> " + new Display(ciscComputer).toString());
    }

    private void processInstruction(CiscComputer ciscComputer, GeneralPurposeRegister generalPurposeRegister,
                                    IndexRegister indexRegister, InstructionType type, int effectiveAddress, boolean indirect) {

        effectiveAddress = calculateEffectiveAddress(generalPurposeRegister, indexRegister, type, effectiveAddress, indirect);

        ciscComputer.getMemoryAddressRegister().setValue(Utils.decimalToBinary(effectiveAddress));

        switch (type) {
            case LDR:
                loadRegisterFromMemory(generalPurposeRegister, effectiveAddress);
                break;
            case STR:
                storeRegisterToMemory(generalPurposeRegister, effectiveAddress);
                break;
            case LDA:
                loadRegisterWithAddress(generalPurposeRegister, effectiveAddress);
                break;
            case LDX:
                loadIndexRegisterFromMemory(indexRegister, effectiveAddress);
                break;
            case STX:
                storeIndexRegisterFromMemory(indexRegister, effectiveAddress);
                break;
        }

        ciscComputer.getMemoryBufferRegister().setValue(Memory.memoryMap.get(effectiveAddress));
    }

    private int calculateEffectiveAddress(GeneralPurposeRegister generalPurposeRegister, IndexRegister indexRegister,
                                          InstructionType type, int effectiveAddress, boolean indirect) {

        if (indexRegister == null) {
            if (type == InstructionType.LDX || type == InstructionType.STX) {
                throw new IllegalArgumentException("Invalid Index Register");
            }
        } else if (generalPurposeRegister != null) {
            effectiveAddress += Integer.parseInt(indexRegister.getValue());
        }

        if (indirect) {
            effectiveAddress = Integer.valueOf(Memory.memoryMap.get(effectiveAddress));
        }

        return effectiveAddress;
    }

    private void storeIndexRegisterFromMemory(IndexRegister indexRegister, int effectiveAddress) {
        Memory.memoryMap.put(effectiveAddress, indexRegister.getValue());
    }

    private void loadIndexRegisterFromMemory(IndexRegister indexRegister, int effectiveAddress) {
        indexRegister.setValue(Memory.memoryMap.get(effectiveAddress));
    }

    private void loadRegisterWithAddress(GeneralPurposeRegister generalPurposeRegister, int effectiveAddress) {
        generalPurposeRegister.setValue(String.valueOf(effectiveAddress));
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

        String symbolicForm = type.name() + " "
                + (generalPurposeRegister == null ? "" : generalPurposeRegister.getRegisterNumber() + ",")
                + (indexRegister == null ? "0" : indexRegister.getRegisterNumber()) +
                "," + addressInDecimal;

        if (indirect) {
            symbolicForm += ",1";
        }

        return symbolicForm;
    }

}
