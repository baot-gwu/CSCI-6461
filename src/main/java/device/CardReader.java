package main.java.device;

/**
 * @author jalal
 * @since 10/9/19
 * <p>
 * Card Reader
 * <p>
 */
public class CardReader extends Device {

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.CARD_READER;
    }
}
