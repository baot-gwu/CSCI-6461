package instruction;

import common.CiscComputer;
import memory.Address;
import memory.Cache;
import register.ConditionCode;
import register.ConditionCodeType;
import register.Register;
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

    private static final int MAX_INT_VALUE = (int) Math.pow(2, 15); // One bit for sign

    @Override
    public void process(CiscComputer ciscComputer, Instruction instruction) {
        Address address = AddressDecoder.decodeAddress(instruction);
        Register firstRegister = instruction.getFirstRegister();
        Register secondRegister = instruction.getSecondRegister();
        ciscComputer.getConditionCode().setConditionCodeType(null);

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
        }
    }

    private void logicalNotOfRegisterAndRegister(Register firstRegister) {
        int contentOfFirstRegister = firstRegister.getDecimalValue();

        firstRegister.setDecimalValue(~contentOfFirstRegister);
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
        int contentOfFirstRegister = firstRegister.getDecimalValue();
        int contentOfSecondRegister = secondRegister.getDecimalValue();

        if (contentOfSecondRegister == 0) {
            ciscComputer.getConditionCode().setConditionCodeType(ConditionCodeType.DIVZERO);
        }

        firstRegister.setDecimalValue(contentOfFirstRegister / contentOfSecondRegister);
        secondRegister.setDecimalValue(contentOfFirstRegister % contentOfSecondRegister);
    }

    private void multiplyRegisterByRegister(Register firstRegister, Register secondRegister, CiscComputer ciscComputer) {
        int contentOfFirstRegister = firstRegister.getDecimalValue();
        int contentOfSecondRegister = secondRegister.getDecimalValue();

        int product = contentOfFirstRegister * contentOfSecondRegister;

        if (Math.abs(product) > MAX_INT_VALUE) {
            ciscComputer.getConditionCode().setConditionCodeType(ConditionCodeType.OVERFLOW);
        }

        firstRegister.setDecimalValue(product / MAX_INT_VALUE);
        secondRegister.setDecimalValue(product % MAX_INT_VALUE);
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

}
