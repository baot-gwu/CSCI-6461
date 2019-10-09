package main.java.instruction;

import main.java.common.CiscComputer;
import main.java.memory.Address;
import main.java.memory.Cache;
import main.java.memory.Word;
import main.java.register.Register;

/**
 * @author jalal
 * @since 12/9/19
 * <p>
 * Class to process Load Store operations
 * <p>
 * Based on instruction type. LDR, STR, LDA, LDX, STX
 */
public class LoadStoreInstructionProcessor implements InstructionProcessor {

    private static InstructionProcessor processor;

    static InstructionProcessor getInstance() {
        if (processor == null) {
            processor = new LoadStoreInstructionProcessor();
        }

        return processor;
    }

    @Override
    public void process(CiscComputer ciscComputer, Instruction instruction) {
        Address address = AddressDecoder.decodeAddress(instruction);

        ciscComputer.getMemoryAddressRegister().setDecimalValue(address.getEffectiveAddress());

        Register generalPurposeRegister = instruction.getFirstRegister();
        Register indexRegister = instruction.getSecondRegister();

        switch (instruction.getType()) {
            case LDR:
                loadRegisterFromMemory(generalPurposeRegister, address);
                break;
            case STR:
                storeRegisterToMemory(generalPurposeRegister, address);
                break;
            case LDA:
                loadRegisterWithAddress(generalPurposeRegister, address);
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

    private void storeIndexRegisterFromMemory(Register indexRegister, Address address) {
        Cache.writeToMemory(address, new Word(indexRegister.getValue(true)));
    }

    private void loadIndexRegisterFromMemory(Register indexRegister, Address address) {
        indexRegister.setDecimalValue(Cache.getWordDecimalValue(address));
    }

    private void loadRegisterWithAddress(Register generalPurposeRegister, Address address) {
        generalPurposeRegister.setDecimalValue(address.getEffectiveAddress());
    }

    private void storeRegisterToMemory(Register generalPurposeRegister, Address address) {
        Cache.writeToMemory(address, new Word(generalPurposeRegister.getValue(true)));
    }

    private void loadRegisterFromMemory(Register generalPurposeRegister, Address address) {
        generalPurposeRegister.setDecimalValue(Cache.getWordDecimalValue(address));
    }
}
