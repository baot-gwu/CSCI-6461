package main.java.util;

import main.java.instruction.InstructionType;
import main.java.memory.Word;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author jalal
 * @since 12/9/19
 * <p>
 * Common utils to calculate and check common operations
 */
public class Utils {

    private static final List<InstructionType> instructionWithNoIndexRegister = Arrays.asList(
            InstructionType.MLT, InstructionType.DVD, InstructionType.TRR, InstructionType.AND, InstructionType.ORR,
            InstructionType.NOT, InstructionType.SRC, InstructionType.RRC);

    public static int binaryToDecimal(String binary) {
        return Integer.parseInt(binary, 2);
    }

    public static int unsignedBinaryToDecimal(String binary) {
        String padStr = binary.charAt(0) == '0' ? "0" : "1";
        String fullLengthBinary = StringUtils.leftPad(binary, Word.MAX_SIZE * 2, padStr);

        return Integer.parseUnsignedInt(fullLengthBinary, 2);
    }

    public static String octalToBinary(String octal, int registerSize) {
        String binary = Integer.toBinaryString(Integer.parseInt(octal, 8));

        int prefixZeroCount = registerSize - binary.length();

        if (prefixZeroCount > 0) {
            binary = String.format("%0" + prefixZeroCount + "d", 0) + binary;
        }

        return binary;
    }

    public static String decimalToBinary(int decimal) {
        String result = Integer.toBinaryString(decimal);
        int length = result.length();

        if (length > Word.MAX_SIZE) {
            result = result.substring(length - Word.MAX_SIZE, length);
        }

        return result;
    }

    public static String decimalToUnsignedBinary(int value) {
        if (value < 0) {
            return Integer.toBinaryString(value & 0xFFFF);
        }

        return StringUtils.leftPad(Integer.toBinaryString(value), Word.MAX_SIZE, "0");
    }

    public static String decimalToHex(int decimal) {
        return Integer.toHexString(decimal).toUpperCase();
    }

    public static String binaryToHex(String binary) {
        return autoFill(Integer.toHexString(Integer.parseInt(binary, 2)).toUpperCase(), (binary.length() / 4));
    }

    public static String hexToBinary(String hex) {
        return autoFill(Integer.toBinaryString(Integer.parseInt(hex, 16)), (hex.length() * 4));
    }

    public static String autoFill(String instruction, int size) {
        if (instruction == null) instruction = "0";
        instruction.replace(" ", "");
        for (int i = instruction.length(); i < size; i++) {
            instruction = "0" + instruction;
        }

        if (instruction.length() > size) {
            instruction = instruction.substring(instruction.length() - size);
        }

        return instruction;
    }

    public static boolean binaryValid(String binary) {
        String pattern = "^[01]+$";
        return java.util.regex.Pattern.compile(pattern).matcher(binary).matches();
    }

    public static boolean hexValid(String hex) {
        String pattern = "^[0-9A-F]+$";
        return java.util.regex.Pattern.compile(pattern).matcher(hex).matches();
    }

    public static boolean isValidBinaryInstruction(String instruction) {
        return instruction != null && instruction.matches("[0-1]+");
    }

    public static boolean isValidOctalInstruction(String instruction) {
        return instruction != null && instruction.matches("[0-7]+");
    }

    public static void validImmediate(int value) {
        if (value > 32) {
            throw new IllegalArgumentException("Invalid Immediate Value: " + value);
        }
    }

    public static boolean hasIndexRegister(InstructionType type) {
        return !instructionWithNoIndexRegister.contains(type);
    }

    public static boolean hasSecondRegister(InstructionType type) {
        return InstructionType.NOT != type && !isShiftInstruction(type);
    }

    public static boolean isShiftInstruction(InstructionType type) {
        return (InstructionType.SRC == type || InstructionType.RRC == type);
    }

    public static boolean isValidMultiplicationOrDivisionRegister(int registerNumber) {
        return (registerNumber == 0 || registerNumber == 2);
    }
}
