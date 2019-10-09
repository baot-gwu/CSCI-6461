package test.java.util;

import main.java.util.Utils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtilsTest {

    @Test
    public void testDecimalToUnsignedBinary() {
        assertEquals(Utils.decimalToUnsignedBinary(0), "0000000000000000");
        assertEquals(Utils.decimalToUnsignedBinary(Short.MAX_VALUE), "0111111111111111");
        assertEquals(Utils.decimalToUnsignedBinary(-Short.MAX_VALUE), "1000000000000001");
        assertEquals(Utils.decimalToUnsignedBinary(Short.MIN_VALUE), "1000000000000000");
    }

    @Test
    public void testUnsignedBinaryToDecimal() {
        assertEquals(Utils.unsignedBinaryToDecimal("0000000000000000"), 0);
        assertEquals(Utils.unsignedBinaryToDecimal("0111111111111111"), Short.MAX_VALUE);
        assertEquals(Utils.unsignedBinaryToDecimal("1000000000000001"), -Short.MAX_VALUE);
        assertEquals(Utils.unsignedBinaryToDecimal("1000000000000000"), Short.MIN_VALUE);
    }
}
