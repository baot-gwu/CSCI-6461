package main.java.util;

import main.java.instruction.InstructionType;
import main.java.memory.Word;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static main.java.instruction.InstructionType.*;

/**
 * @author jalal
 * @since 12/9/19
 * <p>
 * Common utils to calculate and check common operations
 */
public class Utils {

    public static final int MFR_ID_ILLEGAL_MEMORY_ADDRESS_RESERVED_LOCATION = 0;
    public static final int MFR_ID_ILLEGAL_TRAP_CODE = 1;
    public static final int MFR_ID_ILLEGAL_OPCODE = 2;
    public static final int MFR_ID_ILLEGAL_MEMORY_ADDRESS_BEYOND_SIZE = 3;

    private static final List<InstructionType> instructionWithNoIndexRegister = Arrays.asList(
            InstructionType.MLT, InstructionType.DVD, InstructionType.TRR, InstructionType.AND, InstructionType.ORR,
            InstructionType.NOT, InstructionType.SRC, InstructionType.RRC, InstructionType.IN, InstructionType.OUT,
            InstructionType.CHK, InstructionType.TRAP);

    private static final List<InstructionType> floatingPointInstructions = Arrays.asList(
            InstructionType.FADD, InstructionType.FSUB, InstructionType.CNVRT, InstructionType.LDFR, InstructionType.STFR);

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

    public static boolean numberValid(String value) {
        String pattern = "^[0-9]+$";
        return java.util.regex.Pattern.compile(pattern).matcher(value).matches();
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
        return InstructionType.NOT != type && !isShiftOrRotateInstruction(type) && !isIoInstruction(type) && InstructionType.TRAP != type;
    }

    public static boolean isShiftOrRotateInstruction(InstructionType type) {
        return (InstructionType.SRC == type || InstructionType.RRC == type);
    }

    public static boolean isIoInstruction(InstructionType type) {
        return (InstructionType.IN == type || InstructionType.OUT == type || InstructionType.CHK == type);
    }

    public static boolean isValidMultiplicationOrDivisionRegister(int registerNumber) {
        return (registerNumber == 0 || registerNumber == 2);
    }

    public static int increment(int decimal) {
        return decimal + 1;
    }

    public static String increment(String binary) {
        return decimalToUnsignedBinary(increment(unsignedBinaryToDecimal(binary)));
    }

    public static int[] stringToIntegerArray(String str, String regex) {
        String[] temp = str.split(regex);
        int[] array = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            array[i] = Integer.parseInt(temp[i].trim());
        }
        return array;
    }

    public static String arrayToString(Object[] arr) {
        return StringUtils.join(arr, ",");
    }

    public static String arrayToStringParagraph(String[] arr) {
        return StringUtils.join(arr, "\n");
    }

    public static String trimBinaryValue(String binaryValue, int size) {
        int length = binaryValue.length();

        if (length > size) {
            binaryValue = binaryValue.substring(length - size, length);
        }

        return binaryValue;
    }

    public static boolean isFloatingPointInstruction(InstructionType type) {
        return floatingPointInstructions.contains(type);
    }

    public static BigDecimal binaryToFloatingPoint(String value) {
        int exponent = binaryToDecimal(value.substring(2, 8));
        int mantissa = binaryToDecimal(value.substring(8, 16));

        BigDecimal fpValue = new BigDecimal(mantissa + "E" + (value.charAt(1) == '1' ? "-" : "+") + exponent);

        if (value.charAt(0) == '1') {
            fpValue = fpValue.negate();
        }

        return fpValue;
    }

    public static String floatingPointToBinary(BigDecimal value) {
        String[] engStr = value.toEngineeringString().split("E");
        String mantissa = engStr[0];
        int exponant = 0;

        if (mantissa.contains(".")) {
            exponant = mantissa.indexOf('.') - mantissa.length() + 1;
            mantissa = mantissa.replace(".", "");
        }

        mantissa = trimBinaryValue(decimalToUnsignedBinary(Math.abs(Integer.parseInt(mantissa))), 8);

        if (engStr.length == 2) {
            exponant = Integer.parseInt(engStr[1]);
        }

        String expStr = trimBinaryValue(decimalToUnsignedBinary(Math.abs(exponant)), 6);

        expStr = ((exponant < 0) ? "1" : "0") + expStr;

        if (value.compareTo(BigDecimal.ZERO) < 0) {
            return ("1" + expStr + mantissa);
        }

        return ("0" + expStr + mantissa);
    }

    public static int getMantissa(BigDecimal value) {
        return value.unscaledValue().abs().intValue();
    }

    public static String binaryInstruction(String symbolicForm) {
        String[] parts = symbolicForm.split("[ ,]");
        InstructionType type = InstructionType.valueOf(parts[0]);
        String binaryInstruction = type.getOpcodeInBinary();

        int r = 0;
        int x = 0;
        int address = 0;
        int i = 0;
        int al = 0;
        int count = 0;
        int lr = 0;

        if (Arrays.asList(LDR, STR, LDA, JZ, JNE, SOB, JGE, AMR, SMR, JCC).contains(type)) {
            r = Integer.parseInt(parts[1]);
            x = Integer.parseInt(parts[2]);
            if (parts.length == 5) {
                i = Integer.parseInt(parts[4]);
            }
            address = Integer.parseInt(parts[3]);
        } else if (Arrays.asList(LDX, STX, JMA, JSR).contains(type)) {
            x = Integer.parseInt(parts[1]);
            if (parts.length == 4) {
                i = Integer.parseInt(parts[3]);
            }
            address = Integer.parseInt(parts[2]);
        } else if (Arrays.asList(IN, OUT, CHK).contains(type)) {
            r = Integer.parseInt(parts[1]);
            address = Integer.parseInt(parts[2]);
        } else if (Arrays.asList(SRC, RRC).contains(type)) {
            r = Integer.parseInt(parts[1]);
            al = Integer.parseInt(parts[2]);
            lr = Integer.parseInt(parts[3]);
            count = Integer.parseInt(parts[4]);

            return binaryInstruction + trimBinaryValue(decimalToUnsignedBinary(r), 2)
                    + trimBinaryValue(decimalToUnsignedBinary(al), 1)
                    + trimBinaryValue(decimalToUnsignedBinary(lr), 1) + "00"
                    + trimBinaryValue(decimalToUnsignedBinary(count), 4);
        } else if (Arrays.asList(MLT, DVD, TRR, AND, ORR, NOT).contains(type)) {
            return binaryInstruction + trimBinaryValue(decimalToUnsignedBinary(Integer.parseInt(parts[1])), 2)
                    + (type == NOT ? "00" : trimBinaryValue(decimalToUnsignedBinary(Integer.parseInt(parts[2])), 2))
                    + "000000";
        } else if (Arrays.asList(AIR, SIR).contains(type)) {
            r = Integer.parseInt(parts[1]);
            address = Integer.parseInt(parts[2]);

            return binaryInstruction + trimBinaryValue(decimalToUnsignedBinary(r), 2)
                    + "000"
                    + trimBinaryValue(decimalToUnsignedBinary(address), 5);
        }

        return binaryInstruction + trimBinaryValue(decimalToUnsignedBinary(r), 2)
                + trimBinaryValue(decimalToUnsignedBinary(x), 2)
                + trimBinaryValue(decimalToUnsignedBinary(i), 1)
                + trimBinaryValue(decimalToUnsignedBinary(address), 5);
    }
}
