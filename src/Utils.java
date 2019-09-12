/**
 * @author jalal
 * @since 12/9/19
 */
public class Utils {

    public static int binaryToDecimal(String binary) {
        return Integer.parseInt(binary, 2);
    }

    public static String octalToBinary(String octal) {
        return Integer.toBinaryString(Integer.parseInt(octal, 8));
    }

}
