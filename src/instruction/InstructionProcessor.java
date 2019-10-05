package instruction;

import common.CiscComputer;
import memory.Address;
import memory.Cache;
import memory.Word;
import register.GeneralPurposeRegister;
import register.IndexRegister;

/**
 * @author jalal
 * @since 12/9/19
 * <p>
 * This class process and instruction.
 * <p>
 * Based on instruction type. LDR, STR, LDA, LDX, STX
 */
public class InstructionProcessor {

    public void processInstruction(CiscComputer ciscComputer, Instruction instruction) {

        GeneralPurposeRegister generalPurposeRegister = instruction.getGeneralPurposeRegister();
        IndexRegister indexRegister = instruction.getIndexRegister();
        InstructionType type = instruction.getType();

        int effectiveAddressInDecimal = calculateEffectiveAddress(generalPurposeRegister, indexRegister, type,
                instruction.getEffectiveAddressInDecimal(), instruction.isIndirect());
        Address address = new Address(effectiveAddressInDecimal);

        ciscComputer.getMemoryAddressRegister().setDecimalValue(address.getEffectiveAddress());

        switch (instruction.getType()) {
            case LDR:
                loadRegisterFromMemory(generalPurposeRegister, address);
                break;
            case STR:
                storeRegisterToMemory(generalPurposeRegister, address);
                break;
            case LDA:
                loadRegisterWithAddress(generalPurposeRegister, effectiveAddressInDecimal);
                break;
            case LDX:
                loadIndexRegisterFromMemory(indexRegister, address);
                break;
            case STX:
                storeIndexRegisterFromMemory(indexRegister, address);
                break;
        }

        ciscComputer.getMemoryBufferRegister().setDecimalValue(Cache.getWordDecimalValue(address));
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
            effectiveAddressInDecimal = Cache.getWordDecimalValue(new Address(effectiveAddressInDecimal));
        }

        return effectiveAddressInDecimal;
    }

    private void storeIndexRegisterFromMemory(IndexRegister indexRegister, Address address) {
        Cache.writeToMemory(address, new Word(indexRegister.getDecimalValue()));
    }

    private void loadIndexRegisterFromMemory(IndexRegister indexRegister, Address address) {
        indexRegister.setDecimalValue(Cache.getWordDecimalValue(address));
    }

    private void loadRegisterWithAddress(GeneralPurposeRegister generalPurposeRegister, int effectiveAddressInDecimal) {
        generalPurposeRegister.setDecimalValue(effectiveAddressInDecimal);
    }

    private void storeRegisterToMemory(GeneralPurposeRegister generalPurposeRegister, Address address) {
        Cache.writeToMemory(address, new Word(generalPurposeRegister.getDecimalValue()));
    }

    private void loadRegisterFromMemory(GeneralPurposeRegister generalPurposeRegister, Address address) {
        generalPurposeRegister.setDecimalValue(Cache.getWordDecimalValue(address));
    }

}
