package main.java.device;

import main.java.panel.Controller;

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

    @Override
    public void run() {
        if (getValue().equals("0010111101101110"))
            Controller.pushToScreen("\n", false);
        else
            Controller.pushToScreen(getValue(), false);
    }
}
