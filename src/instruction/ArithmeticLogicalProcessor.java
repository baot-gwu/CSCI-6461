package instruction;

import common.CiscComputer;
import memory.Address;
import memory.Cache;
import register.GeneralPurposeRegister;
import register.IndexRegister;
import util.Utils;

/**
 * @author jalal
 * @since 12/9/19
 * <p>
 * Class to process Arithmetic Logical Processor
 * <p>
 * Based on instruction type. AMR, SMR, AIR, SIR
 */
public class ArithmeticLogicalProcessor implements InstructionProcessor {

    @Override
    public void process(CiscComputer ciscComputer, Instruction instruction) {
        Address address = AddressDecoder.decodeAddress(instruction);

        ciscComputer.getMemoryAddressRegister().setDecimalValue(address.getEffectiveAddress());

        GeneralPurposeRegister generalPurposeRegister = instruction.getGeneralPurposeRegister();
        IndexRegister indexRegister = instruction.getIndexRegister();

        switch (instruction.getType()) {
            case AMR:
                addMemoryToRegister(generalPurposeRegister, address);
                break;
            case SMR:
                subtractMemoryFromRegister(generalPurposeRegister, address);
                break;
            case AIR:
                addImmediateToRegister(generalPurposeRegister, address);
                break;
            case SIR:
                subtractImmediateFromRegister(generalPurposeRegister, address);
                break;
        }

        ciscComputer.getMemoryBufferRegister().setDecimalValue(Cache.getWordDecimalValue(address));
    }

    private void addImmediateToRegister(GeneralPurposeRegister generalPurposeRegister, Address address) {
        int immediate = address.getEffectiveAddress();

        if (immediate != 0) {
            Utils.validImmediate(immediate);

            int result = generalPurposeRegister.getDecimalValue() + immediate;

            generalPurposeRegister.setDecimalValue(result);
        }
    }

    private void subtractImmediateFromRegister(GeneralPurposeRegister generalPurposeRegister, Address address) {
        int immediate = address.getEffectiveAddress();

        if (immediate != 0) {
            Utils.validImmediate(immediate);

            int result = generalPurposeRegister.getDecimalValue() - immediate;

            generalPurposeRegister.setDecimalValue(result);
        }
    }

    private void addMemoryToRegister(GeneralPurposeRegister generalPurposeRegister, Address address) {
        int result = generalPurposeRegister.getDecimalValue() + Cache.getWordDecimalValue(address);

        generalPurposeRegister.setDecimalValue(result);
    }

    private void subtractMemoryFromRegister(GeneralPurposeRegister generalPurposeRegister, Address address) {
        int result = generalPurposeRegister.getDecimalValue() - Cache.getWordDecimalValue(address);

        generalPurposeRegister.setDecimalValue(result);
    }

}
