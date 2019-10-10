package main.java.device;

/**
 * @author jalal
 * @since 10/9/19
 * <p>
 * Console Printer
 * <p>
 */
public class ConsolePrinter extends Device {

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.CONSOLE_PRINTER;
    }
}
