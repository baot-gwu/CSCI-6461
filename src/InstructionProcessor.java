/**
 * @author jalal
 * @since 12/9/19
 */
public class InstructionProcessor {

    protected void processInstruction(CiscComputer ciscComputer, Instruction instruction) {

        GeneralPurposeRegister generalPurposeRegister = instruction.getGeneralPurposeRegister();
        IndexRegister indexRegister = instruction.getIndexRegister();
        InstructionType type = instruction.getType();

        int effectiveAddressInDecimal = calculateEffectiveAddress(generalPurposeRegister, indexRegister, type,
                instruction.getEffectiveAddressInDecimal(), instruction.isIndirect());

        ciscComputer.getMemoryAddressRegister().setDecimalValue(effectiveAddressInDecimal);

        switch (instruction.getType()) {
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

}
