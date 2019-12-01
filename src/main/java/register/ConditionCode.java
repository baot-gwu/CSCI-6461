package main.java.register;

/**
 * @author jalal
 * @since 12/9/19
 * <p>
 * Condition Code Register, which contains Condition Code
 */
public class ConditionCode extends Register {

    private ConditionCodeType conditionCodeType;

    @Override
    RegisterType getType() {
        return RegisterType.CONDITION_CODE;
    }

    public void setConditionCodeType(ConditionCodeType conditionCodeType) {
        this.conditionCodeType = conditionCodeType;
    }

    public ConditionCodeType getConditionCodeType() {
        return conditionCodeType;
    }

    @Override
    public String toString() {
        if (conditionCodeType == null) {
            return "";
        }

        return "Condition Code Type: " + conditionCodeType.toString()
                + (conditionCodeType == ConditionCodeType.EQUALORNOT ? " Condition Code Value: " + getDecimalValue() : "");
    }
}