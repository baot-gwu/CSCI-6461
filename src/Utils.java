/**
 * @author jalal
 * @since 12/9/19
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

    public static String symbolicForm(Instruction instruction) {
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
}
