package main.java.memory;

/**
 * @author jalal
 * @since 29/9/19
 *
 * Word
 */
public class Word {

    public static final int MAX_SIZE = 16;

    private String value;

    public Word(String value) {
        if (value != null && value.length() > MAX_SIZE) {
            throw new IllegalArgumentException("Invalid word " + value);
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
