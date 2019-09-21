/**
 * @author jalal
 * @since 12/9/19
 *
 * Common utils to calculate and check common operations
 */
public class Utils {

    public static int binaryToDecimal(String binary) {
        return Integer.parseInt(binary, 2);
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
        return Integer.toBinaryString(decimal);
    }

    public static String decimalToHex(int decimal) {
        return Integer.toHexString(decimal).toUpperCase();
    }

    public static String binaryToHex(String binary) {
        return autoFill(Integer.toHexString(Integer.parseInt(binary, 2)).toUpperCase(), (binary.length() / 4));
    }

    public static String hexToBinary(String hex) {
        return autoFill(Integer.toBinaryString(Integer.parseInt(hex, 16)) , (hex.length() * 4));
    }

    public static String autoFill(String instruction, int size){
        if (instruction == null) instruction = "0";
        instruction.replace(" ","");
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

    public static String symbolicForm(Instruction instruction) {
        if (instruction == null) {
            return null;
        }

        GeneralPurposeRegister generalPurposeRegister = instruction.getGeneralPurposeRegister();
        IndexRegister indexRegister = instruction.getIndexRegister();

        String symbolicForm = instruction.getType().name() + " "
                + (generalPurposeRegister == null ? "" : generalPurposeRegister.getRegisterNumber() + ",")
                + (indexRegister == null ? "0" : indexRegister.getRegisterNumber()) +
                "," + instruction.getEffectiveAddressInDecimal();

        if (instruction.isIndirect()) {
            symbolicForm += ",1";
        }

        return symbolicForm;
    }

    public static boolean isValidBinaryInstruction(String instruction) {
        return instruction != null && instruction.matches("[0-1]+");
    }

    public static boolean isValidOctalInstruction(String instruction) {
        return instruction != null && instruction.matches("[0-7]+");
    }
}
