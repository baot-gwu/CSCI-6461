package main.java.device;

/**
 * @author jalal
 * @since 10/9/19
 * <p>
 * Console Keyboard
 * <p>
 */
public class ConsoleKeyboard extends Device {

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.CONSOLE_KEYBOARD;
    }
}
