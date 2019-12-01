package main.java.device;

import main.java.panel.Controller;
import main.java.util.Utils;

/**
 * @author jalal
 * @since 10/9/19
 * <p>
 * Console Printer for output
 * <p>
 */
public class ConsolePrinter extends Device {

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.CONSOLE_PRINTER;
    }

    @Override
    public void run() {
        System.err.println("Screen Print: " + getValue());
        if (getValue().equals("0010111101101110"))
            Controller.pushToScreen("\n", false);
        else if (getValue().substring(0,8).equals("00000000")) {
            StringBuffer result = new StringBuffer();
            result.append((char) Utils.binaryToDecimal(getValue()));
            Controller.pushToScreen(result.toString(), false);
        } else
            Controller.pushToScreen(getValue(), false);
    }
}
