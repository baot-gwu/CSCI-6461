package main.java.memory;

import main.java.util.Utils;

/**
 * @author jalal
 * @since 29/9/19
 *
 * Word holds value of memory of each address.
 * Eight words in kept in each cache line.
 * All the values of the word is binary in both Memory and Cache line
 */
public class Word {

    public static final int MAX_SIZE = 16;

    private String value;

    public Word(String value) {
        if (value != null && value.length() > 0) {
            if (value.length() > MAX_SIZE) {
                value = value.substring(0, MAX_SIZE);
            }

            if (!Utils.binaryValid(value)) {
                throw new IllegalArgumentException("Invalid Word:" + value);
            }
        }

        this.value = value;
    }

    public Word(int value) {
        this.value = String.valueOf(value);
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return value;
    }
}
