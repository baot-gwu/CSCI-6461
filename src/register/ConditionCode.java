package register;

/**
 * @author jalal
 * @since 12/9/19
 *
 * Condition Code Register
 */
public class ConditionCode extends Register {

    private ConditionCodeType conditionCodeType;

    @Override
    RegisterType getType() {
        return RegisterType.CONDITION_CODE;
    }

    @Override
    public void setDecimalValue(int decimalValue) {
        setConditionCodeType(decimalValue);

        super.setDecimalValue(decimalValue);
    }

    public void setConditionCodeType(int decimalValue) {
        this.conditionCodeType = ConditionCodeType.getConditionCodeType(decimalValue);
    }

    public ConditionCodeType getConditionCodeType() {
        return conditionCodeType;
    }
}