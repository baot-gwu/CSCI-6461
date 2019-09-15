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
        int effectiveAddressInDecimal = Utils.binaryToDecimal(binaryInstruction.substring(11, 16));

        System.out.println(symbolicForm(generalPurposeRegister, indexRegister, type, effectiveAddressInDecimal, indirect));

        processInstruction(ciscComputer, generalPurposeRegister, indexRegister, type, effectiveAddressInDecimal, indirect);
    }

    private void processInstruction(CiscComputer ciscComputer, GeneralPurposeRegister generalPurposeRegister,
                                    IndexRegister indexRegister, InstructionType type, int effectiveAddressInDecimal,
                                    boolean indirect) {

        effectiveAddressInDecimal = calculateEffectiveAddress(generalPurposeRegister, indexRegister, type, effectiveAddressInDecimal, indirect);

        ciscComputer.getMemoryAddressRegister().setDecimalValue(effectiveAddressInDecimal);

        switch (type) {
            case LDR:
                loadRegisterFromMemory(generalPurposeRegister, effectiveAddressInDecimal);
                break;
            case STR:
                storeRegisterToMemory(generalPurposeRegister, effectiveAddressInDecimal);
                break;
            case LDA:
                loadRegisterWithAddress(generalPurposeRegister, effectiveAddressInDecimal);
                break;
            case LDX:
                loadIndexRegisterFromMemory(indexRegister, effectiveAddressInDecimal);
                break;
            case STX:
                storeIndexRegisterFromMemory(indexRegister, effectiveAddressInDecimal);
                break;
        }

        ciscComputer.getMemoryBufferRegister().setDecimalValue(Integer.valueOf(Memory.memoryMap.get(effectiveAddressInDecimal)));
    }

    private int calculateEffectiveAddress(GeneralPurposeRegister generalPurposeRegister, IndexRegister indexRegister,
                                          InstructionType type, int effectiveAddressInDecimal, boolean indirect) {

        if (indexRegister == null) {
            if (type == InstructionType.LDX || type == InstructionType.STX) {
                throw new IllegalArgumentException("Invalid Index Register");
            }
        } else if (generalPurposeRegister != null) {
            effectiveAddressInDecimal += indexRegister.getDecimalValue();
        }

        if (indirect) {
            effectiveAddressInDecimal = Integer.valueOf(Memory.memoryMap.get(effectiveAddressInDecimal));
        }

        return effectiveAddressInDecimal;
    }

    private void storeIndexRegisterFromMemory(IndexRegister indexRegister, int effectiveAddressInDecimal) {
        Memory.memoryMap.put(effectiveAddressInDecimal, String.valueOf(indexRegister.getDecimalValue()));
    }

    private void loadIndexRegisterFromMemory(IndexRegister indexRegister, int effectiveAddressInDecimal) {
        indexRegister.setDecimalValue(Integer.valueOf(Memory.memoryMap.get(effectiveAddressInDecimal)));
    }

    private void loadRegisterWithAddress(GeneralPurposeRegister generalPurposeRegister, int effectiveAddressInDecimal) {
        generalPurposeRegister.setDecimalValue(effectiveAddressInDecimal);
    }

    private void storeRegisterToMemory(GeneralPurposeRegister generalPurposeRegister, int effectiveAddressInDecimal) {
        String value = String.valueOf(generalPurposeRegister.getDecimalValue());

        Memory.memoryMap.put(effectiveAddressInDecimal, value);
    }

    private void loadRegisterFromMemory(GeneralPurposeRegister generalPurposeRegister, int effectiveAddressInDecimal) {
        Integer value = Integer.valueOf(Memory.memoryMap.get(effectiveAddressInDecimal));

        generalPurposeRegister.setDecimalValue(value);
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
