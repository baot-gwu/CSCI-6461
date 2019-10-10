package main.java.device;

/**
 * @author jalal
 * @since 10/9/19
 * <p>
 * Abstract device
 * <p>
 */
public abstract class Device {

    public static final int MAX_DEVICES = 32;

    private String value;

    public abstract DeviceType getDeviceType();

    public String getValue() {
        return value;
    }

    public String getBinaryValue() {
        return getValue();
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setBinaryValue(String value) {
        this.value = value;
    }
}
