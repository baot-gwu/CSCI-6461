package test.java.util;

import main.java.util.Utils;
import org.junit.Test;

import java.math.BigDecimal;

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

    @Test
    public void testFloatingPointValue() {
        assertEquals(Utils.binaryToFloatingPoint("0000000011011011"), BigDecimal.valueOf(219));
        assertEquals(Utils.binaryToFloatingPoint("0010110111011011"), BigDecimal.valueOf(2.19E47));
        assertEquals(Utils.binaryToFloatingPoint("1010110111011011"), BigDecimal.valueOf(-2.19E47));
        assertEquals(Utils.binaryToFloatingPoint("0110110111011011"), BigDecimal.valueOf(2.19E-43));
        assertEquals(Utils.binaryToFloatingPoint("1110110111011011"), BigDecimal.valueOf(-2.19E-43));
        assertEquals(Utils.binaryToFloatingPoint("0100000111010111"), BigDecimal.valueOf(21.5));
    }

    @Test
    public void floatingPointToBinary() {
        assertEquals(Utils.floatingPointToBinary(BigDecimal.valueOf(21.5)), "0100000111010111");
        assertEquals(Utils.floatingPointToBinary(BigDecimal.valueOf(219)), "0000000011011011");
        assertEquals(Utils.floatingPointToBinary(BigDecimal.valueOf(2.19E47)), "0010110111011011");
        assertEquals(Utils.floatingPointToBinary(BigDecimal.valueOf(-2.19E47)), "1010110111011011");
    }
}
