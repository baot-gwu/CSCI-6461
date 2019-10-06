package main.java.register;

/**
 * @author jalal
 * @since 12/9/19
 *
 * Condition Code Type
 */
public enum ConditionCodeType {

    OVERFLOW(0),
    UNDERFLOW(1),
    DIVZERO(2),
    EQUALORNOT(3);

    private int value;

    ConditionCodeType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ConditionCodeType getConditionCodeType(int value) {
        for (ConditionCodeType conditionCodeType : ConditionCodeType.values()) {
            if (conditionCodeType.value == value) {
                return conditionCodeType;
            }
        }

        throw new IllegalArgumentException("Invalid Condition Code Type: " + value);
    }
}
