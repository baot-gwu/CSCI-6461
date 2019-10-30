package main.java.instruction;

import main.java.common.CiscComputer;
import main.java.memory.Address;
import main.java.memory.Cache;
import main.java.memory.Word;
import main.java.register.ConditionCode;
import main.java.register.ConditionCodeType;
import main.java.register.Register;
import main.java.util.Utils;

import java.math.BigInteger;

/**
 * @author jalal
 * @since 12/9/19
 * <p>
 * Class to process Arithmetic Logical Processor
 * <p>
 * Based on instruction type. AMR, SMR, AIR, SIR
 */
public class ArithmeticLogicalInstructionProcessor implements InstructionProcessor {

    private static final BigInteger MAX_INT_VALUE = BigInteger.valueOf((long) Math.pow(2, 16));

    private static InstructionProcessor processor;

    static InstructionProcessor getInstance() {
        if (processor == null) {
            processor = new ArithmeticLogicalInstructionProcessor();
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
            case AMR:
                addMemoryToRegister(firstRegister, address);
                break;
            case SMR:
                subtractMemoryFromRegister(firstRegister, address);
                break;
            case AIR:
                addImmediateToRegister(firstRegister, address);
                break;
            case SIR:
                subtractImmediateFromRegister(firstRegister, address);
                break;
            case MLT:
                multiplyRegisterByRegister(firstRegister, secondRegister, ciscComputer);
                break;
            case DVD:
                divideRegisterByRegister(firstRegister, secondRegister, ciscComputer);
                break;
            case TRR:
                testEqualityOfRegisterAndRegister(firstRegister, secondRegister, ciscComputer);
                break;
            case AND:
                logicalAndOfRegisterAndRegister(firstRegister, secondRegister);
                break;
            case ORR:
                logicalOrOfRegisterAndRegister(firstRegister, secondRegister);
                break;
            case NOT:
                logicalNotOfRegisterAndRegister(firstRegister);
                break;
            case SRC:
                shiftRegisterByCount(firstRegister, instruction);
                break;
            case RRC:
                rotateRegisterByCount(firstRegister, instruction);
                break;
        }
    }

    private void rotateRegisterByCount(Register register, Instruction instruction) {
        String binaryValue = register.getValue(true);
        int count = instruction.getCount();

        for (int i = 0; i < count; i++) {
            if (instruction.isLeft()) {
                binaryValue = binaryValue.substring(1, Word.MAX_SIZE) + binaryValue.charAt(0);
            } else {
                binaryValue = binaryValue.charAt(Word.MAX_SIZE - 1) + binaryValue.substring(0, Word.MAX_SIZE - 1);
            }
        }

        register.setBinaryValue(binaryValue);
    }

    private void shiftRegisterByCount(Register register, Instruction instruction) {
        String binaryValue = register.getValue(true);
        int count = instruction.getCount();

        if (instruction.isLeft()) {
            binaryValue = shiftLeft(binaryValue, count);
        } else {
            if (instruction.isArithmetic()) {
                binaryValue = shiftRight(binaryValue, count, true);
            } else {
                binaryValue = shiftRight(binaryValue, count, false);
            }
        }

        register.setBinaryValue(binaryValue);
    }

    private void logicalNotOfRegisterAndRegister(Register register) {
        int contentOfFirstRegister = register.getDecimalValue();

        register.setDecimalValue(~contentOfFirstRegister);
    }

    private void logicalOrOfRegisterAndRegister(Register firstRegister, Register secondRegister) {
        int contentOfFirstRegister = firstRegister.getDecimalValue();
        int contentOfSecondRegister = secondRegister.getDecimalValue();

        firstRegister.setDecimalValue(contentOfFirstRegister | contentOfSecondRegister);
    }

    private void logicalAndOfRegisterAndRegister(Register firstRegister, Register secondRegister) {
        int contentOfFirstRegister = firstRegister.getDecimalValue();
        int contentOfSecondRegister = secondRegister.getDecimalValue();

        firstRegister.setDecimalValue(contentOfFirstRegister & contentOfSecondRegister);
    }

    private void testEqualityOfRegisterAndRegister(Register firstRegister, Register secondRegister, CiscComputer ciscComputer) {
        int contentOfFirstRegister = firstRegister.getDecimalValue();
        int contentOfSecondRegister = secondRegister.getDecimalValue();

        ConditionCode conditionCode = ciscComputer.getConditionCode();
        conditionCode.setConditionCodeType(ConditionCodeType.EQUALORNOT);

        if (contentOfFirstRegister == contentOfSecondRegister) {
            conditionCode.setDecimalValue(1);
        } else {
            conditionCode.setDecimalValue(0);
        }

        ciscComputer.setConditionCode(conditionCode);
    }

