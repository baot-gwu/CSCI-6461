/**
 * @author jalal
 * @since 12/9/19
 */
public abstract class Register {

    public static final int size = 16;

    int registerNumber;
    private String value;

    abstract RegisterType getType();

    public int getRegisterNumber() {
        return registerNumber;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
