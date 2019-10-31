package main.java.instruction;

import main.java.common.CiscComputer;
import main.java.memory.Address;
import main.java.memory.Cache;
import main.java.memory.Word;
import main.java.register.ConditionCodeType;
import main.java.register.FloatingPointRegister;
import main.java.register.Register;
import main.java.util.Utils;

import java.math.BigDecimal;

public class FloatingPointInstructionProcessor implements InstructionProcessor {

    private static InstructionProcessor processor;
    private static final int MAX_MANTISSA = (int) Math.pow(2, 8);
    private static final int MAX_EXPONENT = (int) Math.pow(2, 6);

    static InstructionProcessor getInstance() {
        if (processor == null) {
            processor = new FloatingPointInstructionProcessor();
        }

        return processor;
    }

    @Override
    public void process(CiscComputer ciscComputer, Instruction instruction) {
        Address address = AddressDecoder.decodeAddress(instruction, ciscComputer.getMachineFaultRegister());
        Register firstRegister = instruction.getFirstRegister();
        Register secondRegister = instruction.getSecondRegister();
        ciscComputer.getConditionCode().setConditionCodeType(null);
        ciscComputer.getMemoryAddressRegister().setDecimalValue(0);
        ciscComputer.getMemoryBufferRegister().setDecimalValue(0);

        switch (instruction.getType()) {
            case FADD:
                addMemoryToRegister((FloatingPointRegister) firstRegister, address, ciscComputer);
                break;
            case FSUB:
                subtractMemoryToRegister((FloatingPointRegister) firstRegister, address, ciscComputer);
                break;
            case LDFR:
                loadFloatingPointRegisterFromMemory((FloatingPointRegister) firstRegister, address);
                break;
            case STFR:
                storeFloatingPointRegisterToMemory((FloatingPointRegister) firstRegister, address);
                break;
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

        if (Utils.getMantissa(result) > MAX_MANTISSA) {
            ciscComputer.getConditionCode().setConditionCodeType(ConditionCodeType.OVERFLOW);
        } else if (result.scale() > MAX_EXPONENT) {
            ciscComputer.getConditionCode().setConditionCodeType(ConditionCodeType.OVERFLOW);
        }

        firstRegister.setFloatingPointValue(result);
    }

    private void addMemoryToRegister(FloatingPointRegister firstRegister, Address address, CiscComputer ciscComputer) {
        BigDecimal registerValue = Utils.binaryToFloatingPoint(firstRegister.getValue(true));
        BigDecimal memoryValue = Utils.binaryToFloatingPoint(Cache.getWordStringValue(address));

        BigDecimal result = registerValue.add(memoryValue);

        if (Utils.getMantissa(result) > MAX_MANTISSA) {
            ciscComputer.getConditionCode().setConditionCodeType(ConditionCodeType.OVERFLOW);
        } else if (result.scale() > MAX_EXPONENT) {
            ciscComputer.getConditionCode().setConditionCodeType(ConditionCodeType.OVERFLOW);
        }

        firstRegister.setFloatingPointValue(result);
    }
}