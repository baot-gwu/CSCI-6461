package main.java.instruction;

import main.java.common.CiscComputer;
import main.java.memory.Address;
import main.java.memory.Cache;
import main.java.memory.Word;
import main.java.register.ConditionCodeType;
import main.java.register.FloatingPointRegister;
import main.java.register.GeneralPurposeRegister;
import main.java.register.Register;
import main.java.util.Utils;

import java.math.BigDecimal;

/**
 * All the floating point and vector instructions are executed through this class
 */
public class FloatingPointVectorInstructionProcessor implements InstructionProcessor {

    private static InstructionProcessor processor;
    private static final int MAX_MANTISSA = (int) Math.pow(2, 8);
    private static final int MAX_EXPONENT = (int) Math.pow(2, 6);

    static InstructionProcessor getInstance() {
        if (processor == null) {
            processor = new FloatingPointVectorInstructionProcessor();
        }

        return processor;
    }

    @Override
    public void process(CiscComputer ciscComputer, Instruction instruction) {
        Address address = AddressDecoder.decodeAddress(instruction, ciscComputer.getMachineFaultRegister());
        Register firstRegister = instruction.getFirstRegister();

        switch (instruction.getType()) {
            case FADD:
                addMemoryToRegister((FloatingPointRegister) firstRegister, address, ciscComputer);
                break;
            case FSUB:
                subtractMemoryToRegister((FloatingPointRegister) firstRegister, address, ciscComputer);
                break;
            case VADD:
                vectorAdd((FloatingPointRegister) firstRegister, address);
                break;
            case VSUB:
                vectorSubtract((FloatingPointRegister) firstRegister, address);
                break;
            case CNVRT:
                convertToFixedOrFloatingPoint((GeneralPurposeRegister) firstRegister, address, ciscComputer);
                break;
            case LDFR:
                loadFloatingPointRegisterFromMemory((FloatingPointRegister) firstRegister, address);
                break;
            case STFR:
                storeFloatingPointRegisterToMemory((FloatingPointRegister) firstRegister, address);
                break;
        }
    }

    private void convertToFixedOrFloatingPoint(GeneralPurposeRegister firstRegister, Address address, CiscComputer ciscComputer) {
        int registerValue = firstRegister.getDecimalValue();
        String value = Cache.getWordStringValue(address);

        if (registerValue == 0) {
            firstRegister.setDecimalValue(Utils.unsignedBinaryToDecimal(value));
        } else {
            ciscComputer.getFloatingPointRegister(0).setFloatingPointValue(Utils.binaryToFloatingPoint(value));
        }
    }

    private void vectorSubtract(FloatingPointRegister firstRegister, Address address) {
        BigDecimal registerValue = Utils.binaryToFloatingPoint(firstRegister.getValue(true));

        int startingAddressOfFirstVector = Cache.getWordDecimalValue(address);
        int startingAddressOfSecondVector = Cache.getWordDecimalValue(new Address(address.getEffectiveAddress() + 1));

        for (int i = 0; i < registerValue.intValue(); i++) {
            Address addressOfFirstVector = new Address(startingAddressOfFirstVector + i);
            int subtract = Cache.getWordDecimalValue(addressOfFirstVector) - Cache.getWordDecimalValue(new Address(startingAddressOfSecondVector + i));

            Cache.writeToMemory(addressOfFirstVector, new Word(Utils.decimalToUnsignedBinary(subtract)));
        }
    }

    private void vectorAdd(FloatingPointRegister firstRegister, Address address) {
        BigDecimal registerValue = Utils.binaryToFloatingPoint(firstRegister.getValue(true));

        int startingAddressOfFirstVector = Cache.getWordDecimalValue(address);
        int startingAddressOfSecondVector = Cache.getWordDecimalValue(new Address(address.getEffectiveAddress() + 1));

        for (int i = 0; i < registerValue.intValue(); i++) {
            Address addressOfFirstVector = new Address(startingAddressOfFirstVector + i);
            int sum = Cache.getWordDecimalValue(addressOfFirstVector) + Cache.getWordDecimalValue(new Address(startingAddressOfSecondVector + i));

            Cache.writeToMemory(addressOfFirstVector, new Word(Utils.decimalToUnsignedBinary(sum)));
        }
    }

    private void storeFloatingPointRegisterToMemory(FloatingPointRegister firstRegister, Address address) {
        Cache.writeToMemory(address, new Word(firstRegister.getValue(true)));
    }

    private void loadFloatingPointRegisterFromMemory(FloatingPointRegister firstRegister, Address address) {
        firstRegister.setBinaryValue(Cache.getWordStringValue(address));
    }

    private void subtractMemoryToRegister(FloatingPointRegister firstRegister, Address address, CiscComputer ciscComputer) {
        BigDecimal registerValue = Utils.binaryToFloatingPoint(firstRegister.getValue(true));
        BigDecimal memoryValue = Utils.binaryToFloatingPoint(Cache.getWordStringValue(address));

        BigDecimal result = registerValue.subtract(memoryValue);

        if (Utils.isOverflow(result)) {
            ciscComputer.getConditionCode().setConditionCodeType(ConditionCodeType.OVERFLOW);
        }

        firstRegister.setFloatingPointValue(result);
    }

    private void addMemoryToRegister(FloatingPointRegister firstRegister, Address address, CiscComputer ciscComputer) {
        BigDecimal registerValue = Utils.binaryToFloatingPoint(firstRegister.getValue(true));
        BigDecimal memoryValue = Utils.binaryToFloatingPoint(Cache.getWordStringValue(address));

        BigDecimal result = registerValue.add(memoryValue);

        if (Utils.isOverflow(result)) {
            ciscComputer.getConditionCode().setConditionCodeType(ConditionCodeType.OVERFLOW);
        }

        firstRegister.setFloatingPointValue(result);
    }
}