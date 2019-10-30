package main.java.program;

import main.java.common.CiscComputer;
import main.java.device.ConsoleKeyboard;
import main.java.device.ConsolePrinter;
import main.java.device.Device;
import main.java.instruction.Instruction;
import main.java.instruction.InstructionType;
import main.java.memory.Address;
import main.java.memory.Cache;
import main.java.register.GeneralPurposeRegister;
import main.java.util.Utils;

public class Program1 {

    private Instruction instruction;
    private int registerNumber = 0;

    public void inputAndStoreNumber(CiscComputer ciscComputer, int[] decimalNumbers, int memoryAddressStartPoint) {
        Device consoleKeyboard = new ConsoleKeyboard();
        ciscComputer.setDevice(consoleKeyboard);

        Device consolePrinter = new ConsolePrinter();
        ciscComputer.setDevice(consolePrinter);

        for (int i = 0; i < decimalNumbers.length; i++) {
            consoleKeyboard.setBinaryValue(Utils.decimalToUnsignedBinary(decimalNumbers[i]));

            inputValue(ciscComputer);

            storeValue(ciscComputer, i, memoryAddressStartPoint);

            outputValue(ciscComputer);
        }
    }

    private void inputValue(CiscComputer ciscComputer) {
        instruction = new Instruction(ciscComputer.getGeneralPurposeRegister(registerNumber), null, InstructionType.IN, null,
                false, false, false, null, 0, null);

        instruction.getType().getProcessor().process(ciscComputer, instruction);

        System.out.println(instruction.symbolicForm());
    }

    private void outputValue(CiscComputer ciscComputer) {
        instruction = new Instruction(ciscComputer.getGeneralPurposeRegister(registerNumber), null, InstructionType.OUT, null,
                null, null, null, null, 1, null);

        instruction.getType().getProcessor().process(ciscComputer, instruction);

        System.out.println(instruction.symbolicForm());
    }

    private void storeValue(CiscComputer ciscComputer, int addressCount, int memoryAddressStartPoint) {
        instruction = new Instruction(ciscComputer.getGeneralPurposeRegister(registerNumber), null, InstructionType.STR, memoryAddressStartPoint + addressCount,
                false, false, false, null, null, null);

        instruction.getType().getProcessor().process(ciscComputer, instruction);

        System.out.println(instruction.symbolicForm());
    }

    public int findClosestNumber(CiscComputer ciscComputer, int numberToBeComparedWith, int totalNumbers, int memoryAddressStartPoint) {
        int minDiff = Integer.MAX_VALUE;
        int closestNumberAddress = 0;

        for (int i = 0; i < totalNumbers; i++) {
            int diff = findDifference(ciscComputer, numberToBeComparedWith, memoryAddressStartPoint + i);

            if (diff < minDiff) {
                minDiff = diff;
                closestNumberAddress = memoryAddressStartPoint + i;
            }
        }

        return Cache.getWordDecimalValue(new Address(closestNumberAddress));
    }

    public int findDifference(CiscComputer ciscComputer, int numberToBeComparedWith, int address) {
        Device consoleKeyboard = new ConsoleKeyboard();
        consoleKeyboard.setBinaryValue(Utils.decimalToUnsignedBinary(numberToBeComparedWith));
        ciscComputer.setDevice(consoleKeyboard);
        inputValue(ciscComputer);

        GeneralPurposeRegister generalPurposeRegister = ciscComputer.getGeneralPurposeRegister(registerNumber);
        instruction = new Instruction(generalPurposeRegister, null, InstructionType.SMR, address, null, null, null, null, null, null);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        System.out.println(instruction.symbolicForm());

        return Math.abs(generalPurposeRegister.getDecimalValue());
    }

}
