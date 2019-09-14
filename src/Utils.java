/**
 * @author jalal
 * @since 12/9/19
 */
public class Utils {

    public static int binaryToDecimal(String binary) {
        return Integer.parseInt(binary, 2);
    }

    public static String octalToBinary(String octal) {
        String binary = Integer.toBinaryString(Integer.parseInt(octal, 8));

        int prefixZeroCount = Register.SIZE - binary.length();

        if (prefixZeroCount > 0) {
            binary = String.format("%0" + prefixZeroCount + "d", 0) + binary;
        }

        return binary;
    }

    public static String decimalToBinary(int decimal) {
        return Integer.toBinaryString(decimal);
    }
}