    private void divideRegisterByRegister(Register firstRegister, Register secondRegister, CiscComputer ciscComputer) {
        int registerNumber = firstRegister.getRegisterNumber();
        checkRegisterValidityForMulDvd(registerNumber);
        checkRegisterValidityForMulDvd(secondRegister.getRegisterNumber());

        int contentOfFirstRegister = firstRegister.getDecimalValue();
        int contentOfSecondRegister = secondRegister.getDecimalValue();

        if (contentOfSecondRegister == 0) {
            ciscComputer.getConditionCode().setConditionCodeType(ConditionCodeType.DIVZERO);
        } else {
            firstRegister.setDecimalValue(contentOfFirstRegister / contentOfSecondRegister);
            ciscComputer.getGeneralPurposeRegister(registerNumber + 1).setDecimalValue(contentOfFirstRegister % contentOfSecondRegister);
        }
    }

    private void multiplyRegisterByRegister(Register firstRegister, Register secondRegister, CiscComputer ciscComputer) {
        int registerNumber = firstRegister.getRegisterNumber();
        checkRegisterValidityForMulDvd(registerNumber);
        checkRegisterValidityForMulDvd(secondRegister.getRegisterNumber());

        BigInteger contentOfFirstRegister = BigInteger.valueOf(firstRegister.getDecimalValue());
        BigInteger contentOfSecondRegister = BigInteger.valueOf(secondRegister.getDecimalValue());

        BigInteger product = contentOfFirstRegister.multiply(contentOfSecondRegister);

        if (product.abs().compareTo(MAX_INT_VALUE) > 0) {
            ciscComputer.getConditionCode().setConditionCodeType(ConditionCodeType.OVERFLOW);
        }

        firstRegister.setDecimalValue(product.divide(MAX_INT_VALUE).intValue());
        ciscComputer.getGeneralPurposeRegister(registerNumber + 1).setDecimalValue(product.mod(MAX_INT_VALUE).intValue());
    }

    private void addImmediateToRegister(Register generalPurposeRegister, Address address) {
        int immediate = address.getEffectiveAddress();

        if (immediate != 0) {
            Utils.validImmediate(immediate);

            int result = generalPurposeRegister.getDecimalValue() + immediate;

            generalPurposeRegister.setDecimalValue(result);
        }
    }

    private void subtractImmediateFromRegister(Register generalPurposeRegister, Address address) {
        int immediate = address.getEffectiveAddress();

        if (immediate != 0) {
            Utils.validImmediate(immediate);

            int result = generalPurposeRegister.getDecimalValue() - immediate;

            generalPurposeRegister.setDecimalValue(result);
        }
    }

    private void addMemoryToRegister(Register generalPurposeRegister, Address address) {
        int result = generalPurposeRegister.getDecimalValue() + Cache.getWordDecimalValue(address);

        generalPurposeRegister.setDecimalValue(result);
    }

    private void subtractMemoryFromRegister(Register generalPurposeRegister, Address address) {

        int result = generalPurposeRegister.getDecimalValue() - Cache.getWordDecimalValue(address);

        generalPurposeRegister.setDecimalValue(result);
    }

    private void checkRegisterValidityForMulDvd(int registerNumber) {
        if (!Utils.isValidMultiplicationOrDivisionRegister(registerNumber)) {
            throw new IllegalArgumentException("Not valid register Number for MUL or DVD: " + registerNumber);
        }
    }

    private String shiftRight(String value, int count, boolean arithmetic) {
        char firstChar;

        for (int i = 0; i < count; i++) {
            if (arithmetic) {
                firstChar = value.charAt(0);
                char lastChar = value.charAt(Word.MAX_SIZE - 1);

                value = firstChar + "" + lastChar + value.substring(1, Word.MAX_SIZE - 1);
            } else {
                firstChar = '0';
                value = firstChar + value.substring(0, Word.MAX_SIZE - 1);
            }
        }

        return value;
    }

    private String shiftLeft(String value, int count) {
        for (int i = 0; i < count; i++) {
            value = value.substring(1, Word.MAX_SIZE) + '0';
        }

        return value;
    }

}
